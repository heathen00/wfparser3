package com.ht.event.core;

final class EventDescriptionImp extends NaturalOrderBase<EventDescription>
    implements EventDescription {
  private final EventFactoryInternal eventFactoryInternal;
  private final Channel channel;
  private final String family;
  private final String name;

  EventDescriptionImp(EventFactoryInternal eventFactoryInternal, Channel channel, String family,
      String name) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channel = channel;
    this.family = family;
    this.name = name;
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
    if (!Event.class.isInstance(obj)) {
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
    return getFullyQualifiedName().compareTo(o.getFullyQualifiedName());
  }

  @Override
  public String toString() {
    return "EventDescriptionImp [getFullyQualifiedName()=" + getFullyQualifiedName() + "]";
  }
}

