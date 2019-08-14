package com.ht.uid;

public interface UidFactory extends CanReset {
  static UidFactory createUidFactory() {
    return UidFactoryInternal.createUidFactoryInternal();
  }

  <T> UID<T> createUid(String key, T component);
}
