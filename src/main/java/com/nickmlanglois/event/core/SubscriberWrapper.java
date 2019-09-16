package com.nickmlanglois.event.core;

final class SubscriberWrapper extends Subscriber {
  private final Subscriber wrappedSubscriber;

  SubscriberWrapper(Subscriber subscriber) {
    wrappedSubscriber = subscriber;
  }

  Subscriber getWrappedSubscriber() {
    return wrappedSubscriber;
  }

  @Override
  public void processPublishEvent(Event event) {
    try {
      wrappedSubscriber.processPublishEvent(event);
    } catch (Exception e) {
    }
  }

  @Override
  public void processUnpublishEvent(Event event) {
    try {
      wrappedSubscriber.processUnpublishEvent(event);
    } catch (Exception e) {
    }
  }

  @Override
  public String toString() {
    return "SubscriberWrapper [wrapped.toString()=" + wrappedSubscriber.toString() + "]";
  }

  @Override
  public String getName() {
    return wrappedSubscriber.getName();
  }
}
