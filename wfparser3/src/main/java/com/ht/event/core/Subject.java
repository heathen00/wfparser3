package com.ht.event.core;

public abstract class Subject implements SubjectPublished<Subject> {

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object obj);

  @Override
  public abstract int compareTo(Subject o);
}
