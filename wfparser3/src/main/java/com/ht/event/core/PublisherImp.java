package com.ht.event.core;

public final class PublisherImp implements Publisher {
  private final EventFactoryInternal eventFactoryInternal;
  private final ChannelInternal channelInternal;

  PublisherImp(EventFactoryInternal eventFactoryInternal, ChannelInternal channelInternal) {
    this.eventFactoryInternal = eventFactoryInternal;
    this.channelInternal = channelInternal;
  }

  @Override
  public Channel getChannel() {
    return channelInternal;
  }

  @Override
  public void publish(Event event) {
    channelInternal.publish(this, event);
  }

  @Override
  public void publish(Event event, Subject subject) {
    channelInternal.publish(this, event, subject);
  }

  @Override
  public void unpublish(Event event) {
    channelInternal.unpublish(this, event);
  }

  @Override
  public void unpublish(Event event, Subject subject) {
    channelInternal.unpublish(this, event, subject);
  }
}
