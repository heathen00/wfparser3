package com.ht.event.core;

interface ChannelInternal extends Channel {
  void publish(Event event);

  void unpublish(Event event);

  void enable();
}
