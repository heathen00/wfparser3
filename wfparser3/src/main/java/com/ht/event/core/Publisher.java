package com.ht.event.core;

public interface Publisher {
  Channel getChannel();

  void publish(EventDescription eventDescription);

  void unpublish(EventDescription eventDescription);

  void publish(EventDescription eventDescription, Subject subject);

  void unpublish(EventDescription eventDescription, Subject subject);
}
