package com.ht.event.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ChannelInternalImp extends NaturalOrderBase<Channel> implements ChannelInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final String name;
  private final Map<Event, Set<Publisher>> publishedEventToPublisherMap;
  private boolean isOpen;

  ChannelInternalImp(EventFactoryInternal eventFactoryInternal, String name) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.name = name;
    publishedEventToPublisherMap = new HashMap<>();
    isOpen = false;
  }

  private void ensureParameterIsNotNull(String parameterName, Object parameter) {
    if (null == parameter) {
      throw new NullPointerException(parameterName + " cannot be null");
    }
  }

  private void ensureChannelIsOpen() {
    if (!isOpen()) {
      throw new UnsupportedOperationException("channel is not open");
    }
  }

  private void ensureEventDefinedInChannel(EventDescription eventDescription) {
    if (!getChannelCache().getEventDescriptionList().contains(eventDescription)) {
      throw new UnsupportedOperationException("eventDescription "
          + eventDescription.getFullyQualifiedName() + " is not defined in this channel");
    }
  }

  private ChannelCache getChannelCache() {
    return eventFactoryInternal.getInstanceCache().getChannelCache(getName());
  }

  @Override
  public String getName() {
    return name;
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
  public void publish(Publisher publisher, EventDescription eventDescription) {
    ensureParameterIsNotNull("eventDescription", eventDescription);
    ensureChannelIsOpen();
    ensureEventDefinedInChannel(eventDescription);
    Event event =
        eventFactoryInternal.createEvent(eventDescription, eventFactoryInternal.getNoSubject());
    if (publishedEventToPublisherMap.containsKey(event)) {
      publishedEventToPublisherMap.get(event).add(publisher);
      return;
    }
    for (Subscriber subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processPublishEvent(event);
    }
    if (!publishedEventToPublisherMap.containsKey(event)) {
      publishedEventToPublisherMap.put(event, new HashSet<>());
    }
    publishedEventToPublisherMap.get(event).add(publisher);
  }

  @Override
  public void publish(Publisher publisher, EventDescription eventDescription, Subject subject) {
    ensureParameterIsNotNull("eventDescription", eventDescription);
    ensureParameterIsNotNull("subject", subject);
    ensureChannelIsOpen();
    Event event = eventFactoryInternal.createEvent(eventDescription, subject);
    if (publishedEventToPublisherMap.containsKey(event)) {
      publishedEventToPublisherMap.get(event).add(publisher);
      return;
    }
    for (Subscriber subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processPublishEvent(event);
    }
    if (!publishedEventToPublisherMap.containsKey(event)) {
      publishedEventToPublisherMap.put(event, new HashSet<>());
    }
    publishedEventToPublisherMap.get(event).add(publisher);
  }

  @Override
  public void unpublish(Publisher publisher, EventDescription eventDescription) {
    ensureParameterIsNotNull("eventDescription", eventDescription);
    ensureChannelIsOpen();
    ensureEventDefinedInChannel(eventDescription);
    Event event =
        eventFactoryInternal.createEvent(eventDescription, eventFactoryInternal.getNoSubject());
    if (!publishedEventToPublisherMap.containsKey(event)
        || !publishedEventToPublisherMap.get(event).contains(publisher)) {
      return;
    }
    if (1 < publishedEventToPublisherMap.get(event).size()
        && publishedEventToPublisherMap.get(event).contains(publisher)) {
      publishedEventToPublisherMap.get(event).remove(publisher);
      return;
    }
    for (Subscriber subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processUnpublishEvent(event);
    }
    publishedEventToPublisherMap.get(event).remove(publisher);
    if (0 == publishedEventToPublisherMap.get(event).size()) {
      publishedEventToPublisherMap.remove(event);
    }
  }

  @Override
  public void unpublish(Publisher publisher, EventDescription eventDescription, Subject subject) {
    ensureParameterIsNotNull("event", eventDescription);
    ensureParameterIsNotNull("subject", subject);
    ensureChannelIsOpen();
    Event event = eventFactoryInternal.createEvent(eventDescription, subject);
    if (!publishedEventToPublisherMap.containsKey(event)
        || !publishedEventToPublisherMap.get(event).contains(publisher)) {
      return;
    }
    if (1 < publishedEventToPublisherMap.get(event).size()
        && publishedEventToPublisherMap.get(event).contains(publisher)) {
      publishedEventToPublisherMap.get(event).remove(publisher);
      return;
    }
    for (Subscriber subscriber : getChannelCache().getSubscriberList()) {
      subscriber.processUnpublishEvent(event);
    }
    publishedEventToPublisherMap.get(event).remove(publisher);
    if (0 == publishedEventToPublisherMap.get(event).size()) {
      publishedEventToPublisherMap.remove(event);
    }
  }

  @Override
  public void open() {
    isOpen = true;
  }

  @Override
  public boolean isOpen() {
    return isOpen;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
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

  @Override
  public List<EventDescription> getEventDescriptionList() {
    return getChannelCache().getEventDescriptionList();
  }
}
