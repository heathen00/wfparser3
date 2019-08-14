package com.ht.localizer;

import java.util.Locale;
import com.ht.uid.UidFactory;
import com.ht.wrap.WrapperFactory;

public final class TestableLocalizerFactory
    implements LocalizerFactory, ConfigurableWrapperFactory, ConfigurableUidFactory {

  public static TestableLocalizerFactory getTestableLocalizerFactory(WrapperFactory wrapperFactory,
      UidFactory uidFactory) {
    return new TestableLocalizerFactory(wrapperFactory, uidFactory);
  }

  private final LocalizerFactoryInternal localizerFactoryInternal;

  private TestableLocalizerFactory(WrapperFactory wrapperFactory, UidFactory uidFactory) {
    this.localizerFactoryInternal = new LocalizerFactoryInternalImp(wrapperFactory, uidFactory);
  }

  @Override
  public void setWrapperFactory(WrapperFactory wrapperFactory) {
    localizerFactoryInternal.setWrapperFactory(wrapperFactory);
  }

  @Override
  public WrapperFactory getWrapperFactory() {
    return localizerFactoryInternal.getWrapperFactory();
  }

  @Override
  public void resetAll() {
    localizerFactoryInternal.resetAll();
  }

  @Override
  public Localizer createLocalizer(String name, Locale locale) throws LocalizerException {
    return localizerFactoryInternal.createLocalizer(name, locale);
  }

  @Override
  public LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException {
    return localizerFactoryInternal.createLocalizerBundle(localizer, resourceBundleName);
  }

  @Override
  public LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String methodName) throws LocalizerException {
    return localizerFactoryInternal.createLocalizerType(localizer, groupName, typeName, methodName);
  }

  @Override
  public LocalizerInstance createLocalizerInstance(LocalizerType localizerType, String instanceName)
      throws LocalizerException {
    return localizerFactoryInternal.createLocalizerInstance(localizerType, instanceName);
  }

  @Override
  public void setUidFactory(UidFactory uidFactory) {
    localizerFactoryInternal.setUidFactory(uidFactory);
  }

  @Override
  public UidFactory getUidFactory() {
    return localizerFactoryInternal.getUidFactory();
  }

  public Localizer getUndefinedLocalizer() {
    return localizerFactoryInternal.createUndefinedLocalizer();
  }
}
