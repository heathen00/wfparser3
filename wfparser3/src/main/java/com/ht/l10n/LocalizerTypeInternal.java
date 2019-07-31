package com.ht.l10n;

import com.ht.uid.UID;

interface LocalizerTypeInternal extends LocalizerType {
  LocalizerInstanceInternal addLocalizerInstanceInternal(
      LocalizerInstanceInternal localizerInstanceInternal);

  LocalizerInstanceInternal getLocalizerInstanceInternal(UID<LocalizerInstance> instanceUid);
}
