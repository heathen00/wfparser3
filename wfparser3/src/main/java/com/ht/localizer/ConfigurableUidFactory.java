package com.ht.localizer;

import com.ht.uid.UidFactory;

interface ConfigurableUidFactory {
  void setUidFactory(UidFactory uidFactory);

  UidFactory getUidFactory();
}
