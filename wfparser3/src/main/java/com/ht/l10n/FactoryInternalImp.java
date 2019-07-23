package com.ht.l10n;

import com.ht.uid.UID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

final class FactoryInternalImp implements FactoryInternal {
  private final Map<UID<Localizer>, LocalizerInternal> localizerInternalMap;
  private final LocalizerInternal undefinedLocalizer;

  FactoryInternalImp() {
    localizerInternalMap = new HashMap<>();
    undefinedLocalizer = new UndefinedLocalizerInternalImp();
  }

  private void guardNotNull(String parameterName, Object parameter) {
    if (null == parameter) {
      throw new NullPointerException(parameterName + " cannot be null");
    }
  }

  private void guardNamingConvention(String constructorParameterName,
      String constructorParameterValue) throws LocalizerException {
    if (!constructorParameterValue.matches("^[a-z0-9][a-z0-9.]+$")
        || constructorParameterValue.endsWith(".")) {
      throw new LocalizerException(constructorParameterName
          + " can only contain the characters: lowercase letters, numbers, and period and cannot start or end with a period");
    }
  }

  @Override
  public LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException {
    guardNotNull("localizer", localizer);
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
  public LocalizerInternal createLocalizerInternal(String name, Locale locale)
      throws LocalizerException {
    guardNotNull("name", name);
    guardNamingConvention("name", name);
    guardNotNull("locale", locale);
    LocalizerInternal localizerInternal = new LocalizerInternalImp(this, name, locale);
    localizerInternalMap.put(localizerInternal.getUid(), localizerInternal);
    return localizerInternal;
  }

  @Override
  public Localizer createLocalizer(String name, Locale locale) throws LocalizerException {
    return createLocalizerInternal(name, locale);
  }

  @Override
  public LocalizerBundleInternal createTargetLocalizerBundle(LocalizerInternal localizerInternal,
      String resourceBundleName) throws LocalizerException {
    guardNotNull("localizerInternal", localizerInternal);
    ResourceBundle resourceBundle =
        createResourceBundleForLocalizerBundle(resourceBundleName, localizerInternal.getLocale());
    return new LocalizerBundleInternalImp(this, localizerInternal, resourceBundle);
  }

  @Override
  public LocalizerBundleInternal createRootLocaleLocalizerBundle(
      LocalizerInternal localizerInternal, String resourceBundleName) throws LocalizerException {
    guardNotNull("localizerInternal", localizerInternal);
    guardNotNull("resourceBundleName", resourceBundleName);
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

  @Override
  public LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String instanceName) throws LocalizerException {
    guardNotNull("localizer", localizer);
    guardNotNull("groupName", groupName);
    guardNamingConvention("groupName", groupName);
    guardNotNull("typeName", typeName);
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
    guardNotNull("localizerType", localizerType);
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

  @Override
  public Set<UID<Localizer>> getLocalizerUidSet() {
    return Collections.unmodifiableSet(localizerInternalMap.keySet());
  }

  @Override
  public Localizer getLocalizer(UID<Localizer> localizerUid) {
    guardNotNull("localizerUid", localizerUid);
    if (null == localizerUid) {
      throw new NullPointerException("localizerUid cannot be null");
    }
    LocalizerInternal localizerInternal = localizerInternalMap.get(localizerUid);
    if (null == localizerInternal) {
      localizerInternal = createUndefinedLocalizer();
    }
    return localizerInternal;
  }

  @Override
  public void resetAll() {
    localizerInternalMap.clear();
  }
}
