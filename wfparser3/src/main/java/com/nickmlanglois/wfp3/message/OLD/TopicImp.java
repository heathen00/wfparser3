package com.nickmlanglois.wfp3.message.OLD;

import com.nickmlanglois.uid.Uid;
import com.nickmlanglois.uid.UidFactory;

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
