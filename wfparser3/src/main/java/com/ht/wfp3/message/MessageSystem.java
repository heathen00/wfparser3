package com.ht.wfp3.message;

import java.util.Set;

/**
 * The message system represents the system used for messaging. This is the starting point for
 * clients that intend to use the messaging system.
 * 
 * @author nickl
 *
 */
public interface MessageSystem {
  public interface Config {
    Constraints getConstraints();

    Localization getLocalization();

  }

  static MessageSystem createMessageSystem() {
    return MessageSystemImp.SINGLETON;
  }

  void resetToDefault();

  Config getConfig();

  MessageFactory getMessageFactory();

  Messenger createMessenger();

  Set<UID<Priority>> getPriorityUidList();

  Set<UID<Topic>> getTopicUidList();

  Set<UID<Message>> getMessageUidList();
}
