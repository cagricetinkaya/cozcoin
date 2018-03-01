package com.coz.coin.rest;

import com.coz.coin.client.poloniex.PoloniexExchangeService;
import com.coz.coin.data.model.poloniex.PoloniexChartData;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public String getData(HttpServletRequest request, HttpServletResponse response) {

        String url = Constants.databaseUrl + "/_design/" + request.getParameter("view") + "/_view/";

        url += request.getParameter("view") + "?";
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();

            if (key.equals("view")) {
                continue;
            }

            switch (key) {
                case "id":
                    url = Constants.databaseUrl + "/" + value[0] + "&";
                    break;
                case "keys":
                case "startkey":
                case "endkey":
                    url += (key + "=[");
                    for (String val : value) {
                        String[] subKeys = val.split(",");
                        if (subKeys.length > 1) {
                            url += ("[");
                            for (String sub : subKeys) {
                                url += ("\"" + sub + "\",");
                            }
                            url = url.substring(0, url.length() - 1);
                            url += "]&";
                        } else {
                            url += ("\"" + val + "\",");
                        }
                    }
                    url = url.substring(0, url.length() - 1);
                    url += "]&";
                    break;

                case "key":
                    url += (key + "=\"" + value[0] + "\"&");
                    break;

                default:
                    url += (key + "=" + value[0] + "&");
            }

        }
        url = url.substring(0, url.length() - 1);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity.getBody();
    }

}
