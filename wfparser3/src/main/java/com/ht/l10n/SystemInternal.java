package com.ht.l10n;

interface SystemInternal extends System {
  static SystemInternal getSystemInternal() {
    return SystemInternalImp.getSystemImpSingleton();
  }

  FactoryInternal getFactoryInternal();
}
