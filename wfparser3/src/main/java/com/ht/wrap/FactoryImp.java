package com.ht.wrap;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

final class FactoryImp implements Factory {

  private void guardNotNull(String parameterName, Object parameter) {
    if (null == parameter) {
      throw new NullPointerException(parameterName + " cannot be null");
    }
  }

  @Override
  public ResourceBundleWrapper createResourceBundleWrapper(String baseBundleName, Locale locale) {
    guardNotNull("baseBundleName", baseBundleName);
    if (StringUtils.isBlank(baseBundleName)) {
      throw new UnsupportedOperationException("baseBundleName cannot be empty");
    }
    guardNotNull("locale", locale);
    return new ResourceBundleWrapperImp(baseBundleName, locale);
  }

}
