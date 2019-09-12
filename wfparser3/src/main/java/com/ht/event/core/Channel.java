package com.ht.event.core;

import java.util.List;

public interface Channel extends NaturalOrder<Channel> {
  String getName();

  List<Event> getEventList();

  List<Subscriber> getSubscriberList();

  List<Publisher> getPublisherList();

  boolean isOpen();
}
