package com.ht.wfp3.message;

public class MessageUidImp<T extends Message> implements UID<Message> {
  private final Message component;

  MessageUidImp(Message message) {
    this.component = message;
  }

  @Override
  public String getKey() {
    return "" + component.getTopic().getUid().getKey() + "."
        + component.getDescription().getUid().getKey();
  }

  @Override
  public Message getComponent() {
    return component;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + component.getTopic().getUid().hashCode();
    result = prime * result + component.getDescription().getUid().hashCode();
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
    MessageUidImp<?> other = (MessageUidImp<?>) obj;
    if (!component.getTopic().getUid().equals(other.component.getTopic().getUid())) {
      return false;
    }
    if (!component.getDescription().getUid().equals(other.component.getDescription().getUid())) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "MessageUidImp [topicUid=" + component.getTopic().getUid() + ", priorityUid="
        + component.getPriority().getUid() + ", descriptionUid="
        + component.getDescription().getUid() + "]";
  }
}
