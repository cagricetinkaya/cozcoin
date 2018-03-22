package com.coz.coin.data.model.binance;

import com.google.gson.Gson;

import java.math.BigDecimal;

/**
 *
 * @author David
 */
public class BinanceTicker
{
    public  BigDecimal last;
    public  BigDecimal lowestAsk;
    public  BigDecimal highestBid;
    public  BigDecimal percentChange;
    public  BigDecimal baseVolume;
    public  String exchangeId;
    public String stockId;
    public String Type;
    public String date;
    public String exchangeCode;

    public BinanceTicker(){

    }

    public BinanceTicker(BigDecimal last, BigDecimal lowestAsk, BigDecimal highestBid, BigDecimal percentChange, BigDecimal baseVolume , String exchangeId, String stockId, String Type, String date, String exchangeCode)
    {
        this.last = last;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.percentChange = percentChange;
        this.baseVolume = baseVolume;
        this.exchangeId = exchangeId;
        this.stockId = stockId;
        this.Type = Type;
        this.date = date;
        this.exchangeCode = exchangeCode;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
