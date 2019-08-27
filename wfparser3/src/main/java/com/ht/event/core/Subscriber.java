package com.ht.event.core;

public class Subscriber implements SubscriberPublished {

  @Override
  public void processPublishEvent(Event event) {}

  @Override
  public void processUnpublishEvent(Event event) {}
}
