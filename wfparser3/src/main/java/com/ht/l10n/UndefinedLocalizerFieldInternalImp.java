package com.ht.l10n;

import com.ht.uid.UID;

final class UndefinedLocalizerFieldInternalImp implements LocalizerFieldInternal {
  private static final String UNDEFINED_LOCALIZED_STRING = "UNDEFINED";
  private final UndefinedLocalizerTypeInternalImp undefinedLocalizerTypeInternalImp;
  private final String fieldName;
  private final UID<LocalizerField> undefinedLocalizerFieldUid;

  UndefinedLocalizerFieldInternalImp(UndefinedLocalizerTypeInternalImp undefinedLocalizerTypeImp) {
    undefinedLocalizerTypeInternalImp = undefinedLocalizerTypeImp;
    fieldName = "undef.field";
    undefinedLocalizerFieldUid = UID.createUid(getFullyQualifiedName(), this);
  }

  @Override
  public UID<LocalizerField> getUid() {
    return undefinedLocalizerFieldUid;
  }

  @Override
  public LocalizerType getLocalizerType() {
    return undefinedLocalizerTypeInternalImp;
  }

  @Override
  public String getFieldName() {
    return fieldName;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", undefinedLocalizerTypeInternalImp.getGroupName(),
        undefinedLocalizerTypeInternalImp.getTypeName(),
        undefinedLocalizerTypeInternalImp.getInstanceName(), fieldName);
  }

  @Override
  public String getUnformattedString() throws LocalizerException {
    return UNDEFINED_LOCALIZED_STRING;
  }

  @Override
  public String getFormattedString(Object... parameters) throws LocalizerException {
    return UNDEFINED_LOCALIZED_STRING;
  }

  @Override
  public boolean isDefined() {
    return false;
  }

}
