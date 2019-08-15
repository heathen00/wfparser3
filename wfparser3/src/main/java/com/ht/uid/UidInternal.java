package com.ht.uid;

interface UidInternal<T> extends Uid<T> {
  T getComponent();
}
