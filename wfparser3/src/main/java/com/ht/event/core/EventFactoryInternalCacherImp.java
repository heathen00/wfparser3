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


  private void ensureChannelBelongsToFactory(Channel eventChannel) {
    if (null == instanceCache.getChannelCache(eventChannel.getName())) {
      throw new UnsupportedOperationException(
          "channel " + eventChannel.getName() + " does not exist in this factory");
    }
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
    ensureChannelBelongsToFactory(eventChannel);
    Event event = getChannelCache(eventChannel).getEvent(eventChannel, eventFamily, eventName);
    if (null == event) {
      event = nextEventFactoryInternal.createEvent(eventChannel, eventFamily, eventName);
      getChannelCache(eventChannel).addEvent(event);
    }
    return event;
  }

  @Override
  public Event createEvent(Event event, Subject subject) {
    Event eventWithSubject = getChannelCache(event.getChannel()).getEvent(event, subject);
    if (null == eventWithSubject) {
      eventWithSubject = nextEventFactoryInternal.createEvent(event, subject);
      getChannelCache(eventWithSubject.getChannel()).addEvent(eventWithSubject);
    }
    return eventWithSubject;
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    ensureChannelBelongsToFactory(eventChannel);
    Publisher newPublisher = nextEventFactoryInternal.createPublisher(eventChannel);
    getChannelCache(eventChannel).addPublisher(newPublisher);
    return newPublisher;
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    ensureChannelBelongsToFactory(eventChannel);
    Subscriber subscriberWrapper = new SubscriberWrapper(eventSubscriber);
    ChannelInternal subscribersCurrentChannelInternal =
        getInstanceCache().getChannelInternalForSubscriber(subscriberWrapper);
    if (null != subscribersCurrentChannelInternal
        && !eventChannel.equals(subscribersCurrentChannelInternal)) {
      throw new UnsupportedOperationException("subscriber already subscribed to channel "
          + subscribersCurrentChannelInternal.getName());
    }
    getChannelCache(eventChannel).addSubscriber(subscriberWrapper);
  }

  @Override
  public void enableChannel(Channel eventChannel) {
    ensureChannelBelongsToFactory(eventChannel);
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

  @Override
  public Subject getNoSubject() {
    return nextEventFactoryInternal.getNoSubject();
  }
}
