package com.ht.event.core;

import com.ht.uid.Uid;
import com.ht.uid.UidFactory;

import java.util.HashMap;
import java.util.Map;

final class EventFactoryInternalCacherImp implements EventFactoryInternal {
  private final EventFactoryInternal eventFactoryInternal;
  private final Map<Uid<Channel>, ChannelCache> channelCacheMap;

  EventFactoryInternalCacherImp(EventFactoryInternal eventFactoryInternal) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channelCacheMap = new HashMap<>();
  }

  @Override
  public Channel createChannel(String channelName) {
    Channel newChannel = eventFactoryInternal.createChannel(channelName);
    if (!channelCacheMap.containsKey(newChannel.getUid())) {
      channelCacheMap.put(newChannel.getUid(), new ChannelCacheImp((ChannelInternal) newChannel));
    }
    return newChannel;
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
