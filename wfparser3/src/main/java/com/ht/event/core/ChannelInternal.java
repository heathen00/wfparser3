package com.ht.event.core;

interface ChannelInternal extends Channel {
  EventFactoryInternal getEventFactoryInternal();

  void publish(Publisher publisher, Event event);

  void publish(Publisher publisher, Event event, Subject subject);

  void unpublish(Publisher publisher, Event event);

  void unpublish(Publisher publisher, Event event, Subject subject);

  void enable();
}
