package com.ht.uid;

interface UidFactoryInternal extends UidFactory, CanReset {
  static UidFactoryInternal createUidFactoryInternal() {
    return new UidFactoryInternalImp();
  }
}
