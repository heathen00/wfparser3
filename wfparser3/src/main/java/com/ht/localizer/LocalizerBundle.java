package com.ht.localizer;

import java.util.Locale;

public interface LocalizerBundle extends Comparable<LocalizerBundle>, DefinedObject {
  String getResourceBundleName();

  Locale getResolvedLocale();

  Locale getTargetLocale();
}
