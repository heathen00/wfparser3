package com.ht.wfp3.message;

import com.ht.uid.Uid;

/**
 * The messenger is the client interface used to send messages in the messaging system.
 * 
 * @author nickl
 *
 */
public interface Messenger {

  /**
   * Locate a message instance for the specified topic and message key. The message key is the same
   * value as what is returned in Message.getUId().getKey().
   * 
   * @param topicName
   * @param messageKey
   * @return
   */
  Uid<Message> getMessageUid(String topicName, String messageKey);

  void sendMessage(Uid<Message> messageUid, Object... parameters);

  void deleteMessage(Uid<Message> messageUid);
}
