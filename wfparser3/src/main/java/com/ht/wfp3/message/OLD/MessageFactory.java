package com.ht.wfp3.message.OLD;

import java.util.Set;
import com.ht.uid.Uid;

interface MessageFactory {
  Uid<Priority> addPriority(String priorityUidKey) throws ConstraintViolationException;

  Priority getPriority(Uid<Priority> priorityUid);

  Uid<Priority> getPriorityUid(String priorityUidKey);

  Set<Uid<Priority>> getPriorityUidSet();

  Uid<Topic> addTopic(String topicUidKey) throws ConstraintViolationException;

  Topic getTopic(Uid<Topic> topicUid);

  Uid<Topic> getTopicUid(String topicUidKey);

  Set<Uid<Topic>> getTopicUidSet();

  Uid<Description> addDescription(String descriptionUidKey) throws ConstraintViolationException;

  Description getDescription(Uid<Description> descriptionUid);

  Uid<Description> getDescriptionUid(String descriptionUidKey);

  Set<Uid<Description>> getDescriptionUidSet();

  Uid<Message> addMessage(Uid<Topic> topicUid, Uid<Priority> priorityUid,
      Uid<Description> descriptionUid) throws ConstraintViolationException;

  Message getMessage(Uid<Message> messageUid);

  Uid<Message> getMessageUid(String messageUidKey);

  Set<Uid<Message>> getMessageUidSet();
}
