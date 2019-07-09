package com.ht.l10n;

import java.util.Locale;

public interface LocalizerBundle {
  String getResourceBundleName();

  // TODO move to internal
  String getFormattedString(LocalizerField localizerField, Object... parameters)
      throws LocalizerException;

  Locale getResolvedLocale();

  Locale getTargetLocale();

  // TODO move to internal
  String getUnformattedString(LocalizerField localizerField) throws LocalizerException;
}
