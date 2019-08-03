package com.ht.localizer;

import com.ht.uid.UniqueComponent;

public interface LocalizerInstance extends UniqueComponent<LocalizerInstance>, DefinedObject {
  LocalizerType getLocalizerType();

  String getInstanceName();

  String getFullyQualifiedName();

  String getUnformattedString() throws LocalizerException;

  String getFormattedString(Object... parameters) throws LocalizerException;
}
