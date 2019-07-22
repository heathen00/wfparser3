package com.ht.l10n;

class SystemInternalImp implements SystemInternal {
  private static SystemInternalImp SYSTEM_IMP_SINGLETON = new SystemInternalImp();

  public static SystemInternalImp getSystemImpSingleton() {
    return SYSTEM_IMP_SINGLETON;
  }

  private FactoryInternal factoryInternal;

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
}
