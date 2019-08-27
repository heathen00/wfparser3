package com.ht.event.core;

import com.ht.uid.UidFactory;

public interface EventFactory {

  static EventFactory createFactory(UidFactory uidFactory) {
    return new EventFactoryInternalImp(uidFactory);
  }

  Channel createChannel(String channelName);

  Event createEvent(Channel eventChannel, String eventFamily, String eventName);

  Publisher createPublisher(Channel eventChannel);

}
