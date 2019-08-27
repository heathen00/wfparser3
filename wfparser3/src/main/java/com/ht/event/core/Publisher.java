package com.ht.event.core;

import com.ht.uid.Uid;

public interface Publisher {
  Channel getChannel();

  void publish(Uid<Event> eventUid);

  void unpublish(Uid<Event> eventUid);
}
