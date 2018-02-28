package com.coz.coin;

public class VTUtil {


    public static String reqgetString(String reqStr,String defaultVal) {
        if (reqStr != null && !reqStr.equals("")) {
            return reqStr;
        }
        return defaultVal;
    }
}
