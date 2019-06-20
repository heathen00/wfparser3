package com.ht.l10n;

import java.util.Locale;

public interface LocalizerBundle {
  String getResourceBundleName();

  String getFormattedString(LocalizerField localizerField, Object... parameters)
      throws LocalizerException;

  Locale getResolvedLocale();

  Locale getTargetLocale();

  String getUnformattedString(LocalizerField localizerField) throws LocalizerException;
}
