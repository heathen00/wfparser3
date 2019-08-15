package com.ht.localizer;

import com.ht.uid.Uid;

interface LocalizerInternal extends Localizer {

  LocalizerBundleInternal addLocalizerBundleInternal(
      LocalizerBundleInternal localizerBundleInternal);

  LocalizerTypeInternal addLocalizerTypeInternal(LocalizerTypeInternal localizerTypeInternal);

  LocalizerTypeInternal getLocalizerTypeInternal(Uid<LocalizerType> typeUid);
}
