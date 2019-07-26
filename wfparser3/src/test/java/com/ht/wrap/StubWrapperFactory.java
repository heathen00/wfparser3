package com.ht.wrap;

import java.util.Locale;

public final class StubWrapperFactory implements WrapperFactory {
  private static final StubWrapperFactory STUB_WRAPPER_FACTORY_SINGLETON = new StubWrapperFactory();

  public static StubWrapperFactory createStubWrapperFactory() {
    return STUB_WRAPPER_FACTORY_SINGLETON;
  }

  private final ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private final ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;

  StubWrapperFactory() {
    resourceBundleWrapperForLocaleConfigurator = new ResourceBundleWrapperConfigurator();
    resourceBundleWrapperForRootLocaleConfigurator = new ResourceBundleWrapperConfigurator();
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapperForLocale(String resourceBundleName,
      Locale locale) {
    resourceBundleWrapperForLocaleConfigurator.baseBundleName(resourceBundleName).locale(locale);
    System.out
        .println("target template: " + resourceBundleWrapperForLocaleConfigurator.getTemplate());
    return new StubResourceBundleWrapperImp(
        resourceBundleWrapperForLocaleConfigurator.getTemplate());
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapperForRootLocale(String resourceBundleName) {
    resourceBundleWrapperForRootLocaleConfigurator.baseBundleName(resourceBundleName);
    System.out
        .println("root template: " + resourceBundleWrapperForRootLocaleConfigurator.getTemplate());
    return new StubResourceBundleWrapperImp(
        resourceBundleWrapperForRootLocaleConfigurator.getTemplate());
  }

  public ResourceBundleWrapperConfigurator getResourceBundleWrapperForLocaleConfigurator() {
    return resourceBundleWrapperForLocaleConfigurator;
  }

  public ResourceBundleWrapperConfigurator getResourceBundleWrapperForRootLocaleConfigurator() {
    return resourceBundleWrapperForRootLocaleConfigurator;
  }
}
