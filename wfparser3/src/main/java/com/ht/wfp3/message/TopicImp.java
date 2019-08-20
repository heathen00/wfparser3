package com.ht.wfp3.message;

import com.ht.uid.Uid;
import com.ht.uid.UidFactory;

class TopicImp implements Topic {

  private final MessageSystem messageSystem;
  private final Uid<Topic> uid;

  TopicImp(UidFactory uidFactory, MessageSystem messageSystem, String topicUidKey) {
    this.messageSystem = messageSystem;
    this.uid = uidFactory.createUid(topicUidKey, this);
  }

  @Override
  public Uid<Topic> getUid() {
    return uid;
  }

  @Override
  public String getName() {
    return messageSystem.getConfig().getLocalization().getTopicName(uid.getKey());
  }
}
