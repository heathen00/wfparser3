package com.ht.event.core;

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
  public int hashCode() {
    return wrappedSubscriber.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    SubscriberWrapper otherWrapper = (SubscriberWrapper) obj;
    return wrappedSubscriber.equals(otherWrapper.getWrappedSubscriber());
  }

  @Override
  public String toString() {
    return "SubscriberWrapper [wrapped.toString()=" + wrappedSubscriber.toString()
        + ", super.hashCode(): " + super.hashCode() + "]";
  }
}
