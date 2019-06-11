package com.ht.l10n;

import java.util.Locale;
import java.util.ResourceBundle;

final class LocalizerBundleImp implements LocalizerBundle, NullObject {
  private final Localizer localizer;
  private final ResourceBundle resourceBundle;

  LocalizerBundleImp(Localizer localizer, ResourceBundle resourceBundle) {
    this.localizer = localizer;
    this.resourceBundle = resourceBundle;
  }

  @Override
  public String getBundleName() {
    return resourceBundle.getBaseBundleName();
  }

  @Override
  public String getFormattedString(LocalizerField localizerField, Object... parameters) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Locale getResolvedLocale() {
    return resourceBundle.getLocale();
  }

  @Override
  public Locale getTargetLocale() {
    return localizer.getLocale();
  }

  @Override
  public String getUnformattedString(LocalizerField localizerField) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isNull() {
    return false;
  }
}
