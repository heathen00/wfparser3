package com.ht.l10n;

import java.util.Locale;
import java.util.Set;
import com.ht.common.UID;

public interface Localizer {
  Locale getLocale();

  void setLocale(Locale locale);

  Set<LocalizerBundle> getLocalizerBundleSet();

  LocalizerType getLocalizerType(UID<LocalizerType> typeUid);

  Set<UID<LocalizerType>> getLocalizerTypeKeySet();

  LocalizerField getLocalizerField(UID<LocalizerField> fieldUid);

  Set<UID<LocalizerField>> getLocalizerFieldKeySet();
}
