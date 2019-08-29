package com.ht.event.core;

public interface Publisher {
  Channel getChannel();

  void publish(Event event);

  void unpublish(Event event);
}
