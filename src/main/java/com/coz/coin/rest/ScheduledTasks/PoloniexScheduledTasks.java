package com.coz.coin.rest.ScheduledTasks;

import com.coz.coin.Constants;
import com.coz.coin.VTUtil;
import com.coz.coin.client.poloniex.PoloniexExchangeService;
import com.coz.coin.data.model.poloniex.PoloniexTicker;
import com.coz.coin.rest.CouchController;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PoloniexScheduledTasks {


    @Autowired
    CouchController couchController;

    @Scheduled(fixedRate = 60000)
    public void saveUsdEthPricePoloniex()  {

        String exchangeParams = "key:ETH_USD";

        String exchange = couchController.getDataByParamAndViewName(exchangeParams,"findExchangeByName");

        String exchangeId = VTUtil.getValueString("id",exchange);

        String stockParams = "key:Poloniex";

        String stock = couchController.getDataByParamAndViewName(stockParams,"findStockByName");

        String stockId = VTUtil.getValueString("id",stock);

        PoloniexExchangeService service = new PoloniexExchangeService(Constants.poloniexApiKey, Constants.poloniexAPISecret);

        PoloniexTicker ticker = service.returnTicker(PoloniexExchangeService.USDT_ETH_CURRENCY_PAIR);
        ticker.percentChange = ticker.percentChange.multiply(BigDecimal.valueOf(100));
        ticker.exchangeId = exchangeId;
        ticker.stockId = stockId;
        ticker.Type = "Prices";
        ticker.date = VTUtil.currentDate();
        ticker.exchangeCode = Constants.EXCHANGE_CODE_ETH_USD;

        couchController.saveDataJson(JSONObject.fromObject(ticker.toString()));

    }

    @Scheduled(fixedRate = 60000)
    public void saveUsdBtcPricePoloniex()  {

        String exchangeParams = "key:BTC_USD";

        String exchange = couchController.getDataByParamAndViewName(exchangeParams,"findExchangeByName");

        String exchangeId = VTUtil.getValueString("id",exchange);

        String stockParams = "key:Poloniex";

        String stock = couchController.getDataByParamAndViewName(stockParams,"findStockByName");

        String stockId = VTUtil.getValueString("id",stock);

        PoloniexExchangeService service = new PoloniexExchangeService(Constants.poloniexApiKey, Constants.poloniexAPISecret);

        PoloniexTicker ticker = service.returnTicker(PoloniexExchangeService.USDT_BTC_CURRENCY_PAIR);
        ticker.percentChange = ticker.percentChange.multiply(BigDecimal.valueOf(100));
        ticker.exchangeId = exchangeId;
        ticker.stockId = stockId;
        ticker.Type = "Prices";
        ticker.date = VTUtil.currentDate();
        ticker.exchangeCode = Constants.EXCHANGE_CODE_BTC_USD;

        couchController.saveDataJson(JSONObject.fromObject(ticker.toString()));

    }


}
