package com.ht.localizer;

import com.ht.uid.UID;

import java.util.Set;

final class SystemInternalImp implements SystemInternal {
  private static SystemInternalImp SYSTEM_INTERNAL_IMP_SINGLETON = new SystemInternalImp();

  public static SystemInternalImp getSystemInternalImpSingleton() {
    return SYSTEM_INTERNAL_IMP_SINGLETON;
  }

  private final LocalizerFactoryInternal factoryInternal;

  private SystemInternalImp() {
    factoryInternal = new LocalizerFactoryInternalImp();
  }

  @Override
  public LocalizerFactory getFactory() {
    return getFactoryInternal();
  }

  @Override
  public LocalizerFactoryInternal getFactoryInternal() {
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
