package com.ht.l10n;

import java.util.Locale;

final class UndefinedLocalizerBundle implements LocalizerBundle, NullObject {
  private static final String INTERNAL_UNDEFINED = "__UNDEFINED__";
  private static final String EXTERNAL_UNDEFINED = "UNDEFINED";
  private static final String UNDEFINED_LANGUAGE = "xx";
  private static final String UNKNOWN_REGION = "ZZ";
  private static final Locale UNKNOWN_LOCALE =
      new Locale.Builder().setLanguage(UNDEFINED_LANGUAGE).setRegion(UNKNOWN_REGION).build();

  @Override
  public String getResourceBundleName() {
    return INTERNAL_UNDEFINED;
  }

  @Override
  public String getFormattedString(LocalizerField localizerField, Object... parameters) {
    return EXTERNAL_UNDEFINED;
  }

  @Override
  public Locale getResolvedLocale() {
    return UNKNOWN_LOCALE;
  }

  @Override
  public Locale getTargetLocale() {
    return UNKNOWN_LOCALE;
  }

  @Override
  public String getUnformattedString(LocalizerField localizerField) {
    return EXTERNAL_UNDEFINED;
  }

  @Override
  public boolean isNull() {
    return false;
  }
}
