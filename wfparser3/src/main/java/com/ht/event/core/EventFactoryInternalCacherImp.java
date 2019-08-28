package com.ht.event.core;

import com.ht.uid.UidFactory;

final class EventFactoryInternalCacherImp implements EventFactoryInternal {
  private final EventFactoryInternal eventFactoryInternal;

  EventFactoryInternalCacherImp(EventFactoryInternal eventFactoryInternal) {
    this.eventFactoryInternal = eventFactoryInternal;
  }

  @Override
  public Channel createChannel(String channelName) {
    return eventFactoryInternal.createChannel(channelName);
  }

  @Override
  public Event createEvent(Channel eventChannel, String eventFamily, String eventName) {
    return eventFactoryInternal.createEvent(eventChannel, eventFamily, eventName);
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    return eventFactoryInternal.createPublisher(eventChannel);
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    eventFactoryInternal.addSubscriber(eventChannel, eventSubscriber);
  }

  @Override
  public void enableChannel(Channel eventChannel) {
    eventFactoryInternal.enableChannel(eventChannel);
  }

  @Override
  public UidFactory getUidFactory() {
    return eventFactoryInternal.getUidFactory();
  }
}
