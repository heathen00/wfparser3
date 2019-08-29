package com.ht.event.core;

public interface EventFactory {
  static EventFactory createFactory() {
    return new EventFactoryInternalValidatorImp(
        new EventFactoryInternalCacherImp(new EventFactoryInternalCreatorImp()));
  }

  Channel createChannel(String channelName);

  Event createEvent(Channel eventChannel, String eventFamily, String eventName);

  Publisher createPublisher(Channel eventChannel);

  void addSubscriber(Channel eventChannel, Subscriber eventSubscriber);

  void enableChannel(Channel eventChannel);
}
