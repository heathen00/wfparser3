package com.ht.event.core;

public final class EventImp implements Event {
  private final EventFactoryInternal eventFactoryInternal;
  private final Channel eventChannel;
  private final String eventFamily;
  private final String eventName;

  EventImp(EventFactoryInternal eventFactoryInternal, Channel eventChannel, String eventFamily,
      String eventName) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.eventChannel = eventChannel;
    this.eventFamily = eventFamily;
    this.eventName = eventName;
  }

  @Override
  public Channel getChannel() {
    return eventChannel;
  }

  @Override
  public String getFamily() {
    return eventFamily;
  }

  @Override
  public String getName() {
    return eventName;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", eventChannel.getName(), getFamily(), getName());
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getFullyQualifiedName().hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    EventImp other = (EventImp) obj;
    if (!getFullyQualifiedName().equals(other.getFullyQualifiedName())) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Event o) {
    return getFullyQualifiedName().compareTo(o.getFullyQualifiedName());
  }
}
