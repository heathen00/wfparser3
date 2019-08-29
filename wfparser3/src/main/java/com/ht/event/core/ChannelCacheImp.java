package com.ht.event.core;

class ChannelCacheImp implements ChannelCache {
  private final ChannelInternal channelInternal;

  ChannelCacheImp(ChannelInternal channelInternal) {
    this.channelInternal = channelInternal;
  }
}
