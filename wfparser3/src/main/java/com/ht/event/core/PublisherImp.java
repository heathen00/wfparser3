package com.ht.event.core;

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
  public void publish(Event event) {
    eventChannelInternal.publish(event);
  }

  @Override
  public void publish(Event event, Subject subject) {
    eventChannelInternal.publish(event, subject);
  }

  @Override
  public void unpublish(Event event) {
    eventChannelInternal.unpublish(event);
  }

  @Override
  public void unpublish(Event event, Subject subject) {
    eventChannelInternal.unpublish(event, subject);
  }
}
