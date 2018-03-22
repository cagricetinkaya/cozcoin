package com.coz.coin.domain.account.request;

import com.coz.coin.Constants;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Request object for canceling an order.
 */
public class CancelOrderRequest extends OrderRequest {

  private Long orderId;

  private String origClientOrderId;

  public CancelOrderRequest(String symbol, Long orderId) {
    super(symbol);
    this.orderId = orderId;
  }

  public CancelOrderRequest(String symbol, String origClientOrderId) {
    super(symbol);
    this.origClientOrderId = origClientOrderId;
  }

  /**
   * Used to uniquely identify this cancel. Automatically generated by default.
   */
  private String newClientOrderId;

  public Long getOrderId() {
    return orderId;
  }

  public CancelOrderRequest orderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public String getOrigClientOrderId() {
    return origClientOrderId;
  }

  public CancelOrderRequest origClientOrderId(String origClientOrderId) {
    this.origClientOrderId = origClientOrderId;
    return this;
  }

  public String getNewClientOrderId() {
    return newClientOrderId;
  }

  public CancelOrderRequest newClientOrderId(String newClientOrderId) {
    this.newClientOrderId = newClientOrderId;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, Constants.TO_STRING_BUILDER_STYLE)
        .append("orderId", orderId)
        .append("origClientOrderId", origClientOrderId)
        .append("newClientOrderId", newClientOrderId)
        .toString();
  }
}
