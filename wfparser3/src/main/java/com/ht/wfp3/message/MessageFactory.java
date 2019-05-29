package com.ht.wfp3.message;

final class MessageFactory {
  private static final MessageFactory SINGLETON = new MessageFactory();

  static MessageFactory createMessageFactory() {
    return SINGLETON;
  }

  Priority createPriority(String uidKey, String name) throws ConstraintViolationException {
    if (null == uidKey) {
      throw new ConstraintViolationException();
    }
    return null;
  }
}
