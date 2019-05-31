package com.ht.wfp3.message;

public final class MessageFactory {
  MessageFactory() {

  }

  Priority createPriority(String uidKey, String name) throws ConstraintViolationException {
    if (null == uidKey) {
      throw new ConstraintViolationException();
    }
    return null;
  }
}
