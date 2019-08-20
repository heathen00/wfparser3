package com.ht.wfp3.message;

import com.ht.uid.Uid;

final class MessageImp implements Message {

  private final MessageSystem messageSystem;
  private final Uid<Message> messageUid;
  private final Topic topic;
  private Priority priority;
  private final Description description;

  MessageImp(MessageSystem messageSystem, Topic topic, Priority priority, Description description) {
    this.messageSystem = messageSystem;
    this.topic = topic;
    this.priority = priority;
    this.description = description;
    this.messageUid = new MessageUidInternalImp(this);
  }

  @Override
  public Uid<Message> getUid() {
    return messageUid;
  }

  @Override
  public Priority getPriority() {
    return priority;
  }

  @Override
  public void setPriority(Uid<Priority> priorityUid) {
    priority = ((MessageSystemInternal) messageSystem).getMessageFactory().getPriority(priorityUid);
  }

  @Override
  public Topic getTopic() {
    return topic;
  }

  @Override
  public Description getDescription() {
    return description;
  }
}
