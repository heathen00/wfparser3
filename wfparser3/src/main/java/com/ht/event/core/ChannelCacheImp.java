package com.ht.event.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ChannelCacheImp implements ChannelCache {
  private final ChannelInternal channelInternal;
  private final List<Subscriber> subscriberList;
  private final List<Publisher> publisherList;
  private final List<Event> eventList;
  private final List<EventDescription> eventDescriptionList;
  private final EventInternalForComparisonImp eventForComparison;
  private final EventDescriptionForComparisonImp eventDescriptionForComparison;

  ChannelCacheImp(ChannelInternal channelInternal) {
    this.channelInternal = channelInternal;
    subscriberList = new ArrayList<>();
    publisherList = new ArrayList<>();
    eventList = new ArrayList<>();
    eventDescriptionList = new ArrayList<>();
    eventForComparison = new EventInternalForComparisonImp();
    eventDescriptionForComparison = new EventDescriptionForComparisonImp();
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
  public Event getEvent(Channel channel, String family, String name) {
    return getEvent(channel, family, name,
        channelInternal.getEventFactoryInternal().getNoSubject());
  }

  @Override
  public Event getEvent(Event event, Subject subject) {
    return getEvent(event.getChannel(), event.getFamily(), event.getName(), subject);
  }

  private Event getEvent(Channel channel, String family, String name, Subject subject) {
    Event event = null;
    eventForComparison.setChannel(channel);
    eventForComparison.setFamily(family);
    eventForComparison.setName(name);
    eventForComparison.setSubject(subject);
    int existingEventIndex = eventList.indexOf(eventForComparison);
    if (-1 != existingEventIndex) {
      event = eventList.get(existingEventIndex);
    }
    return event;
  }

  @Override
  public String toString() {
    return "ChannelCacheImp [getChannelInternal()=" + getChannelInternal()
        + ", getSubscriberList()=" + getSubscriberList() + ", getPublisherList()="
        + getPublisherList() + ", getEventList()=" + getEventList() + ", hashCode()=" + hashCode()
        + "]";
  }

  @Override
  public EventDescription getEventDescription(Channel channel, String family, String name) {
    EventDescription eventDescription = null;
    eventDescriptionForComparison.setChannel(channel);
    eventDescriptionForComparison.setFamily(family);
    eventDescriptionForComparison.setName(name);
    int existingEventIndex = eventDescriptionList.indexOf(eventDescriptionForComparison);
    if (-1 != existingEventIndex) {
      eventDescription = eventDescriptionList.get(existingEventIndex);
    }
    return eventDescription;
  }

  @Override
  public void addEventDescription(EventDescription eventDescription) {
    eventDescriptionList.add(eventDescription);
  }

  @Override
  public List<EventDescription> getEventDescriptionList() {
    return Collections.unmodifiableList(eventDescriptionList);
  }
}
