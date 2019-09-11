package com.ht.event.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AccumulatorSubscriberStub extends Subscriber {
  private final List<Event> processedPublishedEventList;
  private final List<Event> processedUnpublishedEventList;

  public AccumulatorSubscriberStub() {
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
}

