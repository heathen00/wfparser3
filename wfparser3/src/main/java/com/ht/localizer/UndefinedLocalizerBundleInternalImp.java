package com.ht.localizer;

import java.util.Locale;

final class UndefinedLocalizerBundleInternalImp implements LocalizerBundleInternal {
  private static final String INTERNAL_UNDEFINED = "__UNDEFINED__";
  private static final String EXTERNAL_UNDEFINED = "UNDEFINED";

  private final UndefinedLocalizerInternalImp undefinedLocalizerInternalImp;

  UndefinedLocalizerBundleInternalImp(UndefinedLocalizerInternalImp undefinedLocalizer) {
    this.undefinedLocalizerInternalImp = undefinedLocalizer;
  }

  @Override
  public String getResourceBundleName() {
    return INTERNAL_UNDEFINED;
  }

  @Override
  public String getFormattedString(LocalizerInstance localizerInstance, Object... parameters) {
    return EXTERNAL_UNDEFINED;
  }

  @Override
  public Locale getResolvedLocale() {
    return undefinedLocalizerInternalImp.getLocale();
  }

  @Override
  public Locale getTargetLocale() {
    return undefinedLocalizerInternalImp.getLocale();
  }

  @Override
  public String getUnformattedString(LocalizerInstance localizerInstance) {
    return EXTERNAL_UNDEFINED;
  }

  @Override
  public boolean isDefined() {
    return false;
  }

  @Override
  public void loadL10nResource(Locale locale) {}

  @Override
  public int compareTo(LocalizerBundle o) {
    return getResourceBundleName().compareTo(o.getResourceBundleName());
  }
}
