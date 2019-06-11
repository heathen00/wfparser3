package com.ht.l10n;

import java.util.Locale;
import java.util.Set;
import com.ht.common.UID;

final class LocalizerImp implements Localizer {
  private Locale locale;

  public LocalizerImp(Locale locale) {
    this.locale = locale;
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
  public UID<LocalizerBundle> addLocalizerBundle(LocalizerBundle localizerBundle) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LocalizerBundle getLocalizerBundle(UID<LocalizerBundle> bundleUid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<UID<LocalizerBundle>> getLocalizerBundleKeySet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LocalizerType createLocalizerType(LocalizerType localizerType) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<UID<LocalizerType>> getLocalizerTypeKeySet() {
    // TODO Auto-generated method stub
    return null;
  }
}
