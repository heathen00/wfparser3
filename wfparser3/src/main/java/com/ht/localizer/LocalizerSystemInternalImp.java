package com.ht.localizer;

import com.ht.uid.UID;
import com.ht.uid.UidFactory;
import com.ht.wrap.WrapperFactory;
import java.util.Set;

final class LocalizerSystemInternalImp implements LocalizerSystemInternal {
  private final LocalizerFactoryInternal factoryInternal;

  LocalizerSystemInternalImp() {
    factoryInternal = new LocalizerFactoryInternalImp(this);
  }

  @Override
  public LocalizerFactory getLocalizerFactory() {
    return getLocalizerFactoryInternal();
  }

  @Override
  public LocalizerFactoryInternal getLocalizerFactoryInternal() {
    return factoryInternal;
  }

  @Override
  public Set<UID<Localizer>> getLocalizerUidSet() {
    return factoryInternal.getLocalizerUidSet();
  }

  @Override
  public Localizer getLocalizer(UID<Localizer> localizerUid) {
    return factoryInternal.getLocalizer(localizerUid);
  }

  @Override
  public void resetAll() {
    factoryInternal.resetAll();
  }

  @Override
  public WrapperFactory createWrapperFactory() {
    return WrapperFactory.createWrapperFactory();
  }

  @Override
  public UidFactory createUidFactory() {
    return UID.createUidFactory();
  }
}
