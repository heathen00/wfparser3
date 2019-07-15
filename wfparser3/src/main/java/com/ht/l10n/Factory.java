package com.ht.l10n;

import java.util.Locale;

public interface Factory {
  static Factory createFactory() {
    return FactoryInternal.createFactoryInternal();
  }

  Localizer createLocalizer(Locale locale);

  LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String instanceName) throws LocalizerException;

  LocalizerField createLocalizerField(LocalizerType localizerType, String fieldName)
      throws LocalizerException;

  // TODO move to internal
  Localizer createUndefinedLocalizer();
}
