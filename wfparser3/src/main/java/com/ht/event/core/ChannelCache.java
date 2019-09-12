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

  Event getEvent(Channel channel, String family, String name);

  Event getEvent(Event event, Subject subject);

  EventDescription getEventDescription(Channel channel, String family, String name);

  void addEventDescription(EventDescription eventDescription);
}

