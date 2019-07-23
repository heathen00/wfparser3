package com.ht.l10n;

import com.ht.uid.UID;

interface LocalizerInternal extends Localizer {

  LocalizerBundleInternal addLocalizerBundleInternal(
      LocalizerBundleInternal localizerBundleInternal);

  LocalizerTypeInternal addLocalizerTypeInternal(LocalizerTypeInternal localizerTypeInternal);

  LocalizerTypeInternal getLocalizerTypeInternal(UID<LocalizerType> typeUid);
}
