package com.nickmlanglois.event.core;

abstract class NaturalOrderBase<T> implements NaturalOrder<T> {

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object obj);

  @Override
  public abstract int compareTo(T o);
}
