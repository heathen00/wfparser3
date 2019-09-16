package com.nickmlanglois.localizer;

import com.nickmlanglois.uid.UidFactory;

interface ConfigurableUidFactory {
  void setUidFactory(UidFactory uidFactory);

  UidFactory getUidFactory();
}
