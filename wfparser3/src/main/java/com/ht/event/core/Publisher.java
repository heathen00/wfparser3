package com.ht.event.core;

public interface Publisher {
  Channel getChannel();

  void publish(EventDescription event);

  void unpublish(EventDescription event);

  void publish(EventDescription event, Subject subject);

  void unpublish(EventDescription event, Subject subject);
}
