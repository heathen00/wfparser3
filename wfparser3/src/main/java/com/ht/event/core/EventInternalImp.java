package com.ht.event.core;

public final class EventInternalImp extends NaturalOrderBase<Event> implements EventInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final Channel channel;
  private final String family;
  private final String name;
  private final Subject subject;

  EventInternalImp(EventFactoryInternal eventFactoryInternal, Channel channel,
      String family, String name, Subject subject) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channel = channel;
    this.family = family;
    this.name = name;
    this.subject = subject;
  }

  EventInternalImp(EventFactoryInternalCreatorImp eventFactoryInternalCreatorImp, Event event,
      Subject subject) {
    this(eventFactoryInternalCreatorImp, event.getChannel(), event.getFamily(), event.getName(),
        subject);
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
    return "EventInternalImp [getFullyQualifiedName()=" + getFullyQualifiedName()
        + ", getSubject()=" + getSubject() + "]";
  }

  @Override
  public Subject getSubject() {
    return subject;
  }

  @Override
  public void setSubject(Subject subject) {
    throw new UnsupportedOperationException("cannot set subject in no subject event");
  }
}
