package com.ht.localizer;

import com.ht.uid.UID;

import java.util.Set;

public interface System {
  static System getSystem() {
    return SystemInternal.getSystemInternal();
  }

  LocalizerFactory getFactory();

  Set<UID<Localizer>> getLocalizerUidSet();

  Localizer getLocalizer(UID<Localizer> localizerUid);

  void resetAll();
}
