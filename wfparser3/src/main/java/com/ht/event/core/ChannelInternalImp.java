package com.ht.event.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ChannelInternalImp implements ChannelInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final String channelName;
  private final Map<Event, Set<Publisher>> publishedEventToPublisherMap;
  private boolean isEnabled;

  ChannelInternalImp(EventFactoryInternal eventFactoryInternal, String channelName) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channelName = channelName;
    publishedEventToPublisherMap = new HashMap<>();
    isEnabled = false;
  }

  private void ensureParameterIsNotNull(String parameterName, Object parameter) {
    if (null == parameter) {
      throw new NullPointerException(parameterName + " cannot be null");
    }
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
  public void publish(Publisher publisher, Event event) {
    ensureParameterIsNotNull("event", event);
    ensureChannelIsEnabled();
    ensureEventDefinedInChannel(event);
    if (publishedEventToPublisherMap.containsKey(event)) {
      publishedEventToPublisherMap.get(event).add(publisher);
      return;
    }
    for (SubscriberPublished subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processPublishEvent(event);
    }
    if (!publishedEventToPublisherMap.containsKey(event)) {
      publishedEventToPublisherMap.put(event, new HashSet<>());
    }
    publishedEventToPublisherMap.get(event).add(publisher);
  }

  @Override
  public void publish(Publisher publisher, Event event, Subject subject) {
    ensureParameterIsNotNull("event", event);
    ensureParameterIsNotNull("subject", subject);
    ensureChannelIsEnabled();
    Event eventWithSubject = eventFactoryInternal.createEvent(event, subject);
    if (publishedEventToPublisherMap.containsKey(eventWithSubject)) {
      publishedEventToPublisherMap.get(eventWithSubject).add(publisher);
      return;
    }
    for (SubscriberPublished subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processPublishEvent(eventWithSubject);
    }
    if (!publishedEventToPublisherMap.containsKey(eventWithSubject)) {
      publishedEventToPublisherMap.put(eventWithSubject, new HashSet<>());
    }
    publishedEventToPublisherMap.get(eventWithSubject).add(publisher);
  }

  @Override
  public void unpublish(Publisher publisher, Event event) {
    ensureParameterIsNotNull("event", event);
    ensureChannelIsEnabled();
    ensureEventDefinedInChannel(event);
    if (!publishedEventToPublisherMap.containsKey(event)
        || !publishedEventToPublisherMap.get(event).contains(publisher)) {
      return;
    }
    if (1 < publishedEventToPublisherMap.get(event).size()
        && publishedEventToPublisherMap.get(event).contains(publisher)) {
      publishedEventToPublisherMap.get(event).remove(publisher);
      return;
    }
    for (SubscriberPublished subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processUnpublishEvent(event);
    }
    publishedEventToPublisherMap.get(event).remove(publisher);
    if (0 == publishedEventToPublisherMap.get(event).size()) {
      publishedEventToPublisherMap.remove(event);
    }
  }

  @Override
  public void unpublish(Publisher publisher, Event event, Subject subject) {
    ensureParameterIsNotNull("event", event);
    ensureParameterIsNotNull("subject", subject);
    ensureChannelIsEnabled();
    Event eventWithSubject = eventFactoryInternal.createEvent(event, subject);
    if (!publishedEventToPublisherMap.containsKey(eventWithSubject)
        || !publishedEventToPublisherMap.get(eventWithSubject).contains(publisher)) {
      return;
    }
    if (1 < publishedEventToPublisherMap.get(eventWithSubject).size()
        && publishedEventToPublisherMap.get(eventWithSubject).contains(publisher)) {
      publishedEventToPublisherMap.get(eventWithSubject).remove(publisher);
      return;
    }
    for (SubscriberPublished subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processUnpublishEvent(eventWithSubject);
    }
    publishedEventToPublisherMap.get(eventWithSubject).remove(publisher);
    if (0 == publishedEventToPublisherMap.get(eventWithSubject).size()) {
      publishedEventToPublisherMap.remove(eventWithSubject);
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

  @Override
  public EventFactoryInternal getEventFactoryInternal() {
    return eventFactoryInternal;
  }
}
