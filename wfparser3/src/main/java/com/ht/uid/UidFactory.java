package com.ht.uid;

public interface UidFactory {
  <T> UID<T> createUid(String key, T component);
}
