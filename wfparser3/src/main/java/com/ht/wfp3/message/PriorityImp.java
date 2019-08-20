package com.ht.wfp3.message;

import com.ht.uid.Uid;
import com.ht.uid.UidFactory;

final class PriorityImp implements Priority {

  private final MessageSystem messageSystem;
  private final Uid<Priority> uid;

  PriorityImp(UidFactory uidFactory, MessageSystem messageSystem, String priorityUidKey) {
    this.messageSystem = messageSystem;
    uid = uidFactory.createUid(priorityUidKey, this);
  }

  @Override
  public Uid<Priority> getUid() {
    return uid;
  }

  @Override
  public String getName() {
    return messageSystem.getConfig().getLocalization().getPriorityName(uid.getKey());
  }
}
