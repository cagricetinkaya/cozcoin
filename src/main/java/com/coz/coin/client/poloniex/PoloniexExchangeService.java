package com.coz.coin.client.poloniex;

import com.coz.coin.interfaces.ExchangeService;
import com.coz.coin.interfaces.PriceDataAPIClient;
import com.coz.coin.interfaces.TradingAPIClient;
import com.coz.coin.data.map.poloniex.PoloniexDataMapper;
import com.coz.coin.data.model.poloniex.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */
@Slf4j
public class PoloniexExchangeService implements ExchangeService
{
    private final PriceDataAPIClient publicClient;
    private final TradingAPIClient tradingClient;
    private final PoloniexDataMapper mapper;


    public PoloniexExchangeService(String apiKey, String apiSecret)
    {
        this.publicClient = new PoloniexPublicAPIClient();
        this.tradingClient = new PoloniexTradingAPIClient(apiKey, apiSecret);
        this.mapper = new PoloniexDataMapper();
    }

    public PoloniexExchangeService(PriceDataAPIClient publicClient, TradingAPIClient tradingClient, PoloniexDataMapper mapper)
    {
        this.publicClient = publicClient;
        this.tradingClient = tradingClient;
        this.mapper = mapper;
    }

    /**
     * *
     * Returns candlestick chart data for the given currency pair
     *
     * @param currencyPair Examples: USDT_ETH, USDT_BTC, BTC_ETH
     * @param periodInSeconds The candlestick chart data period. Valid values are 300 (5 min), 900 (15 minutes), 7200 (2 hours), 14400 (4 hours), 86400 (daily)
     * @param startEpochInSeconds UNIX timestamp format and used to specify the start date of the data returned
     * @return List of PoloniexChartData
     */
    @Override
    public List<PoloniexChartData> returnChartData(String currencyPair, Long periodInSeconds, Long startEpochInSeconds)
    {
        long start = System.currentTimeMillis();
        List<PoloniexChartData> chartData = new ArrayList<PoloniexChartData>();

        String chartDataResult = publicClient.getChartData(currencyPair, periodInSeconds, startEpochInSeconds);
        chartData = mapper.mapChartData(chartDataResult);

        return chartData;
    }

    /**
     * *
     * Returns the ticker for all currency pairs
     *
     * @return ticker data mapped to pair
     */
    @Override
    public Map<String, PoloniexTicker> returnTicker()
    {
        long start = System.currentTimeMillis();
        Map<String, PoloniexTicker> tickerResult = null;

        String tickerData = publicClient.returnTicker();
        tickerResult = mapper.mapTicker(tickerData);


        return tickerResult;
    }

    /**
     * *
     * Returns the ticker for a given currency pair
     *
     * @param currencyPair Examples: USDT_ETH, USDT_BTC, BTC_ETH
     * @return PoloniexTicker
     */
    @Override
    public PoloniexTicker returnTicker(String currencyPair)
    {
        long start = System.currentTimeMillis();
        PoloniexTicker tickerResult = null;

            String tickerData = publicClient.returnTicker();
            tickerResult = mapper.mapTickerForCurrency(currencyPair, tickerData);

        return tickerResult;
    }

    @Override
    public List<String> returnAllMarkets()
    {
        long start = System.currentTimeMillis();
        List<String> allMarkets = new ArrayList<>();
        try
        {
            String tickerData = publicClient.returnTicker();
            allMarkets = mapper.mapMarkets(tickerData);
        }
        catch (Exception ex)
        {
        }

        return allMarkets;
    }

    /**
     * *
     * Returns the complete balances inclusive non-zero balances or not depending on parameter includeZeroBalances
     * @param includeZeroBalances The includeZeroBalances
     * @return Map<String, PoloniexCompleteBalance>
     */
    @Override
    public Map<String, PoloniexCompleteBalance> returnBalance(boolean includeZeroBalances)
    {
        long start = System.currentTimeMillis();
        Map<String, PoloniexCompleteBalance> balance = null;
        try
        {
            String completeBalancesResult = tradingClient.returnCompleteBalances();
            if (includeZeroBalances) {
                balance = mapper.mapCompleteBalanceResult(completeBalancesResult);
            } else {
                balance = mapper.mapCompleteBalanceResultForNonZeroCurrencies(completeBalancesResult);
            }
        }
        catch (Exception ex)
        {
        }

        return balance;
    }

    /**
     * *
     * Returns the balance for specified currency type
     *
     * @param currencyType Examples: BTC, ETH, DASH
     * @return PoloniexCompleteBalance
     */
    @Override
    public PoloniexCompleteBalance returnCurrencyBalance(String currencyType)
    {
        long start = System.currentTimeMillis();
        PoloniexCompleteBalance balance = null;
        try
        {
            String completeBalancesResult = tradingClient.returnCompleteBalances();
            balance = mapper.mapCompleteBalanceResultForCurrency(currencyType, completeBalancesResult);
        }
        catch (Exception ex)
        {

        }

        return balance;
    }

    /**
     * *
     * If you are enrolled in the maker-taker fee schedule, returns your current trading fees and trailing 30-day volume in BTC. This information is updated once every 24 hours.
     *
     * @return PoloniexFeeInfo
     */
    @Override
    public PoloniexFeeInfo returnFeeInfo()
    {
        long start = System.currentTimeMillis();
        PoloniexFeeInfo feeInfo = null;
        try
        {
            String feeInfoResult = tradingClient.returnFeeInfo();
            feeInfo = mapper.mapFeeInfo(feeInfoResult);

        }
        catch (Exception ex)
        {

        }

        return feeInfo;
    }

    /**
     * *
     * Returns the active loans from Poloniex
     *
     * @return PoloniexActiveLoanTypes
     */
    @Override
    public PoloniexActiveLoanTypes returnActiveLoans()
    {
        long start = System.currentTimeMillis();
        PoloniexActiveLoanTypes activeLoanTypes = null;
        try
        {
            String activeLoansResult = tradingClient.returnActiveLoans();
            activeLoanTypes = mapper.mapActiveLoans(activeLoansResult);

        }
        catch (Exception ex)
        {

        }

        return activeLoanTypes;
    }

    /**
     * *
     * Returns your open orders for a given currency pair
     *
     * @param currencyPair Examples: USDT_ETH, USDT_BTC, BTC_ETH
     * @return List of PoloniexOpenOrder
     */
    @Override
    public List<PoloniexOpenOrder> returnOpenOrders(String currencyPair)
    {
        long start = System.currentTimeMillis();
        List<PoloniexOpenOrder> openOrders = new ArrayList<PoloniexOpenOrder>();
        try
        {
            String openOrdersData = tradingClient.returnOpenOrders(currencyPair);
            openOrders = mapper.mapOpenOrders(openOrdersData);

            return openOrders;
        }
        catch (Exception ex)
        {

        }

        return openOrders;
    }

    /**
     * *
     * Returns up to 50,000 trades for given currency pair
     *
     * @param currencyPair Examples: USDT_ETH, USDT_BTC, BTC_ETH
     * @return List of PoloniexTradeHistory
     */
    @Override
    public List<PoloniexTradeHistory> returnTradeHistory(String currencyPair)
    {
        long start = System.currentTimeMillis();
        List<PoloniexTradeHistory> tradeHistory = new ArrayList<PoloniexTradeHistory>();
        try
        {
            String tradeHistoryData = tradingClient.returnTradeHistory(currencyPair);
            tradeHistory = mapper.mapTradeHistory(tradeHistoryData);
            return tradeHistory;
        }
        catch (Exception ex)
        {

        }

        return tradeHistory;
    }

    /**
     * *
     * Places a sell order in a given market
     *
     * @param currencyPair Examples: USDT_ETH, USDT_BTC, BTC_ETH
     * @param sellPrice
     * @param amount
     * @param fillOrKill Will either fill in its entirety or be completely aborted
     * @param immediateOrCancel Order can be partially or completely filled, but any portion of the order that cannot be filled immediately will be canceled rather than left on the order book
     * @param postOnly A post-only order will only be placed if no portion of it fills immediately; this guarantees you will never pay the taker fee on any part of the order that fills
     * @return PoloniexOrderResult
     */
    @Override
    public PoloniexOrderResult sell(String currencyPair, BigDecimal sellPrice, BigDecimal amount, boolean fillOrKill, boolean immediateOrCancel, boolean postOnly)
    {
        long start = System.currentTimeMillis();
        PoloniexOrderResult orderResult = null;
        try
        {
            String sellTradeResult = tradingClient.sell(currencyPair, sellPrice, amount, fillOrKill, immediateOrCancel, postOnly);
            orderResult = mapper.mapTradeOrder(sellTradeResult);

        }
        catch (Exception ex)
        {

        }

        return orderResult;
    }

    /**
     * *
     * Places a buy order in a given market
     *
     * @param currencyPair Examples: USDT_ETH, USDT_BTC, BTC_ETH
     * @param buyPrice
     * @param amount
     * @param fillOrKill Will either fill in its entirety or be completely aborted
     * @param immediateOrCancel Order can be partially or completely filled, but any portion of the order that cannot be filled immediately will be canceled rather than left on the order book
     * @param postOnly A post-only order will only be placed if no portion of it fills immediately; this guarantees you will never pay the taker fee on any part of the order that fills
     * @return PoloniexOrderResult
     */
    @Override
    public PoloniexOrderResult buy(String currencyPair, BigDecimal buyPrice, BigDecimal amount, boolean fillOrKill, boolean immediateOrCancel, boolean postOnly)
    {
        long start = System.currentTimeMillis();
        PoloniexOrderResult orderResult = null;
        try
        {
            String buyTradeResult = tradingClient.buy(currencyPair, buyPrice, amount, fillOrKill, immediateOrCancel, postOnly);
            orderResult = mapper.mapTradeOrder(buyTradeResult);

        }
        catch (Exception ex)
        {

        }

        return orderResult;
    }

    /**
     * *
     * Cancels an order you have placed in a given market
     *
     * @param orderNumber
     * @return true if successful, false otherwise
     */
    @Override
    public boolean cancelOrder(String orderNumber)
    {
        long start = System.currentTimeMillis();
        boolean success = false;
        try
        {
            String cancelOrderResult = tradingClient.cancelOrder(orderNumber);
            success = mapper.mapCancelOrder(cancelOrderResult);

            return success;
        }
        catch (Exception ex)
        {

        }

        return success;
    }

    @Override
    public PoloniexOrderResult moveOrder(String orderNumber, BigDecimal rate, Boolean immediateOrCancel, Boolean postOnly)
    {
        long start = System.currentTimeMillis();
        PoloniexOrderResult orderResult = null;
        try
        {
            String moveOrderResult = tradingClient.moveOrder(orderNumber, rate);
            orderResult = mapper.mapTradeOrder(moveOrderResult);

        }
        catch (Exception ex)
        {

        }

        return orderResult;
    }
}
