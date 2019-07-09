package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

final class LocalizerInternalImp implements LocalizerInternal {
  private Locale locale;
  private final Set<LocalizerBundle> localizerBundleSet;

  public LocalizerInternalImp(Locale locale) {
    this.locale = locale;
    localizerBundleSet = new HashSet<>();
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(Locale locale) {
    if (null == locale) {
      throw new NullPointerException("locale cannot be null");
    }
    this.locale = locale;
  }

  @Override
  public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
    if (null == typeUid) {
      throw new NullPointerException("typeUid cannot be null");
    }
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<UID<LocalizerType>> getLocalizerTypeKeySet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
    // TODO Auto-generated method stub
    return null;
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
}
