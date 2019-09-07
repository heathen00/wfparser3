package com.ht.event.core;

interface EventFactoryInternal extends EventFactory {
  InstanceCache getInstanceCache();

  EventFactoryInternal getRootEventFactoryInternal();
}
