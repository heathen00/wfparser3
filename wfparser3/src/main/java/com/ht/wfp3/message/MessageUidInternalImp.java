package com.ht.wfp3.message;

import com.ht.uid.Uid;

public class MessageUidInternalImp implements MessageUidInternal {
  private final Message message;

  MessageUidInternalImp(Message message) {
    this.message = message;
  }

  @Override
  public String getKey() {
    return "" + message.getTopic().getUid().getKey() + "."
        + message.getDescription().getUid().getKey();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + message.getTopic().getUid().hashCode();
    result = prime * result + message.getDescription().getUid().hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MessageUidInternalImp other = (MessageUidInternalImp) obj;
    if (!message.getTopic().getUid().equals(other.message.getTopic().getUid())) {
      return false;
    }
    if (!message.getDescription().getUid().equals(other.message.getDescription().getUid())) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "MessageUidImp [topicUid=" + message.getTopic().getUid() + ", priorityUid="
        + message.getPriority().getUid() + ", descriptionUid=" + message.getDescription().getUid()
        + "]";
  }

  @Override
  public int compareTo(Uid<Message> o) {
    MessageUidInternalImp messageUid = (MessageUidInternalImp) o;
    int compareTo =
        message.getTopic().getUid().compareTo(messageUid.getMessage().getTopic().getUid());
    if (0 == compareTo) {
      compareTo = message.getDescription().getUid()
          .compareTo(messageUid.getMessage().getDescription().getUid());
    }
    return compareTo;
  }

  @Override
  public Message getMessage() {
    return message;
  }
}
