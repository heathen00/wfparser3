package com.ht.wrap;

import java.util.Locale;
import java.util.MissingResourceException;

public final class StubResourceBundleWrapperImp implements ResourceBundleWrapper {
  private final ResourceBundleWrapperTemplate resourceBundleWrapperTemplate;

  StubResourceBundleWrapperImp(ResourceBundleWrapperTemplate resourceBundleWrapper) {
    this.resourceBundleWrapperTemplate = new ResourceBundleWrapperTemplate(resourceBundleWrapper);
  }

  @Override
  public String getBaseBundleName() {
    return resourceBundleWrapperTemplate.getBaseBundleName();
  }

  @Override
  public Locale getLocale() {
    return resourceBundleWrapperTemplate.getLocale();
  }

  @Override
  public void loadResourceBundle() {
    if (!resourceBundleWrapperTemplate.doesResourceBundleExist()) {
      throw new MissingResourceException("resource bundle does not exist", getBaseBundleName(),
          "no key");
    }
  }

  @Override
  public String getUnformattedString(String key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getFormattedString(String key, Object... args) {
    // TODO Auto-generated method stub
    return null;
  }

}
