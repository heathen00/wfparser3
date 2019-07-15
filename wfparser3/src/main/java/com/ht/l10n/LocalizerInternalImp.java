package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

final class LocalizerInternalImp implements LocalizerInternal {
  private Locale locale;
  private final FactoryInternal factoryInternal;
  private final Set<LocalizerBundleInternal> localizerBundleSet;
  private final Map<UID<LocalizerType>, LocalizerTypeInternal> localizerTypeMap;

  public LocalizerInternalImp(FactoryInternal factoryInternal, Locale locale) {
    this.factoryInternal = factoryInternal;
    this.locale = locale;
    localizerBundleSet = new HashSet<>();
    localizerTypeMap = new HashMap<>();
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(Locale locale) throws LocalizerException {
    if (null == locale) {
      throw new NullPointerException("locale cannot be null");
    }
    this.locale = locale;
    for (LocalizerBundleInternal localizerBundleInternal : localizerBundleSet) {
      localizerBundleInternal.loadL10nResource(this.locale);
    }
  }

  @Override
  public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
    if (null == typeUid) {
      throw new NullPointerException("typeUid cannot be null");
    }
    LocalizerType localizerType = localizerTypeMap.get(typeUid);
    if (null == localizerType) {
      localizerType = factoryInternal.createUndefinedLocalizer().getLocalizerType(null);
    }
    return localizerType;
  }

  @Override
  public Set<UID<LocalizerType>> getLocalizerTypeKeySet() {
    return Collections.unmodifiableSet(localizerTypeMap.keySet());
  }

  @Override
  public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
    if (fieldUid == null) {
      throw new NullPointerException("fieldUid cannot be null");
    }
    LocalizerField localizerField = null;
    for (UID<LocalizerType> localizerTypeKey : localizerTypeMap.keySet()) {
      localizerField = localizerTypeMap.get(localizerTypeKey).getLocalizerField(fieldUid);
      if (localizerField.isDefined()) {
        break;
      }
    }
    if (null == localizerField) {
      localizerField = factoryInternal.createUndefinedLocalizer().getLocalizerField(null);
    }
    return localizerField;
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
    Set<UID<LocalizerField>> localizerFieldUidSet = new HashSet<>();
    for (UID<LocalizerType> localizerTypeUid : localizerTypeMap.keySet()) {
      localizerFieldUidSet.addAll(localizerTypeMap.get(localizerTypeUid).getLocalizerFieldKeySet());
    }
    return Collections.unmodifiableSet(localizerFieldUidSet);
  }

  @Override
  public Set<LocalizerBundle> getLocalizerBundleSet() {
    return Collections.unmodifiableSet(localizerBundleSet);
  }

  @Override
  public boolean isDefined() {
    return true;
  }

  @Override
  public LocalizerBundleInternal addLocalizerBundleInternal(
      LocalizerBundleInternal localizerBundleInternal) {
    localizerBundleSet.add(localizerBundleInternal);
    return localizerBundleInternal;
  }

  @Override
  public LocalizerTypeInternal addLocalizerTypeInternal(
      LocalizerTypeInternal localizerTypeInternal) {
    localizerTypeMap.put(localizerTypeInternal.getUid(), localizerTypeInternal);
    return localizerTypeInternal;
  }
}