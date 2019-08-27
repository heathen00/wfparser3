package com.ht.event.core;

import com.ht.uid.UidFactory;

public final class EventFactoryInternalImp implements EventFactoryInternal {
  private UidFactory uidFactory;

  public EventFactoryInternalImp(UidFactory uidFactory) {
    this.uidFactory = uidFactory;
  }

  @Override
  public Channel createChannel(String channelName) {
    return new ChannelInternalImp(this, channelName);
  }

  @Override
  public UidFactory getUidFactory() {
    return uidFactory;
  }

  @Override
  public Event createEvent(Channel eventChannel, String eventFamily, String eventName) {
    Event newEvent = new EventImp(this, eventChannel, eventFamily, eventName);
    return ((ChannelInternal) eventChannel).addEvent(newEvent);
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    Publisher newPublisher = new PublisherImp(this, (ChannelInternal) eventChannel);
    return ((ChannelInternal) eventChannel).addPublisher(newPublisher);
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    ((ChannelInternal) eventChannel).addSubscriber(eventSubscriber);
  }
}
