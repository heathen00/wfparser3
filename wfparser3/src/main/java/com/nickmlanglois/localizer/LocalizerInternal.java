package com.nickmlanglois.localizer;

import com.nickmlanglois.uid.Uid;

interface LocalizerInternal extends Localizer {

  LocalizerBundleInternal addLocalizerBundleInternal(
      LocalizerBundleInternal localizerBundleInternal);

  LocalizerTypeInternal addLocalizerTypeInternal(LocalizerTypeInternal localizerTypeInternal);

  LocalizerTypeInternal getLocalizerTypeInternal(Uid<LocalizerType> typeUid);
}
