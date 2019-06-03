package com.ht.wfp3.message;

import java.util.Set;

interface MessageFactory {
  void addMessageNotYetImplemented();

  UID<Priority> addPriority(String string) throws ConstraintViolationException;

  Priority getPriority(UID<Priority> priorityUid);

  UID<Priority> getPriorityUid(String string);


  Set<UID<Priority>> getPriorityUidSet();

  Set<UID<Topic>> getTopicUidSet();

  Set<UID<Message>> getMessageUidSet();
}
