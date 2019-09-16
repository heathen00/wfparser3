package com.nickmlanglois.wrap;

import java.util.Locale;

public interface WrapperFactory {
  static WrapperFactory createWrapperFactory() {
    return WrapperFactoryImp.createWrapperFactoryImp();
  }

  ResourceBundleWrapper createResourceBundleWrapperForLocale(String resourceBundleName,
      Locale locale);

  ResourceBundleWrapper createResourceBundleWrapperForRootLocale(String resourceBundleName);

  void resetAll();
}
