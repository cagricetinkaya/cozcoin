package com.coz.coin.client.binance;
import com.coz.coin.Constants;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Binance API error object.
 */
public class BinanceApiError {

  /**
   * Error code.
   */
  private int code;

  /**
   * Error message.
   */
  private String msg;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, Constants.TO_STRING_BUILDER_STYLE)
        .append("code", code)
        .append("msg", msg)
        .toString();
  }
}
