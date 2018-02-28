package com.coz.coin.client;


import lombok.extern.slf4j.Slf4j;
import rx.functions.Action1;
import ws.wamp.jawampa.ApplicationError;
import ws.wamp.jawampa.PubSubData;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClientBuilder;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author David
 */
@Slf4j
public class WSSClient implements AutoCloseable
{
    private final WampClient wampClient;
    private final Map<String, Action1<PubSubData>> subscriptions;

    public WSSClient(String uri, String realm) throws ApplicationError, Exception
    {
        this.subscriptions = new HashMap<>();
        WampClientBuilder builder = new WampClientBuilder();
        builder.withConnectorProvider(new NettyWampClientConnectorProvider())
                .withUri(uri)
                .withRealm(realm)
                .withInfiniteReconnects()
                .withReconnectInterval(5, TimeUnit.SECONDS);

        wampClient = builder.build();
    }


    /***
     * 
     * @param runTimeInMillis The subscription time expressed in milliseconds. The minimum runtime is 1 minute. 
     */
    public void run(long runTimeInMillis)
    {
        try
        {
            wampClient.statusChanged()
                    .subscribe((WampClient.State newState)
                            ->
                    {
                        if (newState instanceof WampClient.ConnectedState)
                        {

                            for (Entry<String, Action1<PubSubData>> subscription : this.subscriptions.entrySet())
                            {
                                wampClient.makeSubscription(subscription.getKey()).subscribe(subscription.getValue());
                            }
                        }
                        else if (newState instanceof WampClient.DisconnectedState)
                        {
                        }
                        else if (newState instanceof WampClient.ConnectingState)
                        {
                        }
                    });

            wampClient.open();
            long startTime = System.currentTimeMillis();

            while (wampClient.getTerminationFuture().isDone() == false && (startTime + runTimeInMillis > System.currentTimeMillis()))
            {
                TimeUnit.MINUTES.sleep(1);
            }
        }
        catch (Exception ex)
        {
        }
    }

    @Override
    public void close() throws Exception
    {
        wampClient.close().toBlocking().last();
    }
}
