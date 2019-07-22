package com.ht.l10n;

public interface System {
  static System getSystem() {
    return SystemInternal.getSystemInternal();
  }

  Factory getFactory();
}
