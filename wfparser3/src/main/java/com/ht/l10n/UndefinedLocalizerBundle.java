package com.ht.l10n;

import java.util.Locale;

final class UndefinedLocalizerBundle implements LocalizerBundle, NullObject {
  private static final String UNDEFINED_BUNDLE_NAME = "__UNDEFINED__";

  @Override
  public String getBundleName() {
    return UNDEFINED_BUNDLE_NAME;
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
    return false;
  }
}
