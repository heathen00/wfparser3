package com.nickmlanglois.wrap;

import java.util.Locale;
import com.nickmlanglois.wrap.ResourceBundleWrapper;
import com.nickmlanglois.wrap.WrapperFactory;

public final class StubWrapperFactory implements WrapperFactory {
  public static StubWrapperFactory createStubWrapperFactory() {
    return new StubWrapperFactory();
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
    return new StubResourceBundleWrapperImp(
        resourceBundleWrapperForLocaleConfigurator.getTemplate());
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapperForRootLocale(String resourceBundleName) {
    resourceBundleWrapperForRootLocaleConfigurator.baseBundleName(resourceBundleName);
    return new StubResourceBundleWrapperImp(
        resourceBundleWrapperForRootLocaleConfigurator.getTemplate());
  }

  public ResourceBundleWrapperConfigurator getResourceBundleWrapperForLocaleConfigurator() {
    return resourceBundleWrapperForLocaleConfigurator;
  }

  public ResourceBundleWrapperConfigurator getResourceBundleWrapperForRootLocaleConfigurator() {
    return resourceBundleWrapperForRootLocaleConfigurator;
  }

  @Override
  public void resetAll() {
    resourceBundleWrapperForLocaleConfigurator.resetAll();
    resourceBundleWrapperForRootLocaleConfigurator.resetAll();
  }
}
