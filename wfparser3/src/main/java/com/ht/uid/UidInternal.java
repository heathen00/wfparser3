package com.ht.uid;

interface UidInternal<T> extends UID<T> {
  T getComponent();
}
