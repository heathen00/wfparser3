package com.ht.event.core;

interface EventFactoryInternal extends EventFactory {
  InstanceCache getInstanceCache();

  EventFactoryInternal getRootEventFactoryInternal();

  Event createEvent(Event event, Subject subject);

  Subject getNoSubject();

  Event createEvent(EventDescription eventDescription, Subject subject);
}
