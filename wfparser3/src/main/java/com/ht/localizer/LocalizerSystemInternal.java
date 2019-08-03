package com.ht.localizer;

interface LocalizerSystemInternal extends LocalizerSystem {
  static LocalizerSystemInternal getSystemInternal() {
    return LocalizerSystemInternalImp.getLocalizerSystemInternalImpSingleton();
  }

  LocalizerFactoryInternal getLocalizerFactoryInternal();
}
