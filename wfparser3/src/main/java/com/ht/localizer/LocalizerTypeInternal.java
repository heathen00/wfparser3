package com.ht.localizer;

import com.ht.uid.UID;

interface LocalizerTypeInternal extends LocalizerType {
  LocalizerInstanceInternal addLocalizerInstanceInternal(
      LocalizerInstanceInternal localizerInstanceInternal);

  LocalizerInstanceInternal getLocalizerInstanceInternal(UID<LocalizerInstance> instanceUid);
}
