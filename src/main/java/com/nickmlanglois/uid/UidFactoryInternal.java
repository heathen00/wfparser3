package com.nickmlanglois.uid;

interface UidFactoryInternal extends UidFactory {
  static UidFactoryInternal createUidFactoryInternal() {
    return new UidFactoryInternalImp();
  }
}
