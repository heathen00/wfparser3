package com.ht.event.core;

public abstract class Subject implements Comparable<Subject> {

  @Override
  public abstract int compareTo(Subject o);

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object obj);

  public abstract boolean isDefined();
}
