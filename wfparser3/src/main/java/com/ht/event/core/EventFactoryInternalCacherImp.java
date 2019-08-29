package com.ht.event.core;

import java.util.HashMap;
import java.util.Map;

final class EventFactoryInternalCacherImp implements EventFactoryInternal {
  private final EventFactoryInternal rootEventFactoryInternal;
  private final EventFactoryInternal nextEventFactoryInternal;
  private final Map<String, ChannelCache> channelCacheMap;

  EventFactoryInternalCacherImp(EventFactoryInternal rootEventFactoryInternal,
      EventFactoryInternal nextEventFactoryInternal) {
    this.rootEventFactoryInternal = rootEventFactoryInternal;
    this.nextEventFactoryInternal = nextEventFactoryInternal;
    this.channelCacheMap = new HashMap<>();
  }

  @Override
  public Channel createChannel(String channelName) {
    ChannelInternal channelInternal = null;
    if (!channelCacheMap.containsKey(channelName)) {
      channelInternal = (ChannelInternal) nextEventFactoryInternal.createChannel(channelName);
      channelCacheMap.put(channelName, new ChannelCacheImp(channelInternal));
    } else {
      channelInternal = channelCacheMap.get(channelName).getChannelInternal();
    }
    return channelInternal;
  }

  @Override
  public Event createEvent(Channel eventChannel, String eventFamily, String eventName) {
    return nextEventFactoryInternal.createEvent(eventChannel, eventFamily, eventName);
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    return nextEventFactoryInternal.createPublisher(eventChannel);
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    if (!channelCacheMap.get(eventChannel.getName()).getSubscriberList()
        .contains(eventSubscriber)) {
      channelCacheMap.get(eventChannel.getName()).addSubscriber(eventSubscriber);
    }
  }

  @Override
  public void enableChannel(Channel eventChannel) {
    nextEventFactoryInternal.enableChannel(eventChannel);
  }

  @Override
  public ChannelCache getChannelCache(String channelName) {
    return channelCacheMap.get(channelName);
  }

  @Override
  public EventFactoryInternal getRootEventFactoryInternal() {
    return rootEventFactoryInternal;
  }
}
