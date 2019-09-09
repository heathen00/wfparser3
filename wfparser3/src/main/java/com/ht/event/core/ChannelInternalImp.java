package com.ht.event.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class ChannelInternalImp implements ChannelInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final String channelName;
  private final Set<Event> publishedEventSet;
  private boolean isEnabled;

  ChannelInternalImp(EventFactoryInternal eventFactoryInternal, String channelName) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channelName = channelName;
    publishedEventSet = new HashSet<>();
    isEnabled = false;
  }

  private void ensureChannelIsEnabled() {
    if (!isEnabled()) {
      throw new UnsupportedOperationException("channel is not enabled");
    }
  }

  private void ensureEventDefinedInChannel(Event event) {
    if (!getChannelCache().getEventList().contains(event)) {
      throw new UnsupportedOperationException(
          "event " + event.getFullyQualifiedName() + " is not defined in this channel");
    }
  }

  private ChannelCache getChannelCache() {
    return eventFactoryInternal.getInstanceCache().getChannelCache(getName());
  }

  @Override
  public String getName() {
    return channelName;
  }

  @Override
  public List<Event> getEventList() {
    return getChannelCache().getEventList();
  }

  @Override
  public List<Subscriber> getSubscriberList() {
    return getChannelCache().getSubscriberList();
  }

  @Override
  public List<Publisher> getPublisherList() {
    return getChannelCache().getPublisherList();
  }

  @Override
  public void publish(Event event) {
    ensureChannelIsEnabled();
    ensureEventDefinedInChannel(event);
    if (publishedEventSet.contains(event)) {
      return;
    }
    for (SubscriberPublished subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processPublishEvent(event);
    }
    publishedEventSet.add(event);
  }

  @Override
  public void unpublish(Event event) {
    ensureChannelIsEnabled();
    ensureEventDefinedInChannel(event);
    if (!publishedEventSet.contains(event)) {
      return;
    }
    for (SubscriberPublished subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processUnpublishEvent(event);
    }
    publishedEventSet.remove(event);
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
