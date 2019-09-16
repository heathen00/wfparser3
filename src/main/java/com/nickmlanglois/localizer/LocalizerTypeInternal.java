package com.nickmlanglois.localizer;

import com.nickmlanglois.uid.Uid;

interface LocalizerTypeInternal extends LocalizerType {
  LocalizerInstanceInternal addLocalizerInstanceInternal(
      LocalizerInstanceInternal localizerInstanceInternal);

  LocalizerInstanceInternal getLocalizerInstanceInternal(Uid<LocalizerInstance> instanceUid);
}
