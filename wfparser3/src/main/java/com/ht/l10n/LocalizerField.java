package com.ht.l10n;

public interface LocalizerField {
  LocalizerType getLocalizerType();

  String getFieldName();

  String getFullyQualifiedName();

  String getUnformattedString();

  String getFormattedString(Object... parameters);
}
