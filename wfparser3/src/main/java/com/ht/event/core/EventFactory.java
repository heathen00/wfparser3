package com.ht.event.core;

public interface EventFactory {
  static EventFactory createFactory() {
    return new EventFactoryInternalRootImp();
  }

  Channel createChannel(String name);

  Event createEvent(Channel channel, String family, String name);

  Publisher createPublisher(Channel channel);

  void addSubscriber(Channel channel, Subscriber eventSubscriber);

  void openChannel(Channel channel);
}
