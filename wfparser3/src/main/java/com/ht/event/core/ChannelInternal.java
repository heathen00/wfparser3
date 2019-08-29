package com.ht.event.core;

interface ChannelInternal extends Channel {
  Event addEvent(Event event);

  Publisher addPublisher(Publisher publisher);

  void publish(Event event);

  void unpublish(Event event);

  void enable();
}
