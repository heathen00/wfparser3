package com.ht.localizer;

import com.ht.uid.UID;

import java.util.Set;

public interface LocalizerSystem {
  static LocalizerSystem getSystem() {
    return LocalizerSystemInternal.getSystemInternal();
  }

  LocalizerFactory getLocalizerFactory();

  Set<UID<Localizer>> getLocalizerUidSet();

  Localizer getLocalizer(UID<Localizer> localizerUid);

  void resetAll();
}
