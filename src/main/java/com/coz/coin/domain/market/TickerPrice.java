package com.coz.coin.domain.market;

import com.coz.coin.Constants;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Wraps a symbol and its corresponding latest price.
 */
public class TickerPrice {

  /**
   * Ticker symbol.
   */
  private String symbol;

  /**
   * Latest price.
   */
  private String price;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, Constants.TO_STRING_BUILDER_STYLE)
        .append("symbol", symbol)
        .append("price", price)
        .toString();
  }
}
