package com.ht.l10n;

final class LocalizerFieldImp implements LocalizerField {
  private final String fieldName;

  LocalizerFieldImp(String fieldName) {
    this.fieldName = fieldName;
  }

  @Override
  public LocalizerType getLocalizerType() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getFieldName() {
    return fieldName;
  }

  @Override
  public String getFullyQualifiedName() {
    return "UNDEFINED.UNDEFINED.UNDEFINED." + fieldName;
  }

  @Override
  public String getUnformattedString() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getFormattedString(Object... parameters) {
    // TODO Auto-generated method stub
    return null;
  }
}
