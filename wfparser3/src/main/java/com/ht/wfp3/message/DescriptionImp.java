package com.ht.wfp3.message;

public class DescriptionImp implements Description {
  private final MessageSystem messageSystem;
  private final UID<Description> uid;

  DescriptionImp(MessageSystem messageSystem, String descriptionUidKey) {
    this.messageSystem = messageSystem;
    this.uid = new UIDImp<Description>(this, descriptionUidKey);
  }

  @Override
  public UID<Description> getUid() {
    return uid;
  }

  @Override
  public String getFormattedText(Object... parameters) {
    return messageSystem.getConfig().getLocalization().getDescriptionFormattedText(uid.getKey(),
        parameters);
  }

  @Override
  public String getUnformattedText() {
    return messageSystem.getConfig().getLocalization().getDescriptionUnformattedText(uid.getKey());
  }
}
