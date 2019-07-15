package com.ht.l10n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

final class CompositeLocalizerBundleImp implements LocalizerBundleInternal {
  private final List<LocalizerBundleInternal> localizerBundleInternalList;
  private final int TARGET_BUNDLE = 0;

  public CompositeLocalizerBundleImp(Localizer localizer,
      LocalizerBundleInternal targetLocalizerBundle, LocalizerBundleInternal rootLocalizerBundle,
      LocalizerBundleInternal undefinedLocalizerBundle) {
    localizerBundleInternalList = new ArrayList<>();
    localizerBundleInternalList.add(targetLocalizerBundle);
    localizerBundleInternalList.add(rootLocalizerBundle);
    localizerBundleInternalList.add(undefinedLocalizerBundle);
  }

  @Override
  public String getResourceBundleName() {
    return localizerBundleInternalList.get(TARGET_BUNDLE).getResourceBundleName();
  }

  @Override
  public String getFormattedString(LocalizerField localizerField, Object... parameters) {
    String localizedString = null;
    for (int bundleIndex = TARGET_BUNDLE; localizedString == null; bundleIndex++) {
      try {
        localizedString = localizerBundleInternalList.get(bundleIndex)
            .getFormattedString(localizerField, parameters);
      } catch (LocalizerException le) {
        localizedString = null;
      }
    }
    return localizedString;
  }

  @Override
  public Locale getResolvedLocale() {
    return localizerBundleInternalList.get(TARGET_BUNDLE).getResolvedLocale();
  }

  @Override
  public Locale getTargetLocale() {
    return localizerBundleInternalList.get(TARGET_BUNDLE).getTargetLocale();
  }

  @Override
  public String getUnformattedString(LocalizerField localizerField) {
    String localizedString = null;
    for (int bundleIndex = TARGET_BUNDLE; localizedString == null; bundleIndex++) {
      try {
        localizedString =
            localizerBundleInternalList.get(bundleIndex).getUnformattedString(localizerField);
      } catch (LocalizerException le) {
        localizedString = null;
      }
    }
    return localizedString;
  }

  @Override
  public boolean isDefined() {
    return true;
  }

  @Override
  public void loadL10nResource(Locale locale) throws LocalizerException {
    for (LocalizerBundle localizerBundle : localizerBundleInternalList) {
      LocalizerBundleInternal localizerBundleInternal = (LocalizerBundleInternal) localizerBundle;
      localizerBundleInternal.loadL10nResource(locale);
    }
  }
}
