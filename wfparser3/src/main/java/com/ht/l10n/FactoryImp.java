package com.ht.l10n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class FactoryImp implements Factory {
  private void guardNamingConvention(String constructorParameterName,
      String constructorParameterValue) throws LocalizerException {
    if (null == constructorParameterValue) {
      throw new NullPointerException(
          constructorParameterName + " constructor parameter cannot be null");
    }
    if (!constructorParameterValue.matches("^[a-z0-9][a-z0-9.]+$")
        || constructorParameterValue.endsWith(".")) {
      throw new LocalizerException(constructorParameterName
          + " can only contain the characters: lowercase letters, numbers, and period and cannot start or end with a period");
    }
  }

  @Override
  public LocalizerBundle createCompositeLocalizerBundle(Localizer localizer,
      String resourceBundleName) throws LocalizerException {
    LocalizerBundle targetLocalizerBundle =
        createTargetLocalizerBundle(localizer, resourceBundleName);
    LocalizerBundle rootLocalizerBundle =
        createRootLocaleLocalizerBundle(localizer, resourceBundleName);
    LocalizerBundle undefinedLocalizerBundle = createUndefinedLocalizerBundle();
    return new CompositeLocalizerBundleImp(localizer, targetLocalizerBundle, rootLocalizerBundle,
        undefinedLocalizerBundle);
  }

  @Override
  public Localizer createLocalizer(Locale locale) {
    if (null == locale) {
      throw new NullPointerException("locale constructor parameter cannot be null");
    }
    return new LocalizerImp(locale);
  }

  @Override
  public LocalizerBundle createTargetLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException {
    createLocalizerBundleGuard(localizer, resourceBundleName);
    ResourceBundle resourceBundle = null;
    try {
      resourceBundle = createResourceBundleForLocale(localizer.getLocale(), resourceBundleName);
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return new LocalizerBundleImp(localizer, resourceBundle);
  }

  @Override
  public LocalizerBundle createRootLocaleLocalizerBundle(Localizer localizer,
      String resourceBundleName) throws LocalizerException {
    createLocalizerBundleGuard(localizer, resourceBundleName);
    ResourceBundle resourceBundle = null;
    try {
      resourceBundle = createResourceBundleForLocale(Locale.ROOT, resourceBundleName);
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return new LocalizerBundleImp(localizer, resourceBundle);
  }

  @Override
  public LocalizerBundle createUndefinedLocalizerBundle() {
    return new UndefinedLocalizerBundle();
  }

  private void createLocalizerBundleGuard(Localizer localizer, String resourceBundleName) {
    if (null == localizer) {
      throw new NullPointerException("localizer constructor parameter cannot be null");
    }
    if (null == resourceBundleName) {
      throw new NullPointerException("resourceBundleName constructor parameter cannot be null");
    }
  }

  private ResourceBundle createResourceBundleForLocale(Locale locale, String resourceBundleName) {
    ResourceBundle resourceBundle;
    resourceBundle = ResourceBundle.getBundle(resourceBundleName, locale,
        ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_PROPERTIES));
    return resourceBundle;
  }

  LocalizerBundle createNullLocalizerBundle() {
    return new NullLocalizerBundleImp();
  }

  @Override
  public LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String instanceName) throws LocalizerException {
    if (null == localizer) {
      throw new NullPointerException("localizer constructor parameter cannot be null");
    }
    guardNamingConvention("groupName", groupName);
    guardNamingConvention("typeName", typeName);
    guardNamingConvention("instanceName", instanceName);
    return new LocalizerTypeImp(localizer, groupName, typeName, instanceName);
  }

  @Override
  public LocalizerField createLocalizerField(LocalizerType localizerType, String fieldName)
      throws LocalizerException {
    guardNamingConvention("fieldName", fieldName);
    return new LocalizerFieldImp(localizerType, fieldName);
  }

  @Override
  public void addLocalizerBundle(Localizer localizer, LocalizerBundle localizerBundle) {
    // TODO Auto-generated method stub

  }
}
