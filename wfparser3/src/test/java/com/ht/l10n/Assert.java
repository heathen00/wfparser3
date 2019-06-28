package com.ht.l10n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

public final class Assert {
  public static final Assert createAssert() {
    return new Assert();
  }

  private Assert() {}

  public void assertExpectedLocalizerBundle(Locale expectedTargetLocale,
      Locale expectedResolvedLocale, final String expectedResourceBundleName, Localizer localizer,
      LocalizerBundle localizerBundle) {
    assertNotNull(localizerBundle);
    assertEquals(expectedResourceBundleName, localizerBundle.getResourceBundleName());
    assertEquals(expectedTargetLocale, localizer.getLocale());
    assertEquals(expectedTargetLocale, localizerBundle.getTargetLocale());
    assertEquals(expectedResolvedLocale, localizerBundle.getResolvedLocale());
  }
}
