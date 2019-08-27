package com.ht.event.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ht.uid.Uid;

public final class ChannelInternalImp implements ChannelInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final String channelName;
  private final Uid<Channel> channelUid;
  private final Map<Uid<Event>, Event> channelEventMap;
  private final List<Publisher> channelPublisherList;
  private final List<Subscriber> channelSubscriberList;
  private boolean isEnabled;

  ChannelInternalImp(EventFactoryInternal eventFactoryInternal, String channelName) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channelName = channelName;
    this.channelUid = this.eventFactoryInternal.getUidFactory().createUid(this.channelName, this);
    channelEventMap = new HashMap<>();
    channelPublisherList = new ArrayList<>();
    channelSubscriberList = new ArrayList<>();
  }

  @Override
  public String getName() {
    return channelName;
  }

  @Override
  public Uid<Channel> getUid() {
    return channelUid;
  }

  @Override
  public List<Uid<Event>> getEventUidList() {
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
    channelEventMap.put(event.getUid(), event);
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
  public void publish(Uid<Event> eventUid) {
    if (!isEnabled()) {
      throw new UnsupportedOperationException("cannot publish events unless channel enabled");
    }
    Event event = channelEventMap.get(eventUid);
    for (SubscriberPublished subscriber : channelSubscriberList) {
      subscriber.processPublishEvent(event);
    }
  }

  @Override
  public void unpublish(Uid<Event> eventUid) {
    if (!isEnabled()) {
      throw new UnsupportedOperationException("cannot unpublish events unless channel enabled");
    }
    Event event = channelEventMap.get(eventUid);
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
}
