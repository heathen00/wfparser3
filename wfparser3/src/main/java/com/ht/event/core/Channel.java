package com.ht.event.core;

import java.util.List;
import com.ht.uid.Uid;
import com.ht.uid.UniqueComponent;

public interface Channel extends UniqueComponent<Channel> {

  String getName();

  Uid<Channel> getUid();

  List<Uid<Event>> getEventUidList();

  List<Subscriber> getSubscriberList();

  List<Publisher> getPublisherList();

  boolean isEnabled();
}
