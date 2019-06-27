package com.ht.l10n;

import com.ht.common.UID;

final class UndefinedLocalizerFieldImp implements LocalizerFieldInternal {
  private static final String UNDEFINED_LOCALIZED_STRING = "UNDEFINED";
  private final UndefinedLocalizerTypeImp undefinedLocalizerType;
  private final String fieldName;
  private final UID<LocalizerField> undefinedLocalizerFieldUid;

  UndefinedLocalizerFieldImp(UndefinedLocalizerTypeImp undefinedLocalizerTypeImp) {
    undefinedLocalizerType = undefinedLocalizerTypeImp;
    fieldName = "undef.field";
    undefinedLocalizerFieldUid = UID.createUid(getFullyQualifiedName(), this);
  }

  @Override
  public UID<LocalizerField> getUid() {
    return undefinedLocalizerFieldUid;
  }

  @Override
  public LocalizerType getLocalizerType() {
    return undefinedLocalizerType;
  }

  @Override
  public String getFieldName() {
    return fieldName;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", undefinedLocalizerType.getGroupName(),
        undefinedLocalizerType.getTypeName(), undefinedLocalizerType.getInstanceName(), fieldName);
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
