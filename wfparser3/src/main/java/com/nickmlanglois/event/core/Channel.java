package com.nickmlanglois.event.core;

import java.util.List;

public interface Channel extends NaturalOrder<Channel> {
  String getName();

  List<EventDescription> getEventDescriptionList();

  List<Subscriber> getSubscriberList();

  List<Publisher> getPublisherList();

  boolean isOpen();
}
