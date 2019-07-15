package com.ht.l10n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class FactoryInternalImp implements FactoryInternal {
  private static final FactoryInternalImp FACTORY_SINGLETON = new FactoryInternalImp();

  static FactoryInternalImp getFactorySingleton() {
    return FACTORY_SINGLETON;
  }

  private final Localizer undefinedLocalizer;

  private FactoryInternalImp() {
    undefinedLocalizer = new UndefinedLocalizerInternalImp();
  }

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
  public LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException {
    LocalizerBundle targetLocalizerBundle =
        createTargetLocalizerBundle(localizer, resourceBundleName);
    LocalizerBundle rootLocalizerBundle =
        createRootLocaleLocalizerBundle(localizer, resourceBundleName);
    LocalizerBundle undefinedLocalizerBundle = createUndefinedLocalizerBundle();
    CompositeLocalizerBundleImp compositeLocalizerBundleImp = new CompositeLocalizerBundleImp(
        localizer, targetLocalizerBundle, rootLocalizerBundle, undefinedLocalizerBundle);
    LocalizerInternal localizerInternal = (LocalizerInternal) localizer;
    return (LocalizerBundle) localizerInternal
        .addLocalizerBundleInternal(compositeLocalizerBundleImp);
  }

  @Override
  public Localizer createLocalizer(Locale locale) {
    if (null == locale) {
      throw new NullPointerException("locale constructor parameter cannot be null");
    }
    return new LocalizerInternalImp(this, locale);
  }

  @Override
  public LocalizerBundle createTargetLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException {
    createLocalizerBundleGuard(localizer, resourceBundleName);
    ResourceBundle resourceBundle =
        createResourceBundleForLocalizerBundle(resourceBundleName, localizer.getLocale());
    return new LocalizerBundleInternalImp(this, localizer, resourceBundle);
  }

  @Override
  public LocalizerBundle createRootLocaleLocalizerBundle(Localizer localizer,
      String resourceBundleName) throws LocalizerException {
    createLocalizerBundleGuard(localizer, resourceBundleName);
    ResourceBundle resourceBundle = null;
    try {
      resourceBundle = createResourceBundleForLocalizerBundle(resourceBundleName, Locale.ROOT);
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return new LocalizerBundleInternalImp(this, localizer, resourceBundle);
  }

  @Override
  public LocalizerBundle createUndefinedLocalizerBundle() {
    return undefinedLocalizer.getLocalizerBundleSet().iterator().next();
  }

  private void createLocalizerBundleGuard(Localizer localizer, String resourceBundleName) {
    if (null == localizer) {
      throw new NullPointerException("localizer constructor parameter cannot be null");
    }
    if (null == resourceBundleName) {
      throw new NullPointerException("resourceBundleName constructor parameter cannot be null");
    }
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
    LocalizerInternal localizerInternal = (LocalizerInternal) localizer;
    LocalizerTypeInternal localizerTypeInternal =
        new LocalizerTypeInternalImp(this, localizer, groupName, typeName, instanceName);
    return localizerInternal.addLocalizerTypeInternal(localizerTypeInternal);
  }

  @Override
  public LocalizerField createLocalizerField(LocalizerType localizerType, String fieldName)
      throws LocalizerException {
    guardNamingConvention("fieldName", fieldName);
    LocalizerFieldInternal newLocalizerFieldInternal =
        new LocalizerFieldInternalImp(localizerType, fieldName);
    LocalizerFieldInternal existingLocalizerFieldInternal = (LocalizerFieldInternal) localizerType
        .getLocalizerField(newLocalizerFieldInternal.getUid());
    if (existingLocalizerFieldInternal.isDefined()) {
      newLocalizerFieldInternal = existingLocalizerFieldInternal;
    }
    LocalizerTypeInternal localizerTypeInternal = (LocalizerTypeInternal) localizerType;
    localizerTypeInternal.addLocalizerField(newLocalizerFieldInternal);
    return newLocalizerFieldInternal;
  }

  @Override
  public void addLocalizerBundle(Localizer localizer, LocalizerBundle localizerBundle) {
    // TODO Auto-generated method stub
  }

  @Override
  public Localizer createUndefinedLocalizer() {
    return undefinedLocalizer;
  }

  @Override
  public ResourceBundle createResourceBundleForLocalizerBundle(String resourceBundleName,
      Locale targetLocale) throws LocalizerException {
    ResourceBundle resourceBundle = null;
    try {
      resourceBundle = ResourceBundle.getBundle(resourceBundleName, targetLocale,
          ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_PROPERTIES));
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return resourceBundle;
  }
}
