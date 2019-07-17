package com.ht.l10n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class LocalizerBundleInternalImp implements LocalizerBundleInternal {
  private final FactoryInternal factoryInternal;
  private final LocalizerInternal localizerInternal;
  private final String resourceBundleName;
  private ResourceBundle resourceBundle;

  LocalizerBundleInternalImp(FactoryInternal factoryInternal, LocalizerInternal localizerInternal,
      ResourceBundle resourceBundle) {
    this.factoryInternal = factoryInternal;
    this.localizerInternal = localizerInternal;
    this.resourceBundle = resourceBundle;
    this.resourceBundleName = this.resourceBundle.getBaseBundleName();
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
    return localizerInternal.getLocale();
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

  @Override
  public void loadL10nResource(Locale locale) throws LocalizerException {
    this.resourceBundle =
        factoryInternal.createResourceBundleForLocalizerBundle(resourceBundleName, locale);
  }

  @Override
  public int compareTo(LocalizerBundle o) {
    return getResourceBundleName().compareTo(o.getResourceBundleName());
  }
}
