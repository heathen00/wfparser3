package com.ht.wrap;

import java.util.Locale;

final class ResourceBundleWrapperImp implements ResourceBundleWrapper {
  private final String baseBundleName;
  private final Locale locale;

  ResourceBundleWrapperImp(String baseBundleName, Locale locale) {
    this.baseBundleName = baseBundleName;
    this.locale = locale;
  }

  @Override
  public String getBaseBundleName() {
    return baseBundleName;
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public String getString(String key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void loadResourceBundle() {
    // TODO Auto-generated method stub
  }
}
