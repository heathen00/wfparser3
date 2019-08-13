package com.ht.connect;

import com.ht.localizer.LocalizerFactory;
import com.ht.uid.UidFactory;
import com.ht.wrap.WrapperFactory;

public interface Connector {
  void setUidFactory(UidFactory uidFactory);

  UidFactory getUidFactory();

  void setWrapperFactory(WrapperFactory wrapperFactory);

  WrapperFactory getWrapperFactory();

  void setLocalizerFactory(LocalizerFactory localizerFactory);

  LocalizerFactory getLocalizerFactory();
}
