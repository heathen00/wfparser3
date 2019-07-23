package com.ht.l10n;

import com.ht.uid.UID;
import com.ht.uid.UniqueComponent;

import java.util.Set;

public interface LocalizerType extends UniqueComponent<LocalizerType>, DefinedObject {
  Localizer getLocalizer();

  String getGroupName();

  String getTypeName();

  String getInstanceName();

  String getFullyQualifiedName();

  LocalizerField getLocalizerField(UID<LocalizerField> fieldUid);

  Set<UID<LocalizerField>> getLocalizerFieldUidSet();
}
