package com.coz.coin.domain.account.request;

import com.coz.coin.Constants;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Base request parameters for order-related methods.
 */
public class OrderRequest {

  private final String symbol;

  private Long recvWindow;

  private Long timestamp;

  public OrderRequest(String symbol) {
    this.symbol = symbol;
    this.timestamp = System.currentTimeMillis();
    this.recvWindow = Constants.DEFAULT_RECEIVING_WINDOW;
  }

  public String getSymbol() {
    return symbol;
  }

  public Long getRecvWindow() {
    return recvWindow;
  }

  public OrderRequest recvWindow(Long recvWindow) {
    this.recvWindow = recvWindow;
    return this;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public OrderRequest timestamp(Long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, Constants.TO_STRING_BUILDER_STYLE)
        .append("symbol", symbol)
        .append("recvWindow", recvWindow)
        .append("timestamp", timestamp)
        .toString();
  }
}
