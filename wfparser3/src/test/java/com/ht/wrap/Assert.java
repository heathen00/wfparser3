package com.ht.wrap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

public final class Assert {

  private Assert() {}

  public static Assert createWrapperAssert() {
    return new Assert();
  }

  public void assertExpectedResourceBundleWrapper(String expectedBaseBundleName,
      Locale expectedLocale, ResourceBundleWrapper resourceBundleWrapper) {
    assertNotNull(resourceBundleWrapper);
    assertEquals(expectedBaseBundleName, resourceBundleWrapper.getBaseBundleName());
    assertEquals(expectedLocale, resourceBundleWrapper.getLocale());
  }

}
