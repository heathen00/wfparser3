package com.ht.uid;

interface UidFactoryInternal extends UidFactory {
  static UidFactoryInternal createUidFactoryInternal() {
    return new UidFactoryInternalImp();
  }
}
