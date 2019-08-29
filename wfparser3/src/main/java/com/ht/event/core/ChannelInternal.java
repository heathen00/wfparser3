package com.ht.event.core;

interface ChannelInternal extends Channel {
  Event addEvent(Event event);

  Publisher addPublisher(Publisher publisher);

  void addSubscriber(Subscriber subscriber);

  void publish(String eventFullyQualifiedName);

  void unpublish(String eventFullyQualifiedName);

  void enable();
}
