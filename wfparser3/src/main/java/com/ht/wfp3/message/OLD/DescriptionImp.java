package com.ht.wfp3.message.OLD;

import com.ht.uid.Uid;
import com.ht.uid.UidFactory;

public class DescriptionImp implements Description {
  private final MessageSystem messageSystem;
  private final Uid<Description> uid;

  DescriptionImp(UidFactory uidFactory, MessageSystem messageSystem, String descriptionUidKey) {
    this.messageSystem = messageSystem;
    this.uid = uidFactory.createUid(descriptionUidKey, this);
  }

  @Override
  public Uid<Description> getUid() {
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
