package com.ht.l10n;

public interface LocalizerField {
  String getFieldName();

  String getInstanceName();

  String getFullyQualifiedName();

  String getUnformattedString();

  String getFormattedString(Object... parameters);
}
