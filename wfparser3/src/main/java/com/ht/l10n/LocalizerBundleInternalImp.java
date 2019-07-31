package com.ht.l10n;

import com.ht.wrap.ResourceBundleWrapper;

import java.util.Locale;
import java.util.MissingResourceException;

final class LocalizerBundleInternalImp implements LocalizerBundleInternal {
  private final LocalizerFactoryInternal factoryInternal;
  private final LocalizerInternal localizerInternal;
  private final String resourceBundleName;
  private ResourceBundleWrapper resourceBundleWrapper;

  LocalizerBundleInternalImp(LocalizerFactoryInternal factoryInternal,
      LocalizerInternal localizerInternal, ResourceBundleWrapper resourceBundle) {
    this.factoryInternal = factoryInternal;
    this.localizerInternal = localizerInternal;
    this.resourceBundleWrapper = resourceBundle;
    this.resourceBundleName = this.resourceBundleWrapper.getBaseBundleName();
  }

  @Override
  public String getResourceBundleName() {
    return resourceBundleWrapper.getBaseBundleName();
  }

  @Override
  public String getFormattedString(LocalizerInstance localizerInstance, Object... parameters)
      throws LocalizerException {
    String formattedString = null;
    try {
      formattedString = resourceBundleWrapper
          .getFormattedString(localizerInstance.getFullyQualifiedName(), parameters);
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return formattedString;
  }

  @Override
  public Locale getResolvedLocale() {
    return resourceBundleWrapper.getLocale();
  }

  @Override
  public Locale getTargetLocale() {
    return localizerInternal.getLocale();
  }

  @Override
  public String getUnformattedString(LocalizerInstance localizerInstance)
      throws LocalizerException {
    String unformattedString = null;
    try {
      unformattedString =
          resourceBundleWrapper.getUnformattedString(localizerInstance.getFullyQualifiedName());
    } catch (MissingResourceException mre) {
      throw new LocalizerException(mre);
    }
    return unformattedString;
  }

  @Override
  public boolean isDefined() {
    return true;
  }

  @Override
  public void loadL10nResource(Locale locale) throws LocalizerException {
    this.resourceBundleWrapper =
        factoryInternal.createResourceBundleWrapperForLocalizerBundle(resourceBundleName, locale);
  }

  @Override
  public int compareTo(LocalizerBundle o) {
    return getResourceBundleName().compareTo(o.getResourceBundleName());
  }
}
