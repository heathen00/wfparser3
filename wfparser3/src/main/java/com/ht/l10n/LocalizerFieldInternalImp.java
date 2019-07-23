package com.ht.l10n;

import com.ht.uid.UID;

final class LocalizerFieldInternalImp implements LocalizerFieldInternal {
  private final UID<LocalizerField> localizerFieldUid;
  private final LocalizerTypeInternal localizerTypeInternal;
  private final String fieldName;

  LocalizerFieldInternalImp(LocalizerTypeInternal localizerTypeInternal, String fieldName) {
    this.localizerTypeInternal = localizerTypeInternal;
    this.fieldName = fieldName;
    localizerFieldUid = UID.createUid(getFullyQualifiedName(), this);
  }

  @Override
  public LocalizerType getLocalizerType() {
    return localizerTypeInternal;
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
      localizedString = ((LocalizerBundleInternal) localizerBundle).getUnformattedString(this);
    }
    return localizedString;
  }

  @Override
  public String getFormattedString(Object... parameters) throws LocalizerException {
    String formattedLocalizedString = null;
    for (LocalizerBundle localizerBundle : getLocalizerType().getLocalizer()
        .getLocalizerBundleSet()) {
      formattedLocalizedString =
          ((LocalizerBundleInternal) localizerBundle).getFormattedString(this, parameters);
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
