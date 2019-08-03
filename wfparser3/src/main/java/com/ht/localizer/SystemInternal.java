package com.ht.localizer;

interface SystemInternal extends System {
  static SystemInternal getSystemInternal() {
    return SystemInternalImp.getSystemInternalImpSingleton();
  }

  LocalizerFactoryInternal getFactoryInternal();
}
