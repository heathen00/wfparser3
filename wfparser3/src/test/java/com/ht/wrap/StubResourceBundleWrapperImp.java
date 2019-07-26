package com.ht.wrap;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class StubResourceBundleWrapperImp implements ResourceBundleWrapper {
  private final String expectedBaseBundleName;
  private final Locale expectedLocale;
  private final Map<String, String> expectedStringMap;

  StubResourceBundleWrapperImp(String expectedBaseBundleName, Locale expectedLocale) {
    this.expectedBaseBundleName = expectedBaseBundleName;
    this.expectedLocale = expectedLocale;
    expectedStringMap = new HashMap<>();
  }

  @Override
  public String getBaseBundleName() {
    return expectedBaseBundleName;
  }

  @Override
  public Locale getLocale() {
    return expectedLocale;
  }

  @Override
  public void loadResourceBundle() {
    // TODO Auto-generated method stub

  }

  @Override
  public String getUnformattedString(String key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getFormattedString(String key, Object... args) {
    // TODO Auto-generated method stub
    return null;
  }

}
