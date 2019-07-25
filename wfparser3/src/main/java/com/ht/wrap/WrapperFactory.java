package com.ht.wrap;

import java.util.Locale;

public interface WrapperFactory {
  static WrapperFactory createWrapperFactory() {
    return WrapperFactoryImp.getFactorySingleton();
  }

  ResourceBundleWrapper createResourceBundleWrapper(String resourceBundleName, Locale locale);
}
