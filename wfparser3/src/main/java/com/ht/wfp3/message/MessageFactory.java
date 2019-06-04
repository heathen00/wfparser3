package com.ht.wfp3.message;

import java.util.Set;

interface MessageFactory {
  void addMessageNotYetImplemented();

  UID<Priority> addPriority(String priorityUidKey) throws ConstraintViolationException;

  Priority getPriority(UID<Priority> priorityUid);

  UID<Priority> getPriorityUid(String priorityUidKey);

  Set<UID<Priority>> getPriorityUidSet();

  UID<Topic> addTopic(String topicUidKey) throws ConstraintViolationException;

  Topic getTopic(UID<Topic> topicUid);

  UID<Topic> getTopicUid(String topicUidKey);

  Set<UID<Topic>> getTopicUidSet();

  UID<Description> addDescription(String descriptionUidKey) throws ConstraintViolationException;

  Description getDescription(UID<Description> descriptionUid);

  UID<Description> getDescriptionUid(String descriptionUidKey);

  Set<UID<Description>> getDescriptionUidSet();

  Set<UID<Message>> getMessageUidSet();
}
