package com.coz.coin.rest;

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

@RestController
@RequestMapping("/couch")

public class CouchController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getDeneme")
    public String getDeneme(HttpServletRequest request, HttpServletResponse response) {
        return "deneme";
    }

    @RequestMapping("/saveData")
    public String saveData(HttpServletRequest request){
        JSONObject result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(request.getParameter("doc"), headers);

        result = restTemplate.postForObject("http://10.6.20.162:5984/coin", entity, JSONObject.class);
        return  result.toString();
    }
}
