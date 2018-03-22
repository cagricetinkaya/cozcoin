package com.coz.coin.domain.account;

import com.coz.coin.Constants;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A deposit address for a given asset.
 */
public class DepositAddress {

  private String address;

  private boolean success;

  private String addressTag;

  private String asset;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getAddressTag() {
    return addressTag;
  }

  public void setAddressTag(String addressTag) {
    this.addressTag = addressTag;
  }

  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, Constants.TO_STRING_BUILDER_STYLE)
        .append("address", address)
        .append("success", success)
        .append("addressTag", addressTag)
        .append("asset", asset)
        .toString();
  }
}