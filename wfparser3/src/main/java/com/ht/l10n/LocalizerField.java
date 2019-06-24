package com.ht.l10n;

import com.ht.common.UniqueComponent;

public interface LocalizerField extends UniqueComponent<LocalizerField> {
  LocalizerType getLocalizerType();

  String getFieldName();

  String getFullyQualifiedName();

  String getUnformattedString() throws LocalizerException;

  String getFormattedString(Object... parameters) throws LocalizerException;
}
