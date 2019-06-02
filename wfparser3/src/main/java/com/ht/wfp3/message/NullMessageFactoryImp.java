package com.ht.wfp3.message;

import java.util.Set;

public class NullMessageFactoryImp implements MessageFactory {

  @Override
  public UID<Priority> getPriorityUid(String string) {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public UID<Priority> createPriorityUid(Priority priority, String uidKey) {
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
  public Set<UID<Priority>> getPriorityKeySet() {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }

  @Override
  public void addMessageNotYetImplemented() {
    throw new UnsupportedOperationException("message system failed to initialize properly");
  }
}
