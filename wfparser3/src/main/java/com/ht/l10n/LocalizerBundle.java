package com.ht.l10n;

import java.util.Locale;

public interface LocalizerBundle {
  String getResourceBundleName();

  Locale getResolvedLocale();

  Locale getTargetLocale();
}
