package com.ht.event.core;

public interface EventDescription extends NaturalOrder<EventDescription> {
  Channel getChannel();

  String getFamily();

  String getName();

  String getFullyQualifiedName();
}
