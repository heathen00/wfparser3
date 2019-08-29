package com.ht.event.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ChannelInternalImp implements ChannelInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final String channelName;
  private final Map<String, Event> channelEventMap;
  private final List<Publisher> channelPublisherList;
  private final List<Subscriber> channelSubscriberList;
  private boolean isEnabled;

  ChannelInternalImp(EventFactoryInternal eventFactoryInternal, String channelName) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channelName = channelName;
    channelEventMap = new HashMap<>();
    channelPublisherList = new ArrayList<>();
    channelSubscriberList = new ArrayList<>();
  }

  @Override
  public String getName() {
    return channelName;
  }

  @Override
  public List<String> getEventFullyQualifiedNameList() {
    return Collections.unmodifiableList(new ArrayList<>(channelEventMap.keySet()));
  }

  @Override
  public List<Subscriber> getSubscriberList() {
    return Collections.unmodifiableList(channelSubscriberList);
  }

  @Override
  public List<Publisher> getPublisherList() {
    return Collections.unmodifiableList(channelPublisherList);
  }

  @Override
  public Event addEvent(Event event) {
    channelEventMap.put(event.getFullyQualifiedName(), event);
    return event;
  }

  @Override
  public Publisher addPublisher(Publisher publisher) {
    channelPublisherList.add(publisher);
    return publisher;
  }

  @Override
  public void addSubscriber(Subscriber subscriber) {
    channelSubscriberList.add(subscriber);
  }

  @Override
  public void publish(String eventFullyQualifiedName) {
    if (!isEnabled()) {
      throw new UnsupportedOperationException("cannot publish events unless channel enabled");
    }
    Event event = channelEventMap.get(eventFullyQualifiedName);
    for (SubscriberPublished subscriber : channelSubscriberList) {
      subscriber.processPublishEvent(event);
    }
  }

  @Override
  public void unpublish(String eventFullyQualifiedName) {
    if (!isEnabled()) {
      throw new UnsupportedOperationException("cannot unpublish events unless channel enabled");
    }
    Event event = channelEventMap.get(eventFullyQualifiedName);
    for (SubscriberPublished subscriber : channelSubscriberList) {
      subscriber.processUnpublishEvent(event);
    }
  }

  @Override
  public void enable() {
    isEnabled = true;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((channelName == null) ? 0 : channelName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ChannelInternalImp other = (ChannelInternalImp) obj;
    if (channelName == null) {
      if (other.channelName != null) {
        return false;
      }
    } else if (!channelName.equals(other.channelName)) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Channel o) {
    return getName().compareTo(o.getName());
  }
}
