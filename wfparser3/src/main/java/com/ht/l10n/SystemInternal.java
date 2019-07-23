package com.ht.l10n;

interface SystemInternal extends System {
  static SystemInternal getSystemInternal() {
    return SystemInternalImp.getSystemInternalImpSingleton();
  }

  FactoryInternal getFactoryInternal();
}
