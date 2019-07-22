package com.ht.l10n;

import java.util.Locale;

public interface Factory {
  Localizer createLocalizer(String name, Locale locale) throws LocalizerException;

  LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String instanceName) throws LocalizerException;

  LocalizerField createLocalizerField(LocalizerType localizerType, String fieldName)
      throws LocalizerException;
}
