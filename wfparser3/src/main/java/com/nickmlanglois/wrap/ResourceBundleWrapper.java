package com.nickmlanglois.wrap;

import java.util.Locale;

public interface ResourceBundleWrapper {
  String getBaseBundleName();

  Locale getLocale();

  void loadResourceBundle();

  String getUnformattedString(String key);

  String getFormattedString(String key, Object... args);
}
