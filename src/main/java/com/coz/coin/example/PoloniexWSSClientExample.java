package com.coz.coin.example;

import com.coz.coin.client.WSSClient;



/**
 *
 * @author David
 */
public class PoloniexWSSClientExample
{
    private static final String ENDPOINT_URL = "wss://api.poloniex.com";
    private static final String DEFAULT_REALM = "realm1";

    public static void main(String[] args)
    {
        try
        {
            new PoloniexWSSClientExample().run();
        }
        catch (Exception ex)
        {
            System.exit(-1);
        }
    }

    public void run() throws Exception
    {
        try (WSSClient poloniexWSSClient = new WSSClient(ENDPOINT_URL, DEFAULT_REALM))
        {

            poloniexWSSClient.run(60000);
        }
    }
}
