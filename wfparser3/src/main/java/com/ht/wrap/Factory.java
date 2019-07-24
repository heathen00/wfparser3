package com.ht.wrap;

import java.util.Locale;

public interface Factory {
  static Factory createWrapperFactory() {
    return new FactoryImp();
  }

  ResourceBundleWrapper createResourceBundleWrapper(String resourceBundleName, Locale locale);
}
