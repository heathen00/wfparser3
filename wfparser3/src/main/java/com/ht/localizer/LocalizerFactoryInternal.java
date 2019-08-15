package com.ht.localizer;

import com.ht.uid.Uid;
import com.ht.wrap.ResourceBundleWrapper;
import java.util.Locale;
import java.util.Set;

interface LocalizerFactoryInternal
    extends LocalizerFactory, ConfigurableWrapperFactory, ConfigurableUidFactory {
  LocalizerInternal createLocalizerInternal(String name, Locale locale) throws LocalizerException;

  LocalizerBundleInternal createTargetLocalizerBundle(LocalizerInternal localizer,
      String resourceBundleName) throws LocalizerException;

  LocalizerBundleInternal createRootLocaleLocalizerBundle(LocalizerInternal localizer,
      String resourceBundleName) throws LocalizerException;

  LocalizerBundleInternal createUndefinedLocalizerBundle();

  LocalizerInternal createUndefinedLocalizer();

  ResourceBundleWrapper createResourceBundleWrapperForLocalizerBundle(String resourceBundleName,
      Locale targetLocale) throws LocalizerException;

  Set<Uid<Localizer>> getLocalizerUidSet();

  Localizer getLocalizer(Uid<Localizer> localizerUid);
}
