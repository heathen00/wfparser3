package com.nickmlanglois.localizer;

import java.util.Set;
import com.nickmlanglois.uid.Uid;
import com.nickmlanglois.uid.UniqueComponent;

public interface LocalizerType extends UniqueComponent<LocalizerType>, DefinedObject {
  Localizer getLocalizer();

  String getGroupName();

  String getTypeName();

  String getMethodName();

  String getFullyQualifiedName();

  LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid);

  Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet();
}
