package com.ht.l10n;

import com.ht.common.UID;

import java.util.Set;

final class SystemInternalImp implements SystemInternal {
  private static SystemInternalImp SYSTEM_IMP_SINGLETON = new SystemInternalImp();

  public static SystemInternalImp getSystemImpSingleton() {
    return SYSTEM_IMP_SINGLETON;
  }

  private final FactoryInternal factoryInternal;

  private SystemInternalImp() {
    factoryInternal = new FactoryInternalImp();
  }

  @Override
  public Factory getFactory() {
    return getFactoryInternal();
  }

  @Override
  public FactoryInternal getFactoryInternal() {
    return factoryInternal;
  }

  @Override
  public Set<UID<Localizer>> getLocalizerKeySet() {
    return factoryInternal.getLocalizerKeySet();
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
