package com.ht.l10n;

import com.ht.uid.UniqueComponent;

public interface LocalizerField extends UniqueComponent<LocalizerField>, DefinedObject {
  LocalizerType getLocalizerType();

  String getFieldName();

  String getFullyQualifiedName();

  String getUnformattedString() throws LocalizerException;

  String getFormattedString(Object... parameters) throws LocalizerException;
}
