package com.ht.event.core;

final class EventFactoryInternalRootImp implements EventFactoryInternal {
  private final EventFactoryInternal nextEventFactoryInternal;

  EventFactoryInternalRootImp() {
    nextEventFactoryInternal = new EventFactoryInternalParameterValidatorImp(this,
        new EventFactoryInternalCacherImp(this, new EventFactoryInternalCreatorImp(this)));
  }

  @Override
  public Channel createChannel(String channelName) {
    return nextEventFactoryInternal.createChannel(channelName);
  }

  @Override
  public Event createEvent(Channel eventChannel, String eventFamily, String eventName) {
    return nextEventFactoryInternal.createEvent(eventChannel, eventFamily, eventName);
  }

  @Override
  public Event createEvent(Event event, Subject subject) {
    return nextEventFactoryInternal.createEvent(event, subject);
  }

  @Override
  public Publisher createPublisher(Channel eventChannel) {
    return nextEventFactoryInternal.createPublisher(eventChannel);
  }

  @Override
  public void addSubscriber(Channel eventChannel, Subscriber eventSubscriber) {
    nextEventFactoryInternal.addSubscriber(eventChannel, eventSubscriber);
  }

  @Override
  public void enableChannel(Channel eventChannel) {
    nextEventFactoryInternal.enableChannel(eventChannel);
  }

  @Override
  public EventFactoryInternal getRootEventFactoryInternal() {
    return this;
  }

  @Override
  public InstanceCache getInstanceCache() {
    return nextEventFactoryInternal.getInstanceCache();
  }

  @Override
  public Subject getNoSubject() {
    return nextEventFactoryInternal.getNoSubject();
  }
}
