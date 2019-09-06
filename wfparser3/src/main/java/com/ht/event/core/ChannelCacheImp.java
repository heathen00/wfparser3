package com.ht.event.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ChannelCacheImp implements ChannelCache {
  private final ChannelInternal channelInternal;
  private final List<Subscriber> subscriberList;
  private final List<Publisher> publisherList;
  private final List<Event> eventList;
  private final EventForComparisonImp eventForComparison;

  ChannelCacheImp(ChannelInternal channelInternal) {
    this.channelInternal = channelInternal;
    subscriberList = new ArrayList<>();
    publisherList = new ArrayList<>();
    eventList = new ArrayList<>();
    eventForComparison = new EventForComparisonImp();
  }

  public ChannelInternal getChannelInternal() {
    return channelInternal;
  }

  @Override
  public List<Subscriber> getSubscriberList() {
    return Collections.unmodifiableList(subscriberList);
  }

  @Override
  public void addSubscriber(Subscriber eventSubscriber) {
    if (!subscriberList.contains(eventSubscriber)) {
      subscriberList.add(eventSubscriber);
    }
  }

  @Override
  public void addPublisher(Publisher eventPublisher) {
    publisherList.add(eventPublisher);
  }

  @Override
  public List<Publisher> getPublisherList() {
    return Collections.unmodifiableList(publisherList);
  }

  @Override
  public List<Event> getEventList() {
    return Collections.unmodifiableList(eventList);
  }

  @Override
  public void addEvent(Event event) {
    eventList.add(event);
  }

  @Override
  public Event getEvent(Channel eventChannel, String eventFamily, String eventName) {
    Event event = null;
    eventForComparison.setChannel(eventChannel);
    eventForComparison.setFamily(eventFamily);
    eventForComparison.setName(eventName);
    int existingEventIndex = eventList.indexOf(eventForComparison);
    if (-1 != existingEventIndex) {
      event = eventList.get(existingEventIndex);
    }
    return event;
  }
}
