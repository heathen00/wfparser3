package com.ht.l10n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class LocalizerBundleInternalImp implements LocalizerBundleInternal {
  private final Localizer localizer;
  private final ResourceBundle resourceBundle;

  LocalizerBundleInternalImp(Localizer localizer, ResourceBundle resourceBundle) {
    this.localizer = localizer;
    this.resourceBundle = resourceBundle;
  }

  @Override
  public String getResourceBundleName() {
    return resourceBundle.getBaseBundleName();
  }

  @Override
  public String getFormattedString(LocalizerField localizerField, Object... parameters)
      throws LocalizerException {
    String formattedLocalizedString = null;
    try {
      formattedLocalizedString = String.format(resourceBundle.getLocale(),
          resourceBundle.getString(localizerField.getFullyQualifiedName()), parameters);
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return formattedLocalizedString;
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
  public String getUnformattedString(LocalizerField localizerField) throws LocalizerException {
    String unformattedLocalizedString = null;
    try {
      unformattedLocalizedString = resourceBundle.getString(localizerField.getFullyQualifiedName());
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return unformattedLocalizedString;
  }

  @Override
  public boolean isDefined() {
    return true;
  }
}
