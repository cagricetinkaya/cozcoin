package com.coz.coin.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/couch")
public class CouchController {

             @RequestMapping("/getDeneme")
        public String getDeneme(HttpServletRequest request, HttpServletResponse response){
        return "deneme";
        }

}
