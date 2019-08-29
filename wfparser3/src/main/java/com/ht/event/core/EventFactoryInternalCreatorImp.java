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
    Event newEvent = new EventImp(this, eventChannel, eventFamily, eventName);
    return ((ChannelInternal) eventChannel).addEvent(newEvent);
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    Publisher newPublisher = new PublisherImp(this, (ChannelInternal) eventChannel);
    return ((ChannelInternal) eventChannel).addPublisher(newPublisher);
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    throw new UnsupportedOperationException("EventFactory creator does not create subscribers");
  }

  @Override
  public void enableChannel(Channel eventChannel) {
    ((ChannelInternal) eventChannel).enable();
  }

  @Override
  public ChannelCache getChannelCache(String channelName) {
    throw new UnsupportedOperationException("EventFactory creator does not cache instances");
  }

  @Override
  public EventFactoryInternal getRootEventFactoryInternal() {
    return rootEventFactoryInternal;
  }
}
