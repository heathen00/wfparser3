package com.ht.localizer;

import com.ht.uid.Uid;

interface LocalizerTypeInternal extends LocalizerType {
  LocalizerInstanceInternal addLocalizerInstanceInternal(
      LocalizerInstanceInternal localizerInstanceInternal);

  LocalizerInstanceInternal getLocalizerInstanceInternal(Uid<LocalizerInstance> instanceUid);
}
