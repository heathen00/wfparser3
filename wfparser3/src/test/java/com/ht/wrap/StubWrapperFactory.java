package com.ht.wrap;

import java.util.Locale;

public class StubWrapperFactory implements WrapperFactory {
  private static final StubWrapperFactory STUB_WRAPPER_FACTORY_SINGLETON = new StubWrapperFactory();

  public static StubWrapperFactory createStubWrapperFactory() {
    return STUB_WRAPPER_FACTORY_SINGLETON;
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapperForLocale(String resourceBundleName,
      Locale locale) {
    return new StubResourceBundleWrapperImp(resourceBundleName, locale);
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapperForRootLocale(String resourceBundleName) {
    // TODO Auto-generated method stub
    return null;
  }
}
