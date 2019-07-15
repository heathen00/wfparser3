package com.ht.l10n;

import java.util.Locale;
import java.util.ResourceBundle;

interface FactoryInternal extends Factory {
  static FactoryInternal createFactoryInternal() {
    return FactoryInternalImp.getFactorySingleton();
  }

  LocalizerBundleInternal createTargetLocalizerBundle(Localizer localizer,
      String resourceBundleName) throws LocalizerException;

  LocalizerBundleInternal createRootLocaleLocalizerBundle(Localizer localizer,
      String resourceBundleName) throws LocalizerException;

  LocalizerBundleInternal createUndefinedLocalizerBundle();

  Localizer createUndefinedLocalizer();

  ResourceBundle createResourceBundleForLocalizerBundle(String resourceBundleName,
      Locale targetLocale) throws LocalizerException;
}
