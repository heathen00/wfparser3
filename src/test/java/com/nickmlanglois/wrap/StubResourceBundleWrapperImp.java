package com.nickmlanglois.wrap;

import java.util.Locale;
import java.util.MissingResourceException;
import com.nickmlanglois.wrap.ResourceBundleWrapper;

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
    if (!resourceBundleWrapperTemplate.doesLocalizedStringExist()) {
      throw new MissingResourceException("unformatted localized string does not exist",
          getBaseBundleName(), key);
    }
    return resourceBundleWrapperTemplate.getLocalizedString(key);
  }

  @Override
  public String getFormattedString(String key, Object... args) {
    if (!resourceBundleWrapperTemplate.doesLocalizedStringExist()) {
      throw new MissingResourceException("formatted localized string does not exist",
          getBaseBundleName(), key);
    }
    return String.format(getLocale(), resourceBundleWrapperTemplate.getLocalizedString(key), args);
  }
}
