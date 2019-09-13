package com.ht.event.core;

final class EventDescriptionNaturalOrderImp extends NaturalOrderBase<EventDescription>
    implements NaturalOrder<EventDescription> {
  private final EventDescription eventDescription;

  EventDescriptionNaturalOrderImp(EventDescription eventDescription) {
    this.eventDescription = eventDescription;
  }

  EventDescription getEventDescription() {
    return eventDescription;
  }

  String getFullyQualifiedName() {
    return String.join(".", eventDescription.getChannel().getName(), eventDescription.getFamily(),
        eventDescription.getName());
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
    if (!EventDescription.class.isInstance(obj)) {
      return false;
    }
    EventDescription other = (EventDescription) obj;
    if (!getFullyQualifiedName().equals(other.getFullyQualifiedName())) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(EventDescription o) {
    if (null == o) {
      throw new NullPointerException("o cannot be null");
    }
    return getFullyQualifiedName().compareTo(o.getFullyQualifiedName());
  }
}
