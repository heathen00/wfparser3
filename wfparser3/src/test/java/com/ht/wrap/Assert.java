package com.ht.wrap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

public final class Assert {

  private Assert() {}

  public static Assert createWrapperAssert() {
    return new Assert();
  }

  public void assertExpectedResourceBundleWrapper(final String expectedBaseBundleName,
      final Locale expectedLocale, final ResourceBundleWrapper resourceBundleWrapper) {
    assertNotNull(resourceBundleWrapper);
    assertEquals(expectedBaseBundleName, resourceBundleWrapper.getBaseBundleName());
    assertEquals(expectedLocale, resourceBundleWrapper.getLocale());
  }

  public void assertExpectedLocalizedAndFormattedStrings(final String expectedUnformattedString,
      final String expectedFormattedStringNoFormat, final Object[] expectedFormatObjectsArray,
      final String unformattedKey, final String formattedKey,
      final ResourceBundleWrapper resourceBundleWrapper) {
    final String expectedFormattedStringWithFormat =
        String.format(resourceBundleWrapper.getLocale(), expectedFormattedStringNoFormat,
            expectedFormatObjectsArray);
    assertNotNull(resourceBundleWrapper);
    assertNotNull(unformattedKey);
    assertNotNull(formattedKey);
    assertEquals(expectedUnformattedString,
        resourceBundleWrapper.getUnformattedString(unformattedKey));
    assertEquals(expectedFormattedStringNoFormat,
        resourceBundleWrapper.getUnformattedString(formattedKey));
    assertEquals(expectedFormattedStringWithFormat,
        resourceBundleWrapper.getFormattedString(formattedKey, expectedFormatObjectsArray));
  }
}
