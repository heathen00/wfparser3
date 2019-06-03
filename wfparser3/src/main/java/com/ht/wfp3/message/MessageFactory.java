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

  Set<UID<Message>> getMessageUidSet();
}
