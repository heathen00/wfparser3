package com.ht.event.core;

import java.util.List;

interface ChannelCache {
  ChannelInternal getChannelInternal();

  List<Subscriber> getSubscriberList();

  void addSubscriber(Subscriber eventSubscriber);

  List<Publisher> getPublisherList();

  void addPublisher(Publisher eventPublisher);

  List<Event> getEventList();

  void addEvent(Event event);

  Event getEvent(Channel eventChannel, String eventFamily, String eventName);

  Event getEvent(Event event, Subject subject);
}

