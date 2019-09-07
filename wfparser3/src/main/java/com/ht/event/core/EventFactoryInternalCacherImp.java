package com.ht.event.core;

final class EventFactoryInternalCacherImp implements EventFactoryInternal {
  private final EventFactoryInternal rootEventFactoryInternal;
  private final EventFactoryInternal nextEventFactoryInternal;
  private final InstanceCache instanceCache;

  EventFactoryInternalCacherImp(EventFactoryInternal rootEventFactoryInternal,
      EventFactoryInternal nextEventFactoryInternal) {
    this.rootEventFactoryInternal = rootEventFactoryInternal;
    this.nextEventFactoryInternal = nextEventFactoryInternal;
    this.instanceCache = InstanceCache.createInstanceCache();
  }

  @Override
  public Channel createChannel(String channelName) {
    ChannelInternal channelInternal = null;
    if (null == instanceCache.getChannelCache(channelName)) {
      channelInternal = (ChannelInternal) nextEventFactoryInternal.createChannel(channelName);
      instanceCache.addChannelCache(channelName, channelInternal);
    } else {
      channelInternal = instanceCache.getChannelCache(channelName).getChannelInternal();
    }
    return channelInternal;
  }

  @Override
  public Event createEvent(Channel eventChannel, String eventFamily, String eventName) {
    Event event = getChannelCache(eventChannel).getEvent(eventChannel, eventFamily, eventName);
    if (null == event) {
      event = nextEventFactoryInternal.createEvent(eventChannel, eventFamily, eventName);
      getChannelCache(eventChannel).addEvent(event);
    }
    return event;
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    Publisher newPublisher = nextEventFactoryInternal.createPublisher(eventChannel);
    getChannelCache(eventChannel).addPublisher(newPublisher);
    return newPublisher;
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    getChannelCache(eventChannel).addSubscriber(eventSubscriber);
  }

  @Override
  public void enableChannel(Channel eventChannel) {
    getChannelCache(eventChannel).getChannelInternal().enable();
  }

  private ChannelCache getChannelCache(Channel eventChannel) {
    return instanceCache.getChannelCache(eventChannel.getName());
  }

  @Override
  public InstanceCache getInstanceCache() {
    return instanceCache;
  }

  @Override
  public EventFactoryInternal getRootEventFactoryInternal() {
    return rootEventFactoryInternal;
  }
}
