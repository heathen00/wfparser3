package com.ht.event.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ChannelInternalImp implements ChannelInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final String channelName;
  private final List<Event> channelEventList;
  private boolean isEnabled;

  ChannelInternalImp(EventFactoryInternal eventFactoryInternal, String channelName) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channelName = channelName;
    channelEventList = new ArrayList<>();
  }

  @Override
  public String getName() {
    return channelName;
  }

  @Override
  public List<Event> getEventList() {
    return Collections.unmodifiableList(new ArrayList<>(channelEventList));
  }

  @Override
  public List<Subscriber> getSubscriberList() {
    return eventFactoryInternal.getChannelCache(getName()).getSubscriberList();
  }

  @Override
  public List<Publisher> getPublisherList() {
    return eventFactoryInternal.getChannelCache(getName()).getPublisherList();
  }

  @Override
  public Event addEvent(Event event) {
    channelEventList.add(event);
    return event;
  }

  @Override
  public void publish(Event event) {
    if (!isEnabled()) {
      throw new UnsupportedOperationException("cannot publish events unless channel enabled");
    }
    for (SubscriberPublished subscriber : eventFactoryInternal.getChannelCache(getName())
        .getSubscriberList()) {
      subscriber.processPublishEvent(event);
    }
  }

  @Override
  public void unpublish(Event event) {
    if (!isEnabled()) {
      throw new UnsupportedOperationException("cannot unpublish events unless channel enabled");
    }
    for (SubscriberPublished subscriber : eventFactoryInternal.getChannelCache(getName())
        .getSubscriberList()) {
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
