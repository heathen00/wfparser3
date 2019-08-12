package com.ht.connect;

import com.ht.uid.UidFactory;

public interface Connector {
  void setUidFactory(UidFactory uidFactory);

  UidFactory getUidFactory();
}
