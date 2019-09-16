package com.nickmlanglois.localizer;

import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import com.nickmlanglois.uid.Uid;
import com.nickmlanglois.uid.UniqueComponent;

public interface Localizer extends UniqueComponent<Localizer>, DefinedObject {
  Locale getLocale();

  void setLocale(Locale locale) throws LocalizerException;

  SortedSet<LocalizerBundle> getLocalizerBundleSet();

  LocalizerType getLocalizerType(Uid<LocalizerType> typeUid);

  Set<Uid<LocalizerType>> getLocalizerTypeUidSet();

  LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid);

  Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet();

  String getName();
}
