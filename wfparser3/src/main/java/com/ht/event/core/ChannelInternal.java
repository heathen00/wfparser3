package com.ht.event.core;

interface ChannelInternal extends Channel {
  void publish(Event event);

  void publish(Event event, Subject subject);

  void unpublish(Event event);

  void unpublish(Event event, Subject subject);

  void enable();
}
