package com.ht.l10n;

import com.ht.common.UID;

interface LocalizerTypeInternal extends LocalizerType {
  LocalizerFieldInternal addLocalizerFieldInternal(LocalizerFieldInternal localizerFieldInternal);

  LocalizerFieldInternal getLocalizerFieldInternal(UID<LocalizerField> fieldUid);
}
