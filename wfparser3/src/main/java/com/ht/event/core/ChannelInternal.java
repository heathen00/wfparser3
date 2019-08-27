package com.ht.event.core;

import com.ht.uid.Uid;

interface ChannelInternal extends Channel {
  Event addEvent(Event event);

  Publisher addPublisher(Publisher publisher);

  void addSubscriber(Subscriber subscriber);

  void publish(Uid<Event> eventUid);

  void unpublish(Uid<Event> eventUid);

  void enable();
}
