package com.ht.wfp3.message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class MessageFactoryImp implements MessageFactory {
  private final MessageSystem messageSystem;
  private final Map<UID<Priority>, Priority> priorityMap;
  private final Map<String, UID<Priority>> priorityKeyMap;

  MessageFactoryImp(MessageSystem messageSystem) throws ConstraintViolationException {
    this.messageSystem = messageSystem;
    this.priorityMap = new HashMap<>();
    this.priorityKeyMap = new HashMap<>();
  }

  public UID<Priority> addPriority(String uidKey) throws ConstraintViolationException {
    if (null == uidKey) {
      throw new ConstraintViolationException("priority uidKey cannot be null");
    }
    if (!uidKey.matches("^[a-z.]+$")) {
      throw new ConstraintViolationException(
          "priority uidKey can only contain lower case letters and periods");
    }
    int priorityUidKeyMaxLength =
        messageSystem.getConfig().getConstraints().getPriorityUidKeyMaxLength();
    if (priorityUidKeyMaxLength < uidKey.length()) {
      throw new ConstraintViolationException(
          "priority uidKey length must be less than or equal to " + priorityUidKeyMaxLength);
    }
    if (Localization.UNKNOWN_L10N_KEY
        .equals(messageSystem.getConfig().getLocalization().getPriorityName(uidKey))) {
      throw new ConstraintViolationException(
          "localization not defined for priority uidKey '" + uidKey + "'");
    }
    Priority priority = new PriorityImp(messageSystem, uidKey);
    UID<Priority> priorityUID = priority.getUId();
    if (null != priorityMap.get(priorityUID)) {
      throw new ConstraintViolationException("priority with uid " + uidKey + " already exists");
    }
    priorityMap.put(priorityUID, priority);
    priorityKeyMap.put(priorityUID.getKey(), priorityUID);
    return priorityUID;
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
  public Set<UID<Topic>> getTopicUidSet() {
    // TODO Auto-generated method stub
    return null;
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
