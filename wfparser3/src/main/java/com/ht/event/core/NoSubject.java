package com.ht.event.core;

final class NoSubject extends Subject {
  private final String description = "the 'no subject' subject";

  @Override
  public int compareTo(Subject o) {
    return -1;
  }

  @Override
  public int hashCode() {
    return description.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return false;
  }

  @Override
  public boolean isDefined() {
    return false;
  }

  @Override
  public String toString() {
    return "NoSubject [description=" + description + "]";
  }
}

