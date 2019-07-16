package com.ht.l10n;

import java.util.Locale;

public interface LocalizerBundle extends DefinedObject {
  String getResourceBundleName();

  Locale getResolvedLocale();

  Locale getTargetLocale();
}
