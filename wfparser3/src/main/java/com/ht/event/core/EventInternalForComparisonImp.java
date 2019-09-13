package com.ht.event.core;

final class EventInternalForComparisonImp implements Event {
  private EventDescription eventDescription;
  private Subject subject;

  @Override
  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  @Override
  public EventDescription getEventDescription() {
    return eventDescription;
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
    return "EventInternalForComparisonImp [getEventDescription()=" + getEventDescription()
        + ", getSubject()=" + getSubject() + "]";
  }
}
