package com.ht.event.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ChannelCacheImp implements ChannelCache {
  private final ChannelInternal channelInternal;
  private final List<Subscriber> subscriberList;

  ChannelCacheImp(ChannelInternal channelInternal) {
    this.channelInternal = channelInternal;
    subscriberList = new ArrayList<>();
  }

  public ChannelInternal getChannelInternal() {
    return channelInternal;
  }

  @Override
  public List<Subscriber> getSubscriberList() {
    return Collections.unmodifiableList(subscriberList);
  }

  @Override
  public void addSubscriber(Subscriber eventSubscriber) {
    subscriberList.add(eventSubscriber);
  }
}
