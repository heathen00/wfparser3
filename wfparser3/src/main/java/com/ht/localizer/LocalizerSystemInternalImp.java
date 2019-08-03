package com.ht.localizer;

import com.ht.uid.UID;

import java.util.Set;

final class LocalizerSystemInternalImp implements LocalizerSystemInternal {
  private static LocalizerSystemInternalImp LOCALIZER_SYSTEM_INTERNAL_IMP_SINGLETON = new LocalizerSystemInternalImp();

  public static LocalizerSystemInternalImp getLocalizerSystemInternalImpSingleton() {
    return LOCALIZER_SYSTEM_INTERNAL_IMP_SINGLETON;
  }

  private final LocalizerFactoryInternal factoryInternal;

  private LocalizerSystemInternalImp() {
    factoryInternal = new LocalizerFactoryInternalImp();
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
}
