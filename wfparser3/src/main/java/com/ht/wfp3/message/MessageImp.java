package com.ht.wfp3.message;

final class MessageImp implements Message {

  private final MessageSystem messageSystem;
  private final UID<Message> messageUid;
  private final Topic topic;
  private Priority priority;
  private final Description description;

  MessageImp(MessageSystem messageSystem, Topic topic, Priority priority, Description description) {
    this.messageSystem = messageSystem;
    this.topic = topic;
    this.priority = priority;
    this.description = description;
    this.messageUid = new MessageUidImp<Message>(this);
  }

  @Override
  public UID<Message> getUid() {
    return messageUid;
  }

  @Override
  public Priority getPriority() {
    return priority;
  }

  @Override
  public void setPriority(UID<Priority> priorityUid) {
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
