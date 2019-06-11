package com.ht.l10n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

final class CompoundLocalizerBundleImp implements LocalizerBundle {
  private final List<LocalizerBundle> localizerBundleList;
  private final int TARGET_BUNDLE = 0;
  private final int ROOT_BUNDLE = 1;
  private final int UNDEFINED_BUNDLE = 2;
  private final int EXCEPTION_BUNDLE = 3;

  public CompoundLocalizerBundleImp(Localizer localizer, LocalizerBundle targetLocalizerBundle,
      LocalizerBundle rootLocalizerBundle, LocalizerBundle undefinedLocalizerBundle) {
    localizerBundleList = new ArrayList<>();
    localizerBundleList.add(targetLocalizerBundle);
    localizerBundleList.add(rootLocalizerBundle);
    localizerBundleList.add(undefinedLocalizerBundle);
  }

  @Override
  public String getBundleName() {
    return localizerBundleList.get(TARGET_BUNDLE).getBundleName();
  }

  @Override
  public String getFormattedString(LocalizerField localizerField, Object... parameters) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Locale getResolvedLocale() {
    return localizerBundleList.get(TARGET_BUNDLE).getResolvedLocale();
  }

  @Override
  public Locale getTargetLocale() {
    return localizerBundleList.get(TARGET_BUNDLE).getTargetLocale();
  }

  @Override
  public String getUnformattedString(LocalizerField localizerField) {
    // TODO Auto-generated method stub
    return null;
  }

}
