package com.ht.localizer;

import com.ht.uid.UID;
import com.ht.uid.UniqueComponent;

import java.util.Set;

public interface LocalizerType extends UniqueComponent<LocalizerType>, DefinedObject {
  Localizer getLocalizer();

  String getGroupName();

  String getTypeName();

  String getMethodName();

  String getFullyQualifiedName();

  LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid);

  Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet();
}
