package com.ht.wfp3.message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class MessageFactoryImp implements MessageFactory {
  private final MessageSystem messageSystem;
  private final Map<UID<Priority>, Priority> priorityMap;
  private final Map<String, UID<Priority>> priorityKeyMap;
  private final Map<UID<Topic>, Topic> topicMap;
  private final Map<String, UID<Topic>> topicKeyMap;
  private final Map<UID<Description>, Description> descriptionMap;
  private final Map<String, UID<Description>> descriptionKeyMap;
  private final Map<UID<Message>, Message> messageMap;
  private final Map<String, UID<Message>> messageKeyMap;

  MessageFactoryImp(MessageSystem messageSystem) throws ConstraintViolationException {
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

  public UID<Priority> addPriority(String priorityUidKey) throws ConstraintViolationException {
    uidKeyValidationGuard(priorityUidKey);
    if (Localization.UNKNOWN_L10N_KEY
        .equals(messageSystem.getConfig().getLocalization().getPriorityName(priorityUidKey))) {
      throw new ConstraintViolationException(
          "localization not defined for priority uidKey '" + priorityUidKey + "'");
    }
    Priority priority = new PriorityImp(messageSystem, priorityUidKey);
    UID<Priority> priorityUid = priority.getUid();
    if (null != priorityMap.get(priorityUid)) {
      throw new ConstraintViolationException(
          "priority with uid " + priorityUidKey + " already exists");
    }
    priorityMap.put(priorityUid, priority);
    priorityKeyMap.put(priorityUid.getKey(), priorityUid);
    return priorityUid;
  }

  public UID<Priority> getPriorityUid(String uidKey) {
    return priorityKeyMap.get(uidKey);
  }

  public Priority getPriority(UID<Priority> priorityUid) {
    return priorityMap.get(priorityUid);
  }

  @Override
  public Set<UID<Priority>> getPriorityUidSet() {
    return Collections.unmodifiableSet(priorityMap.keySet());
  }

  @Override
  public UID<Topic> addTopic(String topicUidKey) throws ConstraintViolationException {
    uidKeyValidationGuard(topicUidKey);
    if (Localization.UNKNOWN_L10N_KEY
        .equals(messageSystem.getConfig().getLocalization().getTopicName(topicUidKey))) {
      throw new ConstraintViolationException(
          "localization not defined for topic uidKey '" + topicUidKey + "'");
    }
    Topic topic = new TopicImp(messageSystem, topicUidKey);
    UID<Topic> topicUid = topic.getUid();
    if (null != topicMap.get(topicUid)) {
      throw new ConstraintViolationException("topic with uid " + topicUidKey + " already exists");
    }
    topicMap.put(topicUid, topic);
    topicKeyMap.put(topicUidKey, topicUid);
    return topicUid;
  }

  @Override
  public Topic getTopic(UID<Topic> topicUid) {
    return topicMap.get(topicUid);
  }

  @Override
  public UID<Topic> getTopicUid(String topicUidKey) {
    return topicKeyMap.get(topicUidKey);
  }

  @Override
  public Set<UID<Topic>> getTopicUidSet() {
    return Collections.unmodifiableSet(topicMap.keySet());
  }

  @Override
  public UID<Description> addDescription(String descriptionUidKey)
      throws ConstraintViolationException {
    uidKeyValidationGuard(descriptionUidKey);
    Description description = new DescriptionImp(messageSystem, descriptionUidKey);
    UID<Description> descriptionUid = description.getUid();
    if (null != descriptionMap.get(descriptionUid)) {
      throw new ConstraintViolationException(
          "description with uid " + descriptionUidKey + " already exists");
    }
    descriptionMap.put(descriptionUid, description);
    descriptionKeyMap.put(descriptionUidKey, descriptionUid);
    return descriptionUid;
  }

  @Override
  public Description getDescription(UID<Description> descriptionUid) {
    return descriptionMap.get(descriptionUid);
  }

  @Override
  public UID<Description> getDescriptionUid(String descriptionUidKey) {
    return descriptionKeyMap.get(descriptionUidKey);
  }

  @Override
  public Set<UID<Description>> getDescriptionUidSet() {
    return Collections.unmodifiableSet(descriptionMap.keySet());
  }

  @Override
  public UID<Message> addMessage(UID<Topic> topicUid, UID<Priority> priorityUid,
      UID<Description> descriptionUid) throws ConstraintViolationException {
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
    UID<Message> messageUid = message.getUid();
    if (null != messageMap.get(messageUid)) {
      throw new ConstraintViolationException("message with uid " + messageUid + " already exists");
    }
    messageMap.put(messageUid, message);
    messageKeyMap.put(messageUid.getKey(), messageUid);
    return messageUid;
  }

  @Override
  public Message getMessage(UID<Message> messageUid) {
    return messageMap.get(messageUid);
  }

  @Override
  public UID<Message> getMessageUid(String messageUidKey) {
    return messageKeyMap.get(messageUidKey);
  }

  @Override
  public Set<UID<Message>> getMessageUidSet() {
    return Collections.unmodifiableSet(messageMap.keySet());
  }
}
