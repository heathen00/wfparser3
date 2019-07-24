package com.ht.wrap;

import java.util.Locale;
import java.util.ResourceBundle;

final class ResourceBundleWrapperImp implements ResourceBundleWrapper {
  private final String baseBundleName;
  private final Locale locale;
  private ResourceBundle resourceBundle;

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
    if (isDefined(resourceBundle)) {
      return resourceBundle.getLocale();
    } else {
      return locale;
    }
  }

  private boolean isDefined(ResourceBundle resourceBundle) {
    return null != resourceBundle;
  }

  @Override
  public void loadResourceBundle() {
    resourceBundle = ResourceBundle.getBundle(baseBundleName, locale,
        ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_PROPERTIES));
  }

  @Override
  public String getUnformattedString(String key) {
    return resourceBundle.getString(key);
  }

  @Override
  public String getFormattedString(String key, Object... args) {
    return String.format(getLocale(), resourceBundle.getString(key), args);
  }
}
