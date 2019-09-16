package com.nickmlanglois.uid;

public interface UidFactory extends CanReset {
  static UidFactory createUidFactory() {
    return UidFactoryInternal.createUidFactoryInternal();
  }

  <T> Uid<T> createUid(String key, T component);
}
