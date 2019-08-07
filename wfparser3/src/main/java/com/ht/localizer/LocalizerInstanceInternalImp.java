package com.ht.localizer;

import com.ht.uid.UID;

final class LocalizerInstanceInternalImp implements LocalizerInstanceInternal {
  private final LocalizerFactoryInternal localizerFactoryInternal;
  private final UID<LocalizerInstance> localizerInstanceUid;
  private final LocalizerTypeInternal localizerTypeInternal;
  private final String instanceName;

  LocalizerInstanceInternalImp(LocalizerFactoryInternal localizerFactoryInternal,
      LocalizerTypeInternal localizerTypeInternal, String instanceName) {
    this.localizerFactoryInternal = localizerFactoryInternal;
    this.localizerTypeInternal = localizerTypeInternal;
    this.instanceName = instanceName;
    localizerInstanceUid =
        this.localizerFactoryInternal.getUidFactory().createUid(getFullyQualifiedName(), this);
  }

  @Override
  public LocalizerType getLocalizerType() {
    return localizerTypeInternal;
  }

  @Override
  public String getInstanceName() {
    return instanceName;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", getLocalizerType().getGroupName(), getLocalizerType().getTypeName(),
        getLocalizerType().getMethodName(), instanceName);
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
  public UID<LocalizerInstance> getUid() {
    return localizerInstanceUid;
  }

  @Override
  public boolean isDefined() {
    return true;
  }
}
