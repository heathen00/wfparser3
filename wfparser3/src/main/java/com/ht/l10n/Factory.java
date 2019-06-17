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

  LocalizerBundle createCompoundLocalizerBundle(Localizer localizer,
      String expectedResourceBundleName, boolean useRootLocale,
      boolean throwExceptionWhenL10nStringDataMissing) throws LocalizerException;
}
