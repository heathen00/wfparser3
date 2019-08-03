package com.ht.uid;

interface UidFactoryInternal extends UidFactory, CanReset {
  static UidFactoryInternal getUidFactoryInternal() {
    return UidFactoryInternalImp.getUidFactory();
  }
}
