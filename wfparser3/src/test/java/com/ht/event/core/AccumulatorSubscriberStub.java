package com.ht.event.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AccumulatorSubscriberStub extends Subscriber {
  public static AccumulatorSubscriberStub createAccumulatorSubscriber(String name) {
    return new AccumulatorSubscriberStub(name);
  }

  private final String name;
  private final List<Event> processedPublishedEventList;
  private final List<Event> processedUnpublishedEventList;

  AccumulatorSubscriberStub(String name) {
    this.name = name;
    processedPublishedEventList = new ArrayList<>();
    processedUnpublishedEventList = new ArrayList<>();
  }

  @Override
  public void processPublishEvent(Event event) {
    processedPublishedEventList.add(event);
  }

  @Override
  public void processUnpublishEvent(Event event) {
    processedUnpublishedEventList.add(event);
  }

  public List<Event> getProcessedPublishedEventList() {
    return Collections.unmodifiableList(processedPublishedEventList);
  }

  public List<Event> getProcessedUnpublishedEventList() {
    return Collections.unmodifiableList(processedUnpublishedEventList);
  }

  @Override
  public String toString() {
    return "AccumulatorSubscriberStub [hashCode()=" + hashCode() + "]";
  }

  @Override
  public String getName() {
    return name;
  }
}

