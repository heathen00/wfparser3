package com.nickmlanglois.event.core;

public abstract class Subscriber implements SubscriberPublished<Subscriber> {

  @Override
  public void processPublishEvent(Event event) {}

  @Override
  public void processUnpublishEvent(Event event) {}

  @Override
  public final int compareTo(Subscriber o) {
    return getName().compareTo(o.getName());
  }

  @Override
  public final boolean equals(Object obj) {
    return getName().equals(((Subscriber) obj).getName());
  }

  @Override
  public final int hashCode() {
    return getName().hashCode();
  }
}
