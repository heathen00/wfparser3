package com.ht.uid;

public interface UidFactory {
  static UidFactory createUidFactory() {
    return UidFactoryInternal.createUidFactoryInternal();
  }

  <T> UID<T> createUid(String key, T component);
}
