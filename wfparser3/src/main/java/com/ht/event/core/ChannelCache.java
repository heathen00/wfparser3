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
}

