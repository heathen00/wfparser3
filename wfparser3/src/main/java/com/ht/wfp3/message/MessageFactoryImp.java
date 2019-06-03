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

  MessageFactoryImp(MessageSystem messageSystem) throws ConstraintViolationException {
    this.messageSystem = messageSystem;
    priorityMap = new HashMap<>();
    priorityKeyMap = new HashMap<>();
    topicMap = new HashMap<>();
    topicKeyMap = new HashMap<>();
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
    UID<Priority> priorityUid = priority.getUId();
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
    Topic topic = new TopicImp(messageSystem, topicUidKey);
    UID<Topic> topicUid = topic.getUId();
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
  public Set<UID<Message>> getMessageUidSet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addMessageNotYetImplemented() {
    // TODO Auto-generated method stub
  }
}
