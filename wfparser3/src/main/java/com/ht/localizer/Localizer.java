package com.ht.localizer;

import com.ht.uid.UID;
import com.ht.uid.UniqueComponent;

import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;

public interface Localizer extends UniqueComponent<Localizer>, DefinedObject {
  Locale getLocale();

  void setLocale(Locale locale) throws LocalizerException;

  SortedSet<LocalizerBundle> getLocalizerBundleSet();

  LocalizerType getLocalizerType(UID<LocalizerType> typeUid);

  Set<UID<LocalizerType>> getLocalizerTypeUidSet();

  LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid);

  Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet();

  String getName();
}
