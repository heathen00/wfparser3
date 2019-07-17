package com.ht.l10n;

import java.util.Locale;
import java.util.ResourceBundle;

interface FactoryInternal extends Factory {
  static FactoryInternal createFactoryInternal() {
    return FactoryInternalImp.getFactorySingleton();
  }

  LocalizerInternal createLocalizerInternal(Locale locale);

  LocalizerBundleInternal createTargetLocalizerBundle(LocalizerInternal localizer,
      String resourceBundleName) throws LocalizerException;

  LocalizerBundleInternal createRootLocaleLocalizerBundle(LocalizerInternal localizer,
      String resourceBundleName) throws LocalizerException;

  LocalizerBundleInternal createUndefinedLocalizerBundle();

  LocalizerInternal createUndefinedLocalizer();

  ResourceBundle createResourceBundleForLocalizerBundle(String resourceBundleName,
      Locale targetLocale) throws LocalizerException;
}
