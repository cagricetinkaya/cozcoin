package com.coz.coin.client.poloniex;

import com.coz.coin.interfaces.LendingService;
import com.coz.coin.interfaces.TradingAPIClient;
import com.coz.coin.data.map.poloniex.PoloniexDataMapper;
import com.coz.coin.data.model.poloniex.PoloniexActiveLoanTypes;
import com.coz.coin.data.model.poloniex.PoloniexLendingHistory;
import com.coz.coin.data.model.poloniex.PoloniexLendingResult;
import com.coz.coin.data.model.poloniex.PoloniexLoanOffer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cheolhee
 */
public class PoloniexLendingService implements LendingService
{
    private final TradingAPIClient tradingClient;
    private final PoloniexDataMapper mapper;


    public PoloniexLendingService(String apiKey, String apiSecret)
    {
        this.tradingClient = new PoloniexTradingAPIClient(apiKey, apiSecret);
        this.mapper = new PoloniexDataMapper();
    }

    public PoloniexLendingService(TradingAPIClient tradingClient, PoloniexDataMapper mapper)
    {
        this.tradingClient = tradingClient;
        this.mapper = mapper;
    }

    /**
     * Returns lending history
     *
     * @param hours time range
     * @return limit number of rows returned
     */
    @Override
    public List<PoloniexLendingHistory> returnLendingHistory(int hours, int limit)
    {
        long start = System.currentTimeMillis();
        List<PoloniexLendingHistory> lendingHistory = new ArrayList<>();
        try
        {
            String lendingHistoryData = tradingClient.returnLendingHistory(hours, limit);
            lendingHistory = mapper.mapLendingHistory(lendingHistoryData);
            return lendingHistory;
        }
        catch (Exception ex)
        {
        }

        return lendingHistory;
    }

    /**
     * @param currency
     * @param amount
     * @param lendingRate
     * @param duration
     * @param autoRenew
     * @return
     */
    @Override
    public PoloniexLendingResult createLoanOffer(String currency, BigDecimal amount, BigDecimal lendingRate, int duration, boolean autoRenew)
    {
        long start = System.currentTimeMillis();
        PoloniexLendingResult result = null;
        try
        {
            String res = tradingClient.createLoanOffer(currency, amount, lendingRate, duration, autoRenew);
            result = mapper.mapLendingResult(res);

        }
        catch (Exception ex)
        {
        }

        return result;
    }

    /**
     * @param orderNumber
     * @return
     */
    @Override
    public PoloniexLendingResult cancelLoanOffer(String orderNumber)
    {
        long start = System.currentTimeMillis();
        PoloniexLendingResult result = null;
        try
        {
            String res = tradingClient.cancelLoanOffer(orderNumber);
            result = mapper.mapLendingResult(res);
        }
        catch (Exception ex)
        {
        }

        return result;
    }

    /**
     * @return
     */
    @Override
    public PoloniexActiveLoanTypes returnActiveLoans()
    {
        long start = System.currentTimeMillis();
        PoloniexActiveLoanTypes activeLoanTypes = null;

        try
        {
            String res = tradingClient.returnActiveLoans();
            activeLoanTypes = mapper.mapActiveLoans(res);
            return activeLoanTypes;
        }
        catch (Exception ex)
        {
        }

        return activeLoanTypes;
    }

    @Override
    public List<PoloniexLoanOffer> returnOpenLoanOffers(String currency)
    {
        long start = System.currentTimeMillis();
        List<PoloniexLoanOffer> offers = Collections.EMPTY_LIST;
        try
        {
            String res = tradingClient.returnOpenLoanOffers();
            offers = mapper.mapOpenLoanOffers(currency, res);
            return offers;
        }
        catch (Exception ex)
        {
        }

        return offers;
    }

    @Override
    public PoloniexLendingResult toggleAutoRenew(String orderNumber)
    {
        long start = System.currentTimeMillis();
        PoloniexLendingResult result = null;
        try
        {
            String res = tradingClient.toggleAutoRenew(orderNumber);
            result = mapper.mapLendingResult(res);
        }
        catch (Exception ex)
        {
        }

        return result;
    }
}
