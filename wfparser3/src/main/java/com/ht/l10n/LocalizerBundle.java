package com.ht.l10n;

import java.util.Locale;

public interface LocalizerBundle extends Comparable<LocalizerBundle>, DefinedObject {
  String getResourceBundleName();

  Locale getResolvedLocale();

  Locale getTargetLocale();
}
