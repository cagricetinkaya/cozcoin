package com.coz.coin.rest;

import com.coz.coin.client.poloniex.PoloniexExchangeService;
import com.coz.coin.data.model.poloniex.PoloniexChartData;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import com.coz.coin.VTUtil;
import com.coz.coin.Constants;



@RestController
@RequestMapping("/couch")
public class CouchController  {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/saveData")
    public String saveData(HttpServletRequest request){
        JSONObject result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(request.getParameter("doc"), headers);

        result = restTemplate.postForObject("http://10.6.20.162:5984/coin", entity, JSONObject.class);
        return  result.toString();
    }

    public String saveDataJson(JSONObject data){
        JSONObject result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(data.toString(), headers);

        result = restTemplate.postForObject(Constants.databaseUrl, entity, JSONObject.class);
        result.put("succes",true);
        return  result.toString();
    }

    @RequestMapping("/getCoinDailyGap")
    public List<PoloniexChartData> getCoinDailyGap(HttpServletRequest request, HttpServletResponse response) {

        String coinId = VTUtil.reqgetString(request.getParameter("coinId"),"1");

        PoloniexExchangeService service = new PoloniexExchangeService(Constants.poloniexApiKey, Constants.poloniexAPISecret);

        Long yesterdayEpochSecond = ZonedDateTime.now(ZoneOffset.UTC).minusDays(1).toEpochSecond();

        List<PoloniexChartData> btcDailyChartData = service.returnChartData(PoloniexExchangeService.USDT_BTC_CURRENCY_PAIR, PoloniexExchangeService.DAILY_TIME_PERIOD, yesterdayEpochSecond);
        for (int i = 0; i < btcDailyChartData.size(); i++) {
            JSONObject obj = (JSONObject) JSONObject.fromObject(btcDailyChartData.get(i).toString());
            obj.put("type","coinGap");
            saveDataJson(obj);
        }
        return btcDailyChartData;


    }


    @RequestMapping("/getData")
    public void getData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject obj =new JSONObject();
        //String strResult = "{\"date\":{\"offset\":{\"totalSeconds\":0,\"id\":\"Z\",\"rules\":{\"fixedOffset\":true,\"transitionRules\":[],\"transitions\":[]}},\"zone\":{\"totalSeconds\":0,\"id\":\"Z\",\"rules\":{\"fixedOffset\":true,\"transitionRules\":[],\"transitions\":[]}},\"dayOfMonth\":26,\"dayOfWeek\":\"MONDAY\",\"dayOfYear\":57,\"month\":\"FEBRUARY\",\"year\":2018,\"hour\":0,\"minute\":0,\"nano\":0,\"second\":0,\"monthValue\":2,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"high\":10350.11042487,\"low\":9358.52927553,\"open\":9560.19775733,\"close\":10309.58721908,\"volume\":25747541.564163,\"quoteVolume\":2614.61774974,\"weightedAverage\":9847.53567389}";
        String strResult = "{" +
                "\"_id\":\"448eb14a89ed2aad56627df30b00e3c4\"," +
                "\"_rev\":\"3-0dbd37b1089f25f38fa04075bb113fa9\"," +
                "\"Coin_Name\":\"Etherium\"," +
                "\"Coin_Code\":\"ETH\"," +
                "\"Type\":\"Coins\"," +
                "\"_attachments\":{" +
                "\"eth (1).png\":{" +
                "\"content_type\":\"image/png\"," +
                "\"revpos\":2," +
                "\"digest\":\"md5-4e9JyrAqGvTDOdAuY+MAkw==\"," +
                "\"length\":493," +
                "\"stub\":true}}" +
                "}";

        JSONObject obb=new JSONObject();

        obj.put("data",strResult);
        obj.put("success",true);
        response.getWriter().write(obj.toString());
    }
}
