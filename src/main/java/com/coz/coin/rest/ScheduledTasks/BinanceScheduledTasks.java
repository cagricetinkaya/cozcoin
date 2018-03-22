package com.coz.coin.rest.ScheduledTasks;

import com.coz.coin.Constants;
import com.coz.coin.VTUtil;
import com.coz.coin.client.binance.BinanceApiClientFactory;
import com.coz.coin.client.binance.BinanceApiRestClient;
import com.coz.coin.data.model.binance.BinanceTicker;
import com.coz.coin.domain.market.*;
import com.coz.coin.rest.CouchController;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class BinanceScheduledTasks  {


    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();

    BinanceApiRestClient client = factory.newRestClient();


    public void saveUsdBtcPriceBinance(CouchController couchController){

        BinanceTicker ticker = new BinanceTicker();

        String exchangeParams = "key:BTC_USD";

        String exchange = couchController.getDataByParamAndViewName(exchangeParams,"findExchangeByName");

        String exchangeId = VTUtil.getValueString("id",exchange);

        String stockParams = "key:Binance";

        String stock = couchController.getDataByParamAndViewName(stockParams,"findStockByName");

        String stockId = VTUtil.getValueString("id",stock);

        TickerStatistics candlestick = client.get24HrPriceStatistics("BTCUSDT");

        ticker.exchangeId = exchangeId;
        ticker.stockId = stockId;
        ticker.last = BigDecimal.valueOf(Double.valueOf(candlestick.getLastPrice()));
        ticker.baseVolume = BigDecimal.valueOf(Double.valueOf(candlestick.getVolume()));
        ticker.highestBid = BigDecimal.valueOf(Double.valueOf(candlestick.getBidPrice()));
        ticker.lowestAsk =  BigDecimal.valueOf(Double.valueOf(candlestick.getAskPrice()));
        ticker.percentChange = BigDecimal.valueOf(Double.valueOf(candlestick.getPriceChangePercent()));
        ticker.Type = "Prices";
        ticker.date = VTUtil.currentDate();
        ticker.exchangeCode = Constants.EXCHANGE_CODE_BTC_USD;

        couchController.saveDataJson(JSONObject.fromObject(ticker.toString()));

    }

    public void saveUsdEthPriceBinance(CouchController couchController){

        BinanceTicker ticker = new BinanceTicker();

        String exchangeParams = "key:ETH_USD";

        String exchange = couchController.getDataByParamAndViewName(exchangeParams,"findExchangeByName");

        String exchangeId = VTUtil.getValueString("id",exchange);

        String stockParams = "key:Binance";

        String stock = couchController.getDataByParamAndViewName(stockParams,"findStockByName");

        String stockId = VTUtil.getValueString("id",stock);

        TickerStatistics candlestick = client.get24HrPriceStatistics("ETHUSDT");

        ticker.exchangeId = exchangeId;
        ticker.stockId = stockId;
        ticker.last = BigDecimal.valueOf(Double.valueOf(candlestick.getLastPrice()));
        ticker.baseVolume = BigDecimal.valueOf(Double.valueOf(candlestick.getVolume()));
        ticker.highestBid = BigDecimal.valueOf(Double.valueOf(candlestick.getBidPrice()));
        ticker.lowestAsk =  BigDecimal.valueOf(Double.valueOf(candlestick.getAskPrice()));
        ticker.percentChange = BigDecimal.valueOf(Double.valueOf(candlestick.getPriceChangePercent()));
        ticker.Type = "Prices";
        ticker.date = VTUtil.currentDate();
        ticker.exchangeCode = Constants.EXCHANGE_CODE_ETH_USD;

        couchController.saveDataJson(JSONObject.fromObject(ticker.toString()));


    }

}
