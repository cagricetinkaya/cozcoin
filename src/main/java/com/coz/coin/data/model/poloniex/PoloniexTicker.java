package com.coz.coin.data.model.poloniex;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author David
 */
public class PoloniexTicker
{
    public final BigDecimal last;
    public final BigDecimal lowestAsk;
    public final BigDecimal highestBid;
    public final BigDecimal percentChange;
    public final BigDecimal baseVolume;
    public final BigDecimal quoteVolume;
    public  String exchangeId;
    public String stockId;
    public String Type;
    public String date;

    public PoloniexTicker(BigDecimal last, BigDecimal lowestAsk, BigDecimal highestBid, BigDecimal percentChange, BigDecimal baseVolume, BigDecimal quoteVolume, String exchangeId, String stockId, String Type, String date)
    {
        this.last = last;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.percentChange = percentChange;
        this.baseVolume = baseVolume;
        this.quoteVolume = quoteVolume;
        this.exchangeId = exchangeId;
        this.stockId = stockId;
        this.Type = Type;
        this.date = date;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
