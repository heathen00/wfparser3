package com.ht.event.core;

import com.ht.uid.Uid;
import com.ht.uid.UidFactory;

final class EventFactoryInternalCreatorImp implements EventFactoryInternal {
  private final UidFactory uidFactory;

  public EventFactoryInternalCreatorImp(UidFactory uidFactory) {
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

  @Override
  public void enableChannel(Channel eventChannel) {
    ((ChannelInternal) eventChannel).enable();
  }

  @Override
  public ChannelCache getChannelCache(Uid<Channel> channelUid) {
    throw new UnsupportedOperationException("EventFactory creator does not cache instances");
  }
}
