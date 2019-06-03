package com.ht.wfp3.message;

final class PriorityImp implements Priority {

  private final MessageSystem messageSystem;
  private final UID<Priority> uid;

  PriorityImp(MessageSystem messageSystem, String uidKey) {
    this.messageSystem = messageSystem;
    uid = new UIDImp<Priority>(this, uidKey);
  }

  @Override
  public UID<Priority> getUId() {
    return uid;
  }

  @Override
  public String getName() {
    return messageSystem.getConfig().getLocalization().getPriorityName(uid.getKey());
  }
}
