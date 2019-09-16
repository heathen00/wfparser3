package com.nickmlanglois.uid;

interface UidInternal<T> extends Uid<T> {
  T getComponent();
}
