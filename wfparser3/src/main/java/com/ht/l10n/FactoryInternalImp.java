package com.ht.l10n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class FactoryInternalImp implements FactoryInternal {
  private static final FactoryInternalImp FACTORY_SINGLETON = new FactoryInternalImp();

  static FactoryInternalImp getFactorySingleton() {
    return FACTORY_SINGLETON;
  }

  private final LocalizerInternal undefinedLocalizer;

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
    if (null == localizer) {
      throw new NullPointerException("localizer constructor parameter cannot be null");
    }
    LocalizerInternal localizerInternal = null;
    if (localizer instanceof LocalizerInternal) {
      localizerInternal = (LocalizerInternal) localizer;
    } else {
      throw new LocalizerException("unknown Localizer implementation");
    }
    LocalizerBundleInternal targetLocalizerBundle =
        createTargetLocalizerBundle(localizerInternal, resourceBundleName);
    LocalizerBundleInternal rootLocalizerBundle =
        createRootLocaleLocalizerBundle(localizerInternal, resourceBundleName);
    LocalizerBundleInternal undefinedLocalizerBundle = createUndefinedLocalizerBundle();
    CompositeLocalizerBundleImp compositeLocalizerBundleImp = new CompositeLocalizerBundleImp(
        targetLocalizerBundle, rootLocalizerBundle, undefinedLocalizerBundle);
    return (LocalizerBundle) localizerInternal
        .addLocalizerBundleInternal(compositeLocalizerBundleImp);
  }

  @Override
  public LocalizerInternal createLocalizerInternal(Locale locale) {
    if (null == locale) {
      throw new NullPointerException("locale constructor parameter cannot be null");
    }
    return new LocalizerInternalImp(this, locale);
  }

  @Override
  public Localizer createLocalizer(Locale locale) {
    return createLocalizerInternal(locale);
  }

  @Override
  public LocalizerBundleInternal createTargetLocalizerBundle(LocalizerInternal localizerInternal,
      String resourceBundleName) throws LocalizerException {
    createLocalizerBundleGuard(localizerInternal, resourceBundleName);
    ResourceBundle resourceBundle =
        createResourceBundleForLocalizerBundle(resourceBundleName, localizerInternal.getLocale());
    return new LocalizerBundleInternalImp(this, localizerInternal, resourceBundle);
  }

  @Override
  public LocalizerBundleInternal createRootLocaleLocalizerBundle(
      LocalizerInternal localizerInternal, String resourceBundleName) throws LocalizerException {
    createLocalizerBundleGuard(localizerInternal, resourceBundleName);
    ResourceBundle resourceBundle = null;
    try {
      resourceBundle = createResourceBundleForLocalizerBundle(resourceBundleName, Locale.ROOT);
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return new LocalizerBundleInternalImp(this, localizerInternal, resourceBundle);
  }

  @Override
  public LocalizerBundleInternal createUndefinedLocalizerBundle() {
    return (LocalizerBundleInternal) undefinedLocalizer.getLocalizerBundleSet().iterator().next();
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
    LocalizerInternal localizerInternal = null;
    if (localizer instanceof LocalizerInternal) {
      localizerInternal = (LocalizerInternal) localizer;
    } else {
      throw new LocalizerException("unknown Localizer implementation");
    }
    LocalizerTypeInternal localizerTypeInternal =
        new LocalizerTypeInternalImp(this, localizerInternal, groupName, typeName, instanceName);
    return localizerInternal.addLocalizerTypeInternal(localizerTypeInternal);
  }

  @Override
  public LocalizerField createLocalizerField(LocalizerType localizerType, String fieldName)
      throws LocalizerException {
    if (null == localizerType) {
      throw new NullPointerException("localizerType constructor parameter cannot be nullF");
    }
    guardNamingConvention("fieldName", fieldName);
    LocalizerTypeInternal localizerTypeInternal = null;
    if (localizerType instanceof LocalizerTypeInternal) {
      localizerTypeInternal = (LocalizerTypeInternal) localizerType;
    } else {
      throw new LocalizerException("unknown LocalizerType implementation");
    }
    LocalizerFieldInternal newLocalizerFieldInternal =
        new LocalizerFieldInternalImp((LocalizerTypeInternal) localizerType, fieldName);
    LocalizerFieldInternal existingLocalizerFieldInternal =
        localizerTypeInternal.getLocalizerFieldInternal(newLocalizerFieldInternal.getUid());
    if (existingLocalizerFieldInternal.isDefined()) {
      newLocalizerFieldInternal = existingLocalizerFieldInternal;
    }
    return localizerTypeInternal.addLocalizerFieldInternal(newLocalizerFieldInternal);
  }

  @Override
  public LocalizerInternal createUndefinedLocalizer() {
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
