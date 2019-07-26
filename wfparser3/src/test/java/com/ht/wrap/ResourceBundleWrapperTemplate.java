package com.ht.wrap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class ResourceBundleWrapperTemplate implements ResourceBundleWrapper {
  private String baseBundleName;
  private Locale locale;
  private boolean doesResourceBundleExist;
  private boolean doesLocalizedStringExist;
  private final Map<String, String> localizedStringMap;

  ResourceBundleWrapperTemplate() {
    localizedStringMap = new HashMap<>();
    resetAllPrivate();
  }

  private void resetAllPrivate() {
    baseBundleName = "NOT SET";
    locale = Locale.ROOT;
    doesResourceBundleExist = false;
    doesLocalizedStringExist = false;
    localizedStringMap.clear();
  }

  public void resetAll() {
    resetAllPrivate();
  }

  ResourceBundleWrapperTemplate(ResourceBundleWrapperTemplate resourceBundleTemplateWrapper) {
    this.baseBundleName = resourceBundleTemplateWrapper.getBaseBundleName();
    this.locale = resourceBundleTemplateWrapper.getLocale();
    this.doesResourceBundleExist = resourceBundleTemplateWrapper.doesResourceBundleExist();
    this.doesLocalizedStringExist = resourceBundleTemplateWrapper.doesLocalizedStringExist();
    this.localizedStringMap = new HashMap<>(resourceBundleTemplateWrapper.getLocalizedStringMap());
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

  public void addLocalizedString(String key, String string) {
    localizedStringMap.put(key, string);
  }

  public Map<String, String> getLocalizedStringMap() {
    return Collections.unmodifiableMap(localizedStringMap);
  }

  public String getLocalizedString(String key) {
    return localizedStringMap.get(key);
  }

  @Override
  public String toString() {
    return "ResourceBundleWrapperTemplate [baseBundleName=" + baseBundleName + ", locale=" + locale
        + ", doesResourceBundleExist=" + doesResourceBundleExist + ", doesLocalizedStringExist="
        + doesLocalizedStringExist + ", localizedStringMap=" + localizedStringMap + "]";
  }
}

