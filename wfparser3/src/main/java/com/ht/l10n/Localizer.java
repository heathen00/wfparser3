package com.ht.l10n;

import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;

import com.ht.common.UID;
import com.ht.common.UniqueComponent;

public interface Localizer extends UniqueComponent<Localizer>, DefinedObject {
  Locale getLocale();

  void setLocale(Locale locale) throws LocalizerException;

  SortedSet<LocalizerBundle> getLocalizerBundleSet();

  LocalizerType getLocalizerType(UID<LocalizerType> typeUid);

  Set<UID<LocalizerType>> getLocalizerTypeKeySet();

  LocalizerField getLocalizerField(UID<LocalizerField> fieldUid);

  Set<UID<LocalizerField>> getLocalizerFieldKeySet();

  String getName();
}
