package com.ht.event.core;

public interface Event extends NaturalOrder<Event> {
  EventDescription getEventDescription();

  Subject getSubject();
}
