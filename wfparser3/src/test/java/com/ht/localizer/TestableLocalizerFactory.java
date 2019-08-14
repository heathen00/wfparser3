package com.ht.localizer;

import java.util.Locale;
import com.ht.uid.UidFactory;
import com.ht.wrap.WrapperFactory;

public final class TestableLocalizerFactory
    implements LocalizerFactory, CanReset, ConfigurableWrapperFactory, ConfigurableUidFactory {

  public static TestableLocalizerFactory getTestableLocalizerFactory(
      final LocalizerSystem localizerSystem) {
    return new TestableLocalizerFactory((LocalizerSystemInternal) localizerSystem);
  }

  private final LocalizerSystemInternal localizerSystemInternal;

  private TestableLocalizerFactory(final LocalizerSystemInternal localizerSystemInternal) {
    this.localizerSystemInternal = localizerSystemInternal;
  }

  @Override
  public void setWrapperFactory(WrapperFactory wrapperFactory) {
    localizerSystemInternal.getLocalizerFactoryInternal().setWrapperFactory(wrapperFactory);
  }

  @Override
  public WrapperFactory getWrapperFactory() {
    return localizerSystemInternal.getLocalizerFactoryInternal().getWrapperFactory();
  }

  @Override
  public void resetAll() {
    localizerSystemInternal.getLocalizerFactoryInternal().resetAll();
  }

  @Override
  public Localizer createLocalizer(String name, Locale locale) throws LocalizerException {
    return localizerSystemInternal.getLocalizerFactoryInternal().createLocalizer(name, locale);
  }

  @Override
  public LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException {
    return localizerSystemInternal.getLocalizerFactoryInternal().createLocalizerBundle(localizer,
        resourceBundleName);
  }

  @Override
  public LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String methodName) throws LocalizerException {
    return localizerSystemInternal.getLocalizerFactoryInternal().createLocalizerType(localizer,
        groupName, typeName, methodName);
  }

  @Override
  public LocalizerInstance createLocalizerInstance(LocalizerType localizerType, String instanceName)
      throws LocalizerException {
    return localizerSystemInternal.getLocalizerFactoryInternal()
        .createLocalizerInstance(localizerType, instanceName);
  }

  @Override
  public void setUidFactory(UidFactory uidFactory) {
    localizerSystemInternal.getLocalizerFactoryInternal().setUidFactory(uidFactory);
  }

  @Override
  public UidFactory getUidFactory() {
    return localizerSystemInternal.getLocalizerFactoryInternal().getUidFactory();
  }

  public Localizer getUndefinedLocalizer() {
    return localizerSystemInternal.getLocalizerFactoryInternal().createUndefinedLocalizer();
  }
}
