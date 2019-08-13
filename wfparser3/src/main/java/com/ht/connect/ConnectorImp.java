package com.ht.connect;

import com.ht.localizer.LocalizerFactory;
import com.ht.uid.UidFactory;
import com.ht.wrap.WrapperFactory;

public class ConnectorImp implements Connector {
  private UidFactory uidFactory;
  private WrapperFactory wrapperFactory;
  private LocalizerFactory localizerFactory;

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

  @Override
  public void setWrapperFactory(WrapperFactory wrapperFactory) {
    if (null == wrapperFactory) {
      throw new NullPointerException("wrapperFactory cannot be null");
    }
    this.wrapperFactory = wrapperFactory;
  }

  @Override
  public WrapperFactory getWrapperFactory() {
    if (null == wrapperFactory) {
      throw new UnsupportedOperationException("wrapperFactory must be set before getting it");
    }
    return wrapperFactory;
  }

  @Override
  public void setLocalizerFactory(LocalizerFactory localizerFactory) {
    if (null == localizerFactory) {
      throw new NullPointerException("localizerFactory cannot be null");
    }
    this.localizerFactory = localizerFactory;
  }

  @Override
  public LocalizerFactory getLocalizerFactory() {
    if (null == localizerFactory) {
      throw new UnsupportedOperationException("localizerFactory must be set before getting it");
    }
    return localizerFactory;
  }
}
