package com.ht.event.core;

import com.ht.uid.Uid;

import java.util.List;

public interface Channel extends Comparable<Channel> {
  String getName();

  List<Uid<Event>> getEventUidList();

  List<Subscriber> getSubscriberList();

  List<Publisher> getPublisherList();

  boolean isEnabled();
}
