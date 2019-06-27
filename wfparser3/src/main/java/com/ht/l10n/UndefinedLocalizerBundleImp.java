package com.ht.l10n;

import java.util.Locale;

final class UndefinedLocalizerBundleImp implements LocalizerBundleInternal {
  private static final String INTERNAL_UNDEFINED = "__UNDEFINED__";
  private static final String EXTERNAL_UNDEFINED = "UNDEFINED";

  private final UndefinedLocalizerImp undefinedLocalizer;

  UndefinedLocalizerBundleImp(UndefinedLocalizerImp undefinedLocalizer) {
    this.undefinedLocalizer = undefinedLocalizer;
  }

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
    return undefinedLocalizer.getLocale();
  }

  @Override
  public Locale getTargetLocale() {
    return undefinedLocalizer.getLocale();
  }

  @Override
  public String getUnformattedString(LocalizerField localizerField) {
    return EXTERNAL_UNDEFINED;
  }

  @Override
  public boolean isDefined() {
    return false;
  }
}
