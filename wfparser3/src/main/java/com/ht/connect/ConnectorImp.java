package com.ht.connect;

import com.ht.uid.UidFactory;

public class ConnectorImp implements Connector {
  private UidFactory uidFactory;

  @Override
  public void setUidFactory(UidFactory uidFactory) {
    if (null == uidFactory) {
      throw new NullPointerException("uidFactory cannot be null");
    }
    this.uidFactory = uidFactory;
  }

  @Override
  public UidFactory getUidFactory() {
    if (null == uidFactory) {
      throw new UnsupportedOperationException("uidFactory must be set before getting it");
    }
    return uidFactory;
  }
}
