package com.ht.event.core;

import com.ht.uid.UidFactory;

final class EventFactoryInternalValidatorImp implements EventFactoryInternal {
  private final EventFactoryInternal eventFactoryInternal;

  EventFactoryInternalValidatorImp(EventFactoryInternal eventFactoryInternal) {
    this.eventFactoryInternal = eventFactoryInternal;
  }

  private void ensureParameterNotNull(String parameterName, Object parameter) {
    if (null == parameter) {
      throw new NullPointerException(parameterName + " cannot equal null");
    }
  }

  private void ensureChannelEnabled(Channel eventChannel, String message) {
    if (eventChannel.isEnabled()) {
      throw new UnsupportedOperationException(message);
    }
  }

  @Override
  public Channel createChannel(String channelName) {
    ensureParameterNotNull("channelName", channelName);
    return eventFactoryInternal.createChannel(channelName);
  }

  @Override
  public Event createEvent(Channel eventChannel, String eventFamily, String eventName) {
    ensureParameterNotNull("eventChannel", eventChannel);
    ensureChannelEnabled(eventChannel, "cannot create events after enabling channel");
    ensureParameterNotNull("eventFamily", eventFamily);
    ensureParameterNotNull("eventName", eventName);
    return eventFactoryInternal.createEvent(eventChannel, eventFamily, eventName);
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    ensureParameterNotNull("eventChannel", eventChannel);
    ensureChannelEnabled(eventChannel, "cannot create publishers after enabling channel");
    return eventFactoryInternal.createPublisher(eventChannel);
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    ensureParameterNotNull("eventChannel", eventChannel);
    ensureChannelEnabled(eventChannel, "cannot add subscribers after enabling channel");
    ensureParameterNotNull("eventSubscriber", eventSubscriber);
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
