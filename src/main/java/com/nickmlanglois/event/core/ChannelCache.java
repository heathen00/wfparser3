package com.nickmlanglois.event.core;

import java.util.List;

interface ChannelCache {
  ChannelInternal getChannelInternal();

  List<Subscriber> getSubscriberList();

  void addSubscriber(Subscriber eventSubscriber);

  List<Publisher> getPublisherList();

  void addPublisher(Publisher eventPublisher);

  List<EventDescription> getEventDescriptionList();

  void addEventDescription(EventDescription eventDescription);

  EventDescription getEventDescription(Channel channel, String family, String name);
}

