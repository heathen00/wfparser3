package com.ht.l10n;

import com.ht.common.UID;

import java.util.Set;

public interface System {
  static System getSystem() {
    return SystemInternal.getSystemInternal();
  }

  Factory getFactory();

  Set<UID<Localizer>> getLocalizerKeySet();

  Localizer getLocalizer(UID<Localizer> localizerUid);

  void resetAll();
}
