package com.ht.l10n;

import com.ht.uid.UID;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

interface FactoryInternal extends Factory {
  LocalizerInternal createLocalizerInternal(String name, Locale locale) throws LocalizerException;

  LocalizerBundleInternal createTargetLocalizerBundle(LocalizerInternal localizer,
      String resourceBundleName) throws LocalizerException;

  LocalizerBundleInternal createRootLocaleLocalizerBundle(LocalizerInternal localizer,
      String resourceBundleName) throws LocalizerException;

  LocalizerBundleInternal createUndefinedLocalizerBundle();

  LocalizerInternal createUndefinedLocalizer();

  ResourceBundle createResourceBundleForLocalizerBundle(String resourceBundleName,
      Locale targetLocale) throws LocalizerException;

  Set<UID<Localizer>> getLocalizerUidSet();

  Localizer getLocalizer(UID<Localizer> localizerUid);

  void resetAll();
}
