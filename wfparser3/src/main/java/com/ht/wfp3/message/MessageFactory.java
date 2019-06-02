package com.ht.wfp3.message;

import java.util.Set;

public interface MessageFactory {
  void addMessageNotYetImplemented();

  UID<Priority> addPriority(String string) throws ConstraintViolationException;

  Set<UID<Priority>> getPriorityKeySet();

  Priority getPriority(UID<Priority> priorityUid);

  UID<Priority> getPriorityUid(String string);

  UID<Priority> createPriorityUid(Priority priority, String uidKey);
}
