package com.ht.wrap;

import java.util.Locale;

public class StubWrapperFactory implements WrapperFactory {
  private static final StubWrapperFactory STUB_WRAPPER_FACTORY_SINGLETON = new StubWrapperFactory();

  public static StubWrapperFactory createStubWrapperFactory() {
    return STUB_WRAPPER_FACTORY_SINGLETON;
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapper(String resourceBundleName,
      Locale locale) {
    // TODO Auto-generated method stub
    return null;
  }
}
