package com.nickmlanglois.wrap;

import java.util.Locale;

public final class ResourceBundleWrapperConfigurator {
  private ResourceBundleWrapperTemplate resourceBundleWrapperTemplate;

  ResourceBundleWrapperConfigurator() {
    resetAllPrivate();
  }

  private void resetAllPrivate() {
    resourceBundleWrapperTemplate = new ResourceBundleWrapperTemplate();
  }

  public ResourceBundleWrapperConfigurator resetAll() {
    resetAllPrivate();
    return this;
  }

  public ResourceBundleWrapperConfigurator baseBundleName(String baseBundleName) {
    resourceBundleWrapperTemplate.setBaseBundleName(baseBundleName);
    return this;
  }

  public ResourceBundleWrapperConfigurator locale(Locale locale) {
    resourceBundleWrapperTemplate.setLocale(locale);
    return this;
  }

  public ResourceBundleWrapperConfigurator doesResourceBundleExist(
      boolean doesResourceBundleExist) {
    resourceBundleWrapperTemplate.setDoesResourceBundleExist(doesResourceBundleExist);
    return this;
  }

  public ResourceBundleWrapperConfigurator doesLocalizedStringExist(
      boolean doesLocalizedStringExist) {
    resourceBundleWrapperTemplate.setDoesLocalizedStringExist(doesLocalizedStringExist);
    return this;
  }

  ResourceBundleWrapperTemplate getTemplate() {
    return resourceBundleWrapperTemplate;
  }

  public ResourceBundleWrapperConfigurator addLocalizedString(String key, String string) {
    resourceBundleWrapperTemplate.addLocalizedString(key, string);
    return this;
  }
}
