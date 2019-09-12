package com.ht.event.core;

public interface Event extends NaturalOrder<Event> {
  EventDescription getEventDescription();

  Channel getChannel();

  String getFamily();

  String getName();

  String getFullyQualifiedName();

  Subject getSubject();
}
