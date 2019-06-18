package com.ht.l10n;

import java.util.Locale;

public interface Factory {
  static Factory createFactory() {
    return new FactoryImp();
  }

  Localizer createLocalizer(Locale locale);

  LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerBundle createRootLocaleLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerBundle createUndefinedLocalizerBundle();

  LocalizerBundle createCompositeLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;
}
