package com.nickmlanglois.event.core;

public interface Event extends NaturalOrder<Event> {
  EventDescription getEventDescription();

  Subject getSubject();
}
