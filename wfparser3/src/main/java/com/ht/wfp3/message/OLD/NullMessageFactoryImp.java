package com.ht.wfp3.message.OLD;

import java.util.Set;
import com.ht.uid.Uid;

final class NullMessageFactoryImp implements MessageFactory {

  @Override
  public Uid<Priority> getPriorityUid(String string) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Uid<Priority> addPriority(String uidKey) throws ConstraintViolationException {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Priority getPriority(Uid<Priority> priorityUid) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Set<Uid<Priority>> getPriorityUidSet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");

  }

  @Override
  public Uid<Topic> addTopic(String topicUidKey) throws ConstraintViolationException {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Topic getTopic(Uid<Topic> topicUid) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Uid<Topic> getTopicUid(String topicUidKey) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Set<Uid<Topic>> getTopicUidSet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Uid<Description> addDescription(String descriptionUidKey)
      throws ConstraintViolationException {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Description getDescription(Uid<Description> descriptionUid) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Uid<Description> getDescriptionUid(String descriptionUidKey) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Set<Uid<Description>> getDescriptionUidSet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Uid<Message> addMessage(Uid<Topic> topicUid, Uid<Priority> priorityUid,
      Uid<Description> descriptionUid) throws ConstraintViolationException {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Message getMessage(Uid<Message> messageUid) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Uid<Message> getMessageUid(String messageUidKey) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public Set<Uid<Message>> getMessageUidSet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }
}
