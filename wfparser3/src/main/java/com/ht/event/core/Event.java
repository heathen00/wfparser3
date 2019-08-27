package com.ht.event.core;

import com.ht.uid.Uid;
import com.ht.uid.UniqueComponent;

public interface Event extends UniqueComponent<Event> {

  Channel getChannel();

  String getFamily();

  String getName();

  String getFullyQualifiedName();

  Uid<Event> getUid();
}
