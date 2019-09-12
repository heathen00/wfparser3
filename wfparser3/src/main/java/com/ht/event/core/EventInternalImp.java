package com.ht.event.core;

public final class EventInternalImp extends NaturalOrderBase<Event> implements EventInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final EventDescription eventDescription;
  private final Subject subject;

  EventInternalImp(EventFactoryInternal eventFactoryInternal, EventDescription eventDescription,
      Subject subject) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.eventDescription = eventDescription;
    this.subject = subject;
  }

  EventInternalImp(EventFactoryInternal eventFactoryInternal, Event event, Subject subject) {
    this(eventFactoryInternal, event.getEventDescription(), subject);
  }

  @Override
  public EventDescription getEventDescription() {
    return eventDescription;
  }

  @Override
  public Channel getChannel() {
    return eventDescription.getChannel();
  }

  @Override
  public String getFamily() {
    return eventDescription.getFamily();
  }

  @Override
  public String getName() {
    return eventDescription.getName();
  }

  @Override
  public String getFullyQualifiedName() {
    return eventDescription.getFullyQualifiedName();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getEventDescription().hashCode() + getSubject().hashCode();
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
    if (!getEventDescription().equals(other.getEventDescription())) {
      return false;
    }
    if (!getSubject().equals(other.getSubject())) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Event o) {
    int compareTo = getEventDescription().compareTo(o.getEventDescription());
    if (0 == compareTo) {
      compareTo = getSubject().compareTo(o.getSubject());
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "EventInternalImp [getEventDescription()=" + getEventDescription() + ", getSubject()="
        + getSubject() + "]";
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
