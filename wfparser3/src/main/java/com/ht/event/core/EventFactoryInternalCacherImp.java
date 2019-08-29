package com.ht.event.core;

import com.ht.uid.Uid;
import com.ht.uid.UidFactory;

import java.util.HashMap;
import java.util.Map;

final class EventFactoryInternalCacherImp implements EventFactoryInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final Map<String, ChannelCache> channelCacheMap;

  EventFactoryInternalCacherImp(EventFactoryInternal eventFactoryInternal) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channelCacheMap = new HashMap<>();
  }

  @Override
  public Channel createChannel(String channelName) {
    ChannelInternal channelInternal = null;
    if (!channelCacheMap.containsKey(channelName)) {
      channelInternal = (ChannelInternal) eventFactoryInternal.createChannel(channelName);
      channelCacheMap.put(channelName, new ChannelCacheImp(channelInternal));
    } else {
      channelInternal = channelCacheMap.get(channelName).getChannelInternal();
    }
    return channelInternal;
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

  @Override
  public ChannelCache getChannelCache(Uid<Channel> channelUid) {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
  }
}
