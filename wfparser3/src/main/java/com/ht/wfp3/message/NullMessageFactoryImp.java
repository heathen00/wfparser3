package com.ht.wfp3.message;

import java.util.Set;

final class NullMessageFactoryImp implements MessageFactory {

  @Override
  public UID<Priority> getPriorityUid(String string) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public UID<Priority> addPriority(String uidKey) throws ConstraintViolationException {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Priority getPriority(UID<Priority> priorityUid) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Set<UID<Priority>> getPriorityUidSet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");

  }

  @Override
  public UID<Topic> addTopic(String topicUidKey) throws ConstraintViolationException {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Topic getTopic(UID<Topic> topicUid) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public UID<Topic> getTopicUid(String topicUidKey) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Set<UID<Topic>> getTopicUidSet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public UID<Description> addDescription(String descriptionUidKey)
      throws ConstraintViolationException {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Description getDescription(UID<Description> descriptionUid) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public UID<Description> getDescriptionUid(String descriptionUidKey) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Set<UID<Description>> getDescriptionUidSet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public UID<Message> addMessage(UID<Topic> topicUid, UID<Priority> priorityUid,
      UID<Description> descriptionUid) throws ConstraintViolationException {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Message getMessage(UID<Message> messageUid) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public UID<Message> getMessageUid(String messageUidKey) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Set<UID<Message>> getMessageUidSet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }
}
