package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class SystemInternalImp implements SystemInternal {
  private static SystemInternalImp SYSTEM_IMP_SINGLETON = new SystemInternalImp();

  public static SystemInternalImp getSystemImpSingleton() {
    return SYSTEM_IMP_SINGLETON;
  }

  private FactoryInternal factoryInternal;
  private final Map<UID<Localizer>, Localizer> localizerInternalMap;

  private SystemInternalImp() {
    factoryInternal = new FactoryInternalImp();
    localizerInternalMap = new HashMap<>();
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
    return Collections.unmodifiableSet(localizerInternalMap.keySet());
  }

  @Override
  public Localizer getLocalizer(UID<Localizer> localizerUid) {
    if (null == localizerUid) {
      throw new NullPointerException("localizerUid cannot be null");
    }
    return null;
  }
}
