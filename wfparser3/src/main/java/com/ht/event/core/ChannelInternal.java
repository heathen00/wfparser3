package com.ht.event.core;

interface ChannelInternal extends Channel {
  EventFactoryInternal getEventFactoryInternal();

  void publish(Publisher publisher, EventDescription event);

  void publish(Publisher publisher, EventDescription event, Subject subject);

  void unpublish(Publisher publisher, EventDescription event);

  void unpublish(Publisher publisher, EventDescription event, Subject subject);

  void open();
}
