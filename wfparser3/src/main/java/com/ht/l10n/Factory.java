package com.ht.l10n;

import java.util.Locale;

public interface Factory {
  static Factory createFactory() {
    return new FactoryImp();
  }

  Localizer createLocalizer(Locale locale);

  LocalizerBundle createTargetLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerBundle createRootLocaleLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerBundle createUndefinedLocalizerBundle();

  LocalizerBundle createCompositeLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  void addLocalizerBundle(Localizer localizer, LocalizerBundle localizerBundle);

  LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String instanceName) throws LocalizerException;

  LocalizerField createLocalizerField(LocalizerType localizerType, String fieldName)
      throws LocalizerException;
}
