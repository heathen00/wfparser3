package com.ht.l10n;

import com.ht.uid.UID;

interface LocalizerTypeInternal extends LocalizerType {
  LocalizerFieldInternal addLocalizerFieldInternal(LocalizerFieldInternal localizerFieldInternal);

  LocalizerFieldInternal getLocalizerFieldInternal(UID<LocalizerField> fieldUid);
}
