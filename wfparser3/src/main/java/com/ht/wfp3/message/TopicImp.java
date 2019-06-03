package com.ht.wfp3.message;

class TopicImp implements Topic {

  private final MessageSystem messageSystem;
  private final UID<Topic> uid;

  TopicImp(MessageSystem messageSystem, String topicUidKey) {
    this.messageSystem = messageSystem;
    this.uid = new UIDImp<Topic>(this, topicUidKey);
  }

  @Override
  public UID<Topic> getUId() {
    return uid;
  }

  @Override
  public String getName() {
    return messageSystem.getConfig().getLocalization().getTopicName(uid.getKey());
  }
}
