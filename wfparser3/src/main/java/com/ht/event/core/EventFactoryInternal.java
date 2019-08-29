package com.ht.event.core;

import com.ht.uid.Uid;
import com.ht.uid.UidFactory;

interface EventFactoryInternal extends EventFactory {
  UidFactory getUidFactory();

  ChannelCache getChannelCache(Uid<Channel> channelUid);
}
