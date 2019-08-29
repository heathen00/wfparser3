package com.ht.event.core;

interface EventFactoryInternal extends EventFactory {
  ChannelCache getChannelCache(String channelName);

  EventFactoryInternal getRootEventFactoryInternal();
}
