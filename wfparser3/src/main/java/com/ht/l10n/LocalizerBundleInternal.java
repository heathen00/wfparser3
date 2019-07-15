package com.ht.l10n;

import java.util.Locale;

interface LocalizerBundleInternal extends LocalizerBundle, DefinedObject {
  String getFormattedString(LocalizerField localizerField, Object... parameters)
      throws LocalizerException;

  String getUnformattedString(LocalizerField localizerField) throws LocalizerException;

  void loadL10nResource(Locale locale) throws LocalizerException;
}
