package com.ht.wfp3.message;

import java.util.Set;

final class MessageSystemImp implements MessageSystem {
  final static class ConfigImp implements MessageSystem.Config {
    private final static int INIT_PRIORITY_UID_KEY_MAXIMUM_LENGTH = 50;
    private final Localization localization;
    private final Constraints constraints;

    ConfigImp() {
      localization = new LocalizationImp();
      constraints = new ConstraintsImp(INIT_PRIORITY_UID_KEY_MAXIMUM_LENGTH);
    }

    @Override
    public Localization getLocalization() {
      return localization;
    }

    @Override
    public Constraints getConstraints() {
      return constraints;
    }
  }

  static final MessageSystem SINGLETON = new MessageSystemImp();

  private MessageFactory messageFactory;
  private MessageSystem.Config config;

  MessageSystemImp() {
    internalResetToDefault();
  }

  private void internalResetToDefault() {
    config = new MessageSystemImp.ConfigImp();
    try {
      messageFactory = new MessageFactoryImp(this);
      messageFactory.addPriority("undefined");
      messageFactory.addPriority("debug");
      messageFactory.addPriority("info");
      messageFactory.addPriority("warn");
      messageFactory.addPriority("error");
    } catch (ConstraintViolationException cve) {
      System.out.println("cve: " + cve);
      messageFactory = new NullMessageFactoryImp();
    }
  }

  @Override
  public MessageFactory getMessageFactory() {
    return messageFactory;
  }

  @Override
  public MessageSystem.Config getConfig() {
    return config;
  }

  @Override
  public Messenger createMessenger() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void resetToDefault() {
    internalResetToDefault();
  }

  @Override
  public Set<UID<Priority>> getPriorityUidList() {
    return messageFactory.getPriorityKeySet();
  }

  @Override
  public Set<UID<Topic>> getTopicUidList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<UID<Message>> getMessageUidList() {
    // TODO Auto-generated method stub
    return null;
  }
}
