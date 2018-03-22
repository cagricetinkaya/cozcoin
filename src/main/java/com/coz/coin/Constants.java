package com.coz.coin;

import org.apache.commons.lang.builder.ToStringStyle;

public class Constants {

    public static final String poloniexApiKey = "Y2EU702R-HOR6U2Q1-SI8XYUP5-FXQZ3ZKS";
    public static final String poloniexAPISecret = "7eee5eea0b8cf889da936c97faad55487dfdbc34bfd026db1c5872707ed80b07ff18048c00c454cb92da4fae4f185cf7086752c248f55b60cc30f57252a9734b";
    public static final String databaseUrl = "http://10.6.20.162:5984/coin";

    public static final String API_BASE_URL = "https://api.binance.com";
    public static final String API_KEY_HEADER = "X-MBX-APIKEY";
    public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY";
    public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";
    public static final long DEFAULT_RECEIVING_WINDOW = 6_000_000L;
    public static ToStringStyle TO_STRING_BUILDER_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    public static  final String EXCHANGE_CODE_BTC_USD = "BTC_USD";
    public static  final String EXCHANGE_CODE_ETH_USD = "ETH_USD";

}
