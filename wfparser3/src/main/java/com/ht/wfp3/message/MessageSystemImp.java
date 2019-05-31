package com.ht.wfp3.message;

import java.util.List;

final class MessageSystemImp implements MessageSystem {
  final static class ConfigImp implements MessageSystem.Config {
    private final Localization localization;

    ConfigImp() {
      localization = new LocalizationImp();
    }

    @Override
    public Localization getLocalization() {
      return localization;
    }
  }

  static final MessageSystem SINGLETON = new MessageSystemImp();

  private final MessageFactory messageFactory;
  private final MessageSystem.Config config;

  MessageSystemImp() {
    messageFactory = new MessageFactory();
    config = new MessageSystemImp.ConfigImp();
  }

  @Override
  public MessageFactory createMessageFactory() {
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
  public List<Priority> getPriorityList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Topic> getTopicList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Message> getMessageList(Topic topic) {
    // TODO Auto-generated method stub
    return null;
  }
}
