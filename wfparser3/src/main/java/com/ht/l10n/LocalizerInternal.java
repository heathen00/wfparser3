package com.ht.l10n;

interface LocalizerInternal extends Localizer {

  LocalizerBundleInternal addLocalizerBundleInternal(
      LocalizerBundleInternal localizerBundleInternal);

  LocalizerTypeInternal addLocalizerTypeInternal(LocalizerTypeInternal localizerTypeInternal);
}
