package com.ht.l10n;

import java.util.Locale;

public interface LocalizerFactory {
  Localizer createLocalizer(String name, Locale locale) throws LocalizerException;

  LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String methodName) throws LocalizerException;

  LocalizerInstance createLocalizerInstance(LocalizerType localizerType, String instanceName)
      throws LocalizerException;
}
