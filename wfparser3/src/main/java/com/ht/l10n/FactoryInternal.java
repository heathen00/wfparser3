package com.ht.l10n;

import java.util.Locale;
import java.util.ResourceBundle;

interface FactoryInternal extends Factory {
  static FactoryInternal createFactoryInternal() {
    return FactoryInternalImp.getFactorySingleton();
  }

  LocalizerBundle createTargetLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerBundle createRootLocaleLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerBundle createUndefinedLocalizerBundle();

  ResourceBundle createResourceBundleForLocalizerBundle(String resourceBundleName,
      Locale targetLocale) throws LocalizerException;
}
