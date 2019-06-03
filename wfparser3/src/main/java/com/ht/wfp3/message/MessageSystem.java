package com.ht.wfp3.message;

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
    return MessageSystemInternalImp.SINGLETON;
  }

  void resetToDefault();

  Config getConfig();

  Messenger createMessenger();
}
