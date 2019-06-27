package com.ht.l10n;

import java.util.Set;
import com.ht.common.UID;
import com.ht.common.UniqueComponent;

public interface LocalizerType extends UniqueComponent<LocalizerType> {
  Localizer getLocalizer();

  String getGroupName();

  String getTypeName();

  String getInstanceName();

  String getFullyQualifiedName();

  LocalizerField getLocalizerField(UID<LocalizerField> fieldUid);

  Set<UID<LocalizerField>> getLocalizerFieldKeySet();
}
