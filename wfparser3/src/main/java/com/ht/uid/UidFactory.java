package com.ht.uid;

public interface UidFactory extends CanReset {
  static UidFactory createUidFactory() {
    return UidFactoryInternal.createUidFactoryInternal();
  }

  <T> Uid<T> createUid(String key, T component);
}
