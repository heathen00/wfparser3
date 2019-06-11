package com.ht.l10n;

import java.util.Locale;
import java.util.Set;
import com.ht.common.UID;

public interface Localizer {
  Locale getLocale();

  void setLocale(Locale locale);

  UID<LocalizerBundle> addLocalizerBundle(LocalizerBundle localizerBundle);

  LocalizerBundle getLocalizerBundle(UID<LocalizerBundle> bundleUid);

  Set<UID<LocalizerBundle>> getLocalizerBundleKeySet();

  LocalizerType createLocalizerType(LocalizerType localizerType);

  LocalizerType getLocalizerType(UID<LocalizerType> typeUid);

  Set<UID<LocalizerType>> getLocalizerTypeKeySet();
}
