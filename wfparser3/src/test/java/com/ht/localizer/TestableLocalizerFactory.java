package com.ht.localizer;

import com.ht.localizer.CanReset;
import com.ht.localizer.ConfigurableWrapperFactory;
import com.ht.localizer.Localizer;
import com.ht.localizer.LocalizerBundle;
import com.ht.localizer.LocalizerException;
import com.ht.localizer.LocalizerFactory;
import com.ht.localizer.LocalizerFactoryInternal;
import com.ht.localizer.LocalizerInstance;
import com.ht.localizer.LocalizerType;
import com.ht.uid.UidFactory;
import com.ht.localizer.LocalizerSystemInternal;
import com.ht.wrap.WrapperFactory;
import java.util.Locale;

public final class TestableLocalizerFactory
    implements LocalizerFactory, CanReset, ConfigurableWrapperFactory, ConfigurableUidFactory {
  private static final TestableLocalizerFactory TESTABLE_LOCALIZER_FACTORY_SIGNLETON =
      new TestableLocalizerFactory();

  public static TestableLocalizerFactory getTestableLocalizerFactory() {
    return TESTABLE_LOCALIZER_FACTORY_SIGNLETON;
  }

  private final LocalizerFactoryInternal localizerFactoryInternal;

  private TestableLocalizerFactory() {
    localizerFactoryInternal =
        LocalizerSystemInternal.getSystemInternal().getLocalizerFactoryInternal();
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
}
