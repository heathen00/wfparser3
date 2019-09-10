package com.ht.event.core;

public final class EventInternalImp implements EventInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final Channel channel;
  private final String family;
  private final String name;
  private Subject subject;

  EventInternalImp(EventFactoryInternal eventFactoryInternal, Channel eventChannel,
      String eventFamily, String eventName, Subject subject) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channel = eventChannel;
    this.family = eventFamily;
    this.name = eventName;
    this.subject = subject;
  }

  @Override
  public Channel getChannel() {
    return channel;
  }

  @Override
  public String getFamily() {
    return family;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", channel.getName(), getFamily(), getName());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getFullyQualifiedName().hashCode() + getSubject().hashCode();
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
    if (!Event.class.isInstance(obj)) {
      return false;
    }
    Event other = (Event) obj;
    if (!getFullyQualifiedName().equals(other.getFullyQualifiedName())) {
      return false;
    }
    if (!getSubject().equals(other.getSubject())) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Event o) {
    int compareTo = getFullyQualifiedName().compareTo(o.getFullyQualifiedName());
    if (0 == compareTo) {
      compareTo = getSubject().compareTo(o.getSubject());
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "EventImp [getFullyQualifiedName()=" + getFullyQualifiedName() + "]";
  }

  @Override
  public Subject getSubject() {
    return subject;
  }

  @Override
  public void setSubject(Subject subject) {
    this.subject = subject;
  }
}
