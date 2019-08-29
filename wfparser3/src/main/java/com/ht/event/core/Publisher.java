package com.ht.event.core;

public interface Publisher {
  Channel getChannel();

  void publish(String eventFullyQualifiedName);

  void unpublish(String eventFullyQualifiedName);
}
