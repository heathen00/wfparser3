package com.ht.event.core;

import com.ht.uid.Uid;

public final class PublisherImp implements Publisher {
  private final EventFactoryInternal eventFactoryInternal;
  private final ChannelInternal eventChannelInternal;

  PublisherImp(EventFactoryInternal eventFactoryInternal, ChannelInternal eventChannelInternal) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.eventChannelInternal = eventChannelInternal;
  }

  @Override
  public Channel getChannel() {
    return eventChannelInternal;
  }

  @Override
  public void publish(Uid<Event> eventUid) {
    eventChannelInternal.publish(eventUid);
  }

  @Override
  public void unpublish(Uid<Event> eventUid) {
    eventChannelInternal.unpublish(eventUid);
  }
}
