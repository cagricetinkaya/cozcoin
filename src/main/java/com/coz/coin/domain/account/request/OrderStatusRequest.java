package com.coz.coin.domain.account.request;

import com.coz.coin.Constants;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A specialized order request with additional filters.
 */
public class OrderStatusRequest extends OrderRequest {

  private Long orderId;

  private String origClientOrderId;

  public OrderStatusRequest(String symbol, Long orderId) {
    super(symbol);
    this.orderId = orderId;
  }

  public OrderStatusRequest(String symbol, String origClientOrderId) {
    super(symbol);
    this.origClientOrderId = origClientOrderId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public OrderStatusRequest orderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public String getOrigClientOrderId() {
    return origClientOrderId;
  }

  public OrderStatusRequest origClientOrderId(String origClientOrderId) {
    this.origClientOrderId = origClientOrderId;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, Constants.TO_STRING_BUILDER_STYLE)
        .append("orderId", orderId)
        .append("origClientOrderId", origClientOrderId)
        .toString();
  }
}
