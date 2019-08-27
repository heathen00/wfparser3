package com.ht.event.core;

import com.ht.uid.Uid;

public final class EventImp implements Event {
  private final EventFactoryInternal eventFactoryInternal;
  private final Channel eventChannel;
  private final String eventFamily;
  private final String eventName;
  private final Uid<Event> eventUid;

  EventImp(EventFactoryInternal eventFactoryInternal, Channel eventChannel, String eventFamily,
      String eventName) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.eventChannel = eventChannel;
    this.eventFamily = eventFamily;
    this.eventName = eventName;
    this.eventUid =
        this.eventFactoryInternal.getUidFactory().createUid(getFullyQualifiedName(), this);
  }

  @Override
  public Channel getChannel() {
    return eventChannel;
  }

  @Override
  public String getFamily() {
    return eventFamily;
  }

  @Override
  public String getName() {
    return eventName;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", eventChannel.getName(), getFamily(), getName());
  }

  @Override
  public Uid<Event> getUid() {
    return eventUid;
  }
}
