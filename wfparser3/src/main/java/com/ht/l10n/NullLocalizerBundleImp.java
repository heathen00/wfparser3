package com.ht.l10n;

import java.util.Locale;

class NullLocalizerBundleImp implements LocalizerBundle, NullObject {

  @Override
  public String getBundleName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getFormattedString(LocalizerField localizerField, Object... parameters) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Locale getResolvedLocale() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Locale getTargetLocale() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getUnformattedString(LocalizerField localizerField) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isNull() {
    return true;
  }
}
