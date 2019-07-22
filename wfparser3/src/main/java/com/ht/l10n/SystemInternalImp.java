package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class SystemInternalImp implements SystemInternal {
  private static SystemInternalImp SYSTEM_IMP_SINGLETON = new SystemInternalImp();

  public static SystemInternalImp getSystemImpSingleton() {
    return SYSTEM_IMP_SINGLETON;
  }

  private FactoryInternal factoryInternal;
  private Set<LocalizerInternal> localizerInternalSet;

  private SystemInternalImp() {
    factoryInternal = new FactoryInternalImp();
    localizerInternalSet = new HashSet<>();
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
  public Set<Localizer> getLocalizerSet() {
    return Collections.unmodifiableSet(localizerInternalSet);
  }

  @Override
  public Localizer getLocalizer(UID<Localizer> localizerUid) {
    if (null == localizerUid) {
      throw new NullPointerException("localizerUid cannot be null");
    }
    return null;
  }
}
