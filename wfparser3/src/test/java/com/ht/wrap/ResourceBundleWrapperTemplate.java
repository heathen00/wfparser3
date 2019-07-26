package com.ht.wrap;

import java.util.Locale;

class ResourceBundleWrapperTemplate implements ResourceBundleWrapper {
  private static final ResourceBundleWrapperTemplate DEFAULT_RESOURCE_BUNDLE_WRAPPER_TEMPLATE =
      new ResourceBundleWrapperTemplate();

  static ResourceBundleWrapperTemplate getDefaultResourceBundleWrapperTemplate() {
    return DEFAULT_RESOURCE_BUNDLE_WRAPPER_TEMPLATE;
  }

  private String baseBundleName;
  private Locale locale;
  private boolean doesResourceBundleExist;
  private boolean doesLocalizedStringExist;

  private ResourceBundleWrapperTemplate() {
    resetAllPrivate();
  }

  private void resetAllPrivate() {
    baseBundleName = "NOT SET";
    locale = Locale.ROOT;
    doesResourceBundleExist = false;
    doesLocalizedStringExist = false;
  }

  public void resetAll() {
    resetAllPrivate();
  }

  ResourceBundleWrapperTemplate(ResourceBundleWrapperTemplate resourceBundleTemplateWrapper) {
    this.baseBundleName = resourceBundleTemplateWrapper.getBaseBundleName();
    this.locale = resourceBundleTemplateWrapper.getLocale();
    this.doesResourceBundleExist = resourceBundleTemplateWrapper.doesResourceBundleExist();
    this.doesLocalizedStringExist = resourceBundleTemplateWrapper.doesLocalizedStringExist();
  }

  @Override
  public String getBaseBundleName() {
    return baseBundleName;
  }

  public void setBaseBundleName(String baseBundleName) {
    this.baseBundleName = baseBundleName;
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  @Override
  public void loadResourceBundle() {
    throw new UnsupportedOperationException("operation not supported by template");
  }

  @Override
  public String getUnformattedString(String key) {
    throw new UnsupportedOperationException("operation not supported by template");
  }

  @Override
  public String getFormattedString(String key, Object... args) {
    throw new UnsupportedOperationException("operation not supported by template");
  }

  public boolean doesResourceBundleExist() {
    return doesResourceBundleExist;
  }

  public void setDoesResourceBundleExist(boolean doesResourceBundleExist) {
    this.doesResourceBundleExist = doesResourceBundleExist;
  }

  public boolean doesLocalizedStringExist() {
    return doesLocalizedStringExist;
  }

  public void setDoesLocalizedStringExist(boolean doesLocalizedStringExist) {
    this.doesLocalizedStringExist = doesLocalizedStringExist;
  }
}

