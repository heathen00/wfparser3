package com.ht.l10n;

import java.util.Set;
import com.ht.common.UID;

public interface LocalizerType {
  Localizer getLocalizer();

  String getGroupName();

  String getTypeName();

  String getInstanceName();

  LocalizerField getLocalizerField(UID<LocalizerField> fieldUid);

  Set<UID<LocalizerField>> getLocalizerFieldKeySet();
}
