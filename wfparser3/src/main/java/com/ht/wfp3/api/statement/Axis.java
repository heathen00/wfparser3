package com.ht.wfp3.api.statement;

public enum Axis {
  U("u"), V("v");

  private final String keyword;

  Axis(String keyword) {
    this.keyword = keyword;
  }

  public String getKeyword() {
    return keyword;
  }
}
