package com.ht.l10n;

final class LocalizerFieldImp implements LocalizerField {
  private final LocalizerType localizerType;
  private final String fieldName;

  LocalizerFieldImp(LocalizerType localizerType, String fieldName) {
    this.localizerType = localizerType;
    this.fieldName = fieldName;
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
}
