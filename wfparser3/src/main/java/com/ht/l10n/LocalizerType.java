package com.ht.l10n;

import java.util.Set;
import com.ht.common.UID;

public interface LocalizerType {
  String getBundleName();

  String getTypeName();

  LocalizerField addLocalizerField(LocalizerField localizerField);

  LocalizerField getLocalizerField(UID<LocalizerField> fieldUid);

  Set<UID<LocalizerField>> getLocalizerFieldKeySet();
}
