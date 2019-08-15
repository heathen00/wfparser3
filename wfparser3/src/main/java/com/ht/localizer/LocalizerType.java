package com.ht.localizer;

import com.ht.uid.Uid;
import com.ht.uid.UniqueComponent;

import java.util.Set;

public interface LocalizerType extends UniqueComponent<LocalizerType>, DefinedObject {
  Localizer getLocalizer();

  String getGroupName();

  String getTypeName();

  String getMethodName();

  String getFullyQualifiedName();

  LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid);

  Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet();
}
