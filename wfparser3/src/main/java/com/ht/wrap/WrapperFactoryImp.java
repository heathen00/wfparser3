package com.ht.wrap;

import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

final class WrapperFactoryImp implements WrapperFactory {
  public static WrapperFactoryImp createWrapperFactoryImp() {
    return new WrapperFactoryImp();
  }

  private void guardNotNull(String parameterName, Object parameter) {
    if (null == parameter) {
      throw new NullPointerException(parameterName + " cannot be null");
    }
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapperForLocale(String baseBundleName,
      Locale locale) {
    guardNotNull("baseBundleName", baseBundleName);
    if (StringUtils.isBlank(baseBundleName)) {
      throw new UnsupportedOperationException("baseBundleName cannot be empty");
    }
    guardNotNull("locale", locale);
    return new ResourceBundleWrapperImp(baseBundleName, locale);
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapperForRootLocale(String resourceBundleName) {
    return createResourceBundleWrapperForLocale(resourceBundleName, Locale.ROOT);
  }

  @Override
  public void resetAll() {}
}
