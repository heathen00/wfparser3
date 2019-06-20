package com.ht.l10n;

import java.util.Locale;
import com.ht.common.UID;

public interface Factory {
  static Factory createFactory() {
    return new FactoryImp();
  }

  Localizer createLocalizer(Locale locale);

  LocalizerBundle createTargetLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerBundle createRootLocaleLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  LocalizerBundle createUndefinedLocalizerBundle();

  LocalizerBundle createCompositeLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException;

  void addLocalizerBundle(Localizer localizer, LocalizerBundle localizerBundle);

  LocalizerType createLocalizerType(String groupName, String typeName, String instanceName)
      throws LocalizerException;

  UID<LocalizerType> addLocalizerType(Localizer localizer, LocalizerType localizerType);

  LocalizerField createLocalizerField(LocalizerType localizerType, String fieldName)
      throws LocalizerException;
}
