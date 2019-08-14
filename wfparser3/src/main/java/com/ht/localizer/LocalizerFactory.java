package com.ht.localizer;

import java.util.Locale;
import com.ht.uid.UidFactory;
import com.ht.wrap.WrapperFactory;

public interface LocalizerFactory extends CanReset {
  static LocalizerFactory createLocalizerFactory(WrapperFactory wrapperFactory,
      UidFactory uidFactory) {
    return new LocalizerFactoryInternalImp(wrapperFactory, uidFactory);
  }

  Localizer createLocalizer(String name, Locale locale) throws LocalizerException;

  LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String methodName) throws LocalizerException;

  LocalizerInstance createLocalizerInstance(LocalizerType localizerType, String instanceName)
      throws LocalizerException;
}
