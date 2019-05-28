package com.ht.wfp3.message;

/**
 * The messenger is the client interface used to send messages in the messaging system.
 * 
 * @author nickl
 *
 */
public interface Messenger {

  /**
   * Locate a message instance for the specified topic and message key. The message key is the same
   * value as what is returned in Message.getUniqueId().getKey().
   * 
   * @param topicName
   * @param messageKey
   * @return
   */
  UID<Message> getMessageUid(String topicName, String messageKey);

  void sendMessage(UID<Message> messageUID, Object... parameters);

  void deleteMessage(UID<Message> messageUID);
}
