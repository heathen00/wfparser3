package com.ht.l10n;

import com.ht.uid.UID;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

final class LocalizerInternalImp implements LocalizerInternal {
  private final String name;
  private Locale locale;
  private final LocalizerFactoryInternal factoryInternal;
  private final SortedSet<LocalizerBundle> localizerBundleSet;
  private final Map<UID<LocalizerType>, LocalizerTypeInternal> localizerTypeMap;
  private final UID<Localizer> localizerUid;

  public LocalizerInternalImp(LocalizerFactoryInternal factoryInternal, String name, Locale locale) {
    this.factoryInternal = factoryInternal;
    this.name = name;
    this.locale = locale;
    localizerUid = UID.createUid(name, this);
    localizerBundleSet = new TreeSet<>();
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
    for (LocalizerBundle localizerBundle : localizerBundleSet) {
      ((LocalizerBundleInternal) localizerBundle).loadL10nResource(this.locale);
    }
  }

  @Override
  public LocalizerTypeInternal getLocalizerTypeInternal(UID<LocalizerType> typeUid) {
    if (null == typeUid) {
      throw new NullPointerException("typeUid cannot be null");
    }
    LocalizerTypeInternal localizerType = localizerTypeMap.get(typeUid);
    if (null == localizerType) {
      localizerType = factoryInternal.createUndefinedLocalizer().getLocalizerTypeInternal(null);
    }
    return localizerType;
  }

  @Override
  public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
    return getLocalizerTypeInternal(typeUid);
  }

  @Override
  public Set<UID<LocalizerType>> getLocalizerTypeUidSet() {
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
  public Set<UID<LocalizerField>> getLocalizerFieldUidSet() {
    Set<UID<LocalizerField>> localizerFieldUidSet = new HashSet<>();
    for (UID<LocalizerType> localizerTypeUid : localizerTypeMap.keySet()) {
      localizerFieldUidSet.addAll(localizerTypeMap.get(localizerTypeUid).getLocalizerFieldUidSet());
    }
    return Collections.unmodifiableSet(localizerFieldUidSet);
  }

  @Override
  public SortedSet<LocalizerBundle> getLocalizerBundleSet() {
    return Collections.unmodifiableSortedSet(localizerBundleSet);
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

  @Override
  public String getName() {
    return name;
  }

  @Override
  public UID<Localizer> getUid() {
    return localizerUid;
  }
}
