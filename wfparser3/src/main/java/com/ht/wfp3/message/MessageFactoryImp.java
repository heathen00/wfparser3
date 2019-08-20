package com.ht.wfp3.message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.ht.uid.Uid;
import com.ht.uid.UidFactory;

final class MessageFactoryImp implements MessageFactory {
  private UidFactory uidFactory;
  private final MessageSystem messageSystem;
  private final Map<Uid<Priority>, Priority> priorityMap;
  private final Map<String, Uid<Priority>> priorityKeyMap;
  private final Map<Uid<Topic>, Topic> topicMap;
  private final Map<String, Uid<Topic>> topicKeyMap;
  private final Map<Uid<Description>, Description> descriptionMap;
  private final Map<String, Uid<Description>> descriptionKeyMap;
  private final Map<Uid<Message>, Message> messageMap;
  private final Map<String, Uid<Message>> messageKeyMap;

  MessageFactoryImp(UidFactory uidFactory, MessageSystem messageSystem)
      throws ConstraintViolationException {
    this.uidFactory = uidFactory;
    this.messageSystem = messageSystem;
    priorityMap = new HashMap<>();
    priorityKeyMap = new HashMap<>();
    topicMap = new HashMap<>();
    topicKeyMap = new HashMap<>();
    descriptionMap = new HashMap<>();
    descriptionKeyMap = new HashMap<>();
    messageMap = new HashMap<>();
    messageKeyMap = new HashMap<>();
  }

  private void uidKeyValidationGuard(String uidKey) throws ConstraintViolationException {
    if (null == uidKey) {
      throw new ConstraintViolationException("uidKey cannot be null");
    }
    if (!uidKey.matches("^[a-z.]+$")) {
      throw new ConstraintViolationException(
          "uidKey can only contain lower case letters and periods");
    }
    int uidKeyMaxLength = messageSystem.getConfig().getConstraints().getUidKeyMaxLength();
    if (uidKeyMaxLength < uidKey.length()) {
      throw new ConstraintViolationException(
          "uidKey length must be less than or equal to " + uidKeyMaxLength);
    }
  }

  public Uid<Priority> addPriority(String priorityUidKey) throws ConstraintViolationException {
    uidKeyValidationGuard(priorityUidKey);
    if (Localization.UNKNOWN_L10N_KEY
        .equals(messageSystem.getConfig().getLocalization().getPriorityName(priorityUidKey))) {
      throw new ConstraintViolationException(
          "localization not defined for priority uidKey '" + priorityUidKey + "'");
    }
    Priority priority = new PriorityImp(uidFactory, messageSystem, priorityUidKey);
    Uid<Priority> priorityUid = priority.getUid();
    if (null != priorityMap.get(priorityUid)) {
      throw new ConstraintViolationException(
          "priority with uid " + priorityUidKey + " already exists");
    }
    priorityMap.put(priorityUid, priority);
    priorityKeyMap.put(priorityUid.getKey(), priorityUid);
    return priorityUid;
  }

  public Uid<Priority> getPriorityUid(String uidKey) {
    return priorityKeyMap.get(uidKey);
  }

  public Priority getPriority(Uid<Priority> priorityUid) {
    return priorityMap.get(priorityUid);
  }

  @Override
  public Set<Uid<Priority>> getPriorityUidSet() {
    return Collections.unmodifiableSet(priorityMap.keySet());
  }

  @Override
  public Uid<Topic> addTopic(String topicUidKey) throws ConstraintViolationException {
    uidKeyValidationGuard(topicUidKey);
    if (Localization.UNKNOWN_L10N_KEY
        .equals(messageSystem.getConfig().getLocalization().getTopicName(topicUidKey))) {
      throw new ConstraintViolationException(
          "localization not defined for topic uidKey '" + topicUidKey + "'");
    }
    Topic topic = new TopicImp(uidFactory, messageSystem, topicUidKey);
    Uid<Topic> topicUid = topic.getUid();
    if (null != topicMap.get(topicUid)) {
      throw new ConstraintViolationException("topic with uid " + topicUidKey + " already exists");
    }
    topicMap.put(topicUid, topic);
    topicKeyMap.put(topicUidKey, topicUid);
    return topicUid;
  }

  @Override
  public Topic getTopic(Uid<Topic> topicUid) {
    return topicMap.get(topicUid);
  }

  @Override
  public Uid<Topic> getTopicUid(String topicUidKey) {
    return topicKeyMap.get(topicUidKey);
  }

  @Override
  public Set<Uid<Topic>> getTopicUidSet() {
    return Collections.unmodifiableSet(topicMap.keySet());
  }

  @Override
  public Uid<Description> addDescription(String descriptionUidKey)
      throws ConstraintViolationException {
    uidKeyValidationGuard(descriptionUidKey);
    Description description = new DescriptionImp(uidFactory, messageSystem, descriptionUidKey);
    Uid<Description> descriptionUid = description.getUid();
    if (null != descriptionMap.get(descriptionUid)) {
      throw new ConstraintViolationException(
          "description with uid " + descriptionUidKey + " already exists");
    }
    descriptionMap.put(descriptionUid, description);
    descriptionKeyMap.put(descriptionUidKey, descriptionUid);
    return descriptionUid;
  }

  @Override
  public Description getDescription(Uid<Description> descriptionUid) {
    return descriptionMap.get(descriptionUid);
  }

  @Override
  public Uid<Description> getDescriptionUid(String descriptionUidKey) {
    return descriptionKeyMap.get(descriptionUidKey);
  }

  @Override
  public Set<Uid<Description>> getDescriptionUidSet() {
    return Collections.unmodifiableSet(descriptionMap.keySet());
  }

  @Override
  public Uid<Message> addMessage(Uid<Topic> topicUid, Uid<Priority> priorityUid,
      Uid<Description> descriptionUid) throws ConstraintViolationException {
    if (null == topicUid) {
      throw new ConstraintViolationException("topicUid cannot be null");
    }
    if (null == priorityUid) {
      throw new ConstraintViolationException("priorityUid cannot be null");
    }
    if (null == descriptionUid) {
      throw new ConstraintViolationException("descriptionUid cannot be null");
    }
    Message message = new MessageImp(messageSystem, getTopic(topicUid), getPriority(priorityUid),
        getDescription(descriptionUid));
    Uid<Message> messageUid = message.getUid();
    if (null != messageMap.get(messageUid)) {
      throw new ConstraintViolationException("message with uid " + messageUid + " already exists");
    }
    messageMap.put(messageUid, message);
    messageKeyMap.put(messageUid.getKey(), messageUid);
    return messageUid;
  }

  @Override
  public Message getMessage(Uid<Message> messageUid) {
    return messageMap.get(messageUid);
  }

  @Override
  public Uid<Message> getMessageUid(String messageUidKey) {
    return messageKeyMap.get(messageUidKey);
  }

  @Override
  public Set<Uid<Message>> getMessageUidSet() {
    return Collections.unmodifiableSet(messageMap.keySet());
  }
}
