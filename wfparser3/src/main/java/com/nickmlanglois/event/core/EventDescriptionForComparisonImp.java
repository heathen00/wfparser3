package com.nickmlanglois.event.core;

final class EventDescriptionForComparisonImp implements EventDescription {
  private Channel channel;
  private String family;
  private String name;
  private final EventDescriptionNaturalOrderImp eventDescriptionNaturalOrder;

  EventDescriptionForComparisonImp() {
    eventDescriptionNaturalOrder = new EventDescriptionNaturalOrderImp(this);
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

  public void setChannel(Channel channel) {
    this.channel = channel;
  }

  public void setFamily(String family) {
    this.family = family;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getFullyQualifiedName() {
    return eventDescriptionNaturalOrder.getFullyQualifiedName();
  }

  @Override
  public int hashCode() {
    return eventDescriptionNaturalOrder.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return eventDescriptionNaturalOrder.equals(obj);
  }

  @Override
  public int compareTo(EventDescription o) {
    return eventDescriptionNaturalOrder.compareTo(o);
  }

  @Override
  public String toString() {
    return "EventDescriptionForComparisonImp [getFullyQualifiedName()=" + getFullyQualifiedName()
        + "]";
  }
}

