package com.ht.l10n;

import com.ht.common.UID;

final class LocalizerFieldImp implements LocalizerFieldInternal {
  private final UID<LocalizerField> localizerFieldUid;
  private final LocalizerType localizerType;
  private final String fieldName;

  LocalizerFieldImp(LocalizerType localizerType, String fieldName) {
    this.localizerType = localizerType;
    this.fieldName = fieldName;
    localizerFieldUid = UID.createUid(getFullyQualifiedName(), this);
  }

  @Override
  public LocalizerType getLocalizerType() {
    return localizerType;
  }

  @Override
  public String getFieldName() {
    return fieldName;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", getLocalizerType().getGroupName(), getLocalizerType().getTypeName(),
        getLocalizerType().getInstanceName(), fieldName);
  }

  @Override
  public String getUnformattedString() throws LocalizerException {
    String localizedString = null;
    for (LocalizerBundle localizerBundle : getLocalizerType().getLocalizer()
        .getLocalizerBundleSet()) {
      localizedString = localizerBundle.getUnformattedString(this);
    }
    return localizedString;
  }

  @Override
  public String getFormattedString(Object... parameters) throws LocalizerException {
    String formattedLocalizedString = null;
    for (LocalizerBundle localizerBundle : getLocalizerType().getLocalizer()
        .getLocalizerBundleSet()) {
      formattedLocalizedString = localizerBundle.getFormattedString(this, parameters);
    }
    return formattedLocalizedString;
  }

  @Override
  public UID<LocalizerField> getUid() {
    return localizerFieldUid;
  }

  @Override
  public boolean isDefined() {
    return true;
  }
}
