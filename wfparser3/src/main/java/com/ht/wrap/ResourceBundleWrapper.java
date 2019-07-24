package com.ht.wrap;

import java.util.Locale;

public interface ResourceBundleWrapper {
  String getBaseBundleName();

  Locale getLocale();

  String getString(String key);

  void loadResourceBundle();

  // this.resourceBundle =
  // factoryInternal.createResourceBundleForLocalizerBundle(resourceBundleName, locale);
}
