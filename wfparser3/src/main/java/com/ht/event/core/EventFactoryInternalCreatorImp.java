package com.ht.event.core;

final class EventFactoryInternalCreatorImp implements EventFactoryInternal {
  private final EventFactoryInternal rootEventFactoryInternal;

  public EventFactoryInternalCreatorImp(EventFactoryInternal rootEventFactoryInternal) {
    this.rootEventFactoryInternal = rootEventFactoryInternal;
  }

  @Override
  public Channel createChannel(String channelName) {
    return new ChannelInternalImp(getRootEventFactoryInternal(), channelName);
  }

  @Override
  public Event createEvent(Channel eventChannel, String eventFamily, String eventName) {
    return new EventImp(this, eventChannel, eventFamily, eventName);
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    return new PublisherImp(this, (ChannelInternal) eventChannel);
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    throw new UnsupportedOperationException("EventFactory creator does not create subscribers");
  }

  @Override
  public void enableChannel(Channel eventChannel) {
    throw new UnsupportedOperationException("EventFactory creator does not enable event channels");
  }

  @Override
  public EventFactoryInternal getRootEventFactoryInternal() {
    return rootEventFactoryInternal;
  }

  @Override
  public InstanceCache getInstanceCache() {
    throw new UnsupportedOperationException("EventFactory creator does not cache instances");
  }
}
