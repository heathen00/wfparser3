package com.nickmlanglois.uid;

final class UidInternalImp<T> implements UidInternal<T> {
  private final T component;
  private final String key;

  UidInternalImp(String key, T component) {
    this.component = component;
    this.key = key;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public T getComponent() {
    return component;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    @SuppressWarnings("rawtypes")
    UidInternalImp other = (UidInternalImp) obj;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    return true;
  }

  @Override
  public int compareTo(Uid<T> o) {
    return key.compareTo(o.getKey());
  }

  @Override
  public String toString() {
    return "UIDImp [component=" + component + ", key=" + key + "]";
  }
}
