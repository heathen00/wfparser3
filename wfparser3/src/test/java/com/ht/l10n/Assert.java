package com.ht.l10n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ht.common.UID;

import java.util.Locale;
import java.util.Set;

public final class Assert {
  public static final class LocalizationTester {
    public static LocalizationTester createLocalizationTester(LocalizerBundle localizerBundle,
        LocalizerField localizerField, String expectedUnformattedLocalizedString,
        String expectedFormattedLocalizedString, Object... parameters) {
      return new LocalizationTester(localizerBundle, localizerField,
          expectedUnformattedLocalizedString, expectedFormattedLocalizedString, parameters);
    }

    private final LocalizerBundle localizerBundle;
    private final LocalizerField localizerField;
    private final String expectedUnformattedLocalizedString;
    private final String expectedFormattedLocalizedString;
    private final Object[] parameters;

    private LocalizationTester(LocalizerBundle localizerBundle, LocalizerField localizerField,
        String expectedUnformattedLocalizedString, String expectedFormattedLocalizedString,
        Object... parameters) {
      this.localizerBundle = localizerBundle;
      this.localizerField = localizerField;
      this.expectedUnformattedLocalizedString = expectedUnformattedLocalizedString;
      this.expectedFormattedLocalizedString = expectedFormattedLocalizedString;
      this.parameters = parameters;
    }

    public void assertExpectedStringFormattingAndLocalization() {
      try {
        assertEquals(expectedUnformattedLocalizedString,
            localizerBundle.getUnformattedString(localizerField));
        assertEquals(expectedFormattedLocalizedString,
            localizerBundle.getFormattedString(localizerField, parameters));
      } catch (LocalizerException e) {
        fail("Unexpected exception:\n" + e);
      }
    }
  }

  public static final Assert createAssert() {
    return new Assert();
  }

  private Assert() {}

  public void assertExpectedLocalizerBundle(Locale expectedTargetLocale,
      Locale expectedResolvedLocale, final String expectedResourceBundleName, Localizer localizer,
      LocalizerBundle localizerBundle) {
    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizerBundle);
    assertEquals(expectedTargetLocale, localizer.getLocale());
  }

  public void assertExpectedLocalizerBundle(Locale expectedTargetLocale,
      Locale expectedResolvedLocale, String expectedResourceBundleName,
      LocalizerBundle localizerBundle) {
    assertNotNull(localizerBundle);
    assertEquals(expectedResourceBundleName, localizerBundle.getResourceBundleName());
    assertEquals(expectedTargetLocale, localizerBundle.getTargetLocale());
    assertEquals(expectedResolvedLocale, localizerBundle.getResolvedLocale());
  }

  public void assertExpectedLocalizerField(final String expectedFieldName,
      final String expectedFullyQualifiedName, final LocalizerType expectedLocalizerType,
      final String expectedUnformattedString, final String expectedFormattedString,
      LocalizerField localizerField) throws LocalizerException {

    assertExpectedLocalizerField(expectedFieldName, expectedFullyQualifiedName,
        expectedFullyQualifiedName, localizerField);
    assertEquals(expectedLocalizerType, localizerField.getLocalizerType());
    assertEquals(expectedUnformattedString, localizerField.getUnformattedString());
    assertEquals(expectedFormattedString, localizerField.getFormattedString());
  }

  public void assertExpectedLocalizerField(String expectedFieldName,
      String expectedFullyQualifiedName, String expectedUidKey, LocalizerField localizerField) {
    assertNotNull(localizerField);
    assertEquals(expectedFieldName, localizerField.getFieldName());
    assertEquals(expectedFullyQualifiedName, localizerField.getFullyQualifiedName());
    assertNotNull(localizerField.getUid());
    assertEquals(expectedUidKey, localizerField.getUid().getKey());
  }

  public void assertExpectedLocalizerType(final String expectedGroupName,
      final String expectedTypeName, final String expectedInstanceName,
      final Set<UID<LocalizerField>> expectedLocalizerFieldKeySet,
      final Localizer expectedLocalizer, final UID<LocalizerType> expectedLocalizerTypeUid,
      LocalizerType localizerType) {
    assertExpectedLocalizerType(expectedGroupName, expectedTypeName, expectedInstanceName,
        localizerType);
    assertEquals(expectedLocalizerFieldKeySet, localizerType.getLocalizerFieldKeySet());
    assertEquals(expectedLocalizerTypeUid, localizerType.getUid());
  }

  public void assertExpectedLocalizerType(String expectedGroupName, String expectedTypeName,
      String expectedInstanceName, LocalizerType localizerType) {
    final String expectedFullyQualifiedName =
        String.join(".", expectedGroupName, expectedTypeName, expectedInstanceName);
    final String expectedUidKey = expectedFullyQualifiedName;
    assertNotNull(localizerType);
    assertEquals(expectedGroupName, localizerType.getGroupName());
    assertEquals(expectedTypeName, localizerType.getTypeName());
    assertEquals(expectedInstanceName, localizerType.getInstanceName());
    assertEquals(expectedFullyQualifiedName, localizerType.getFullyQualifiedName());
    assertNotNull(localizerType.getUid());
    assertEquals(expectedUidKey, localizerType.getUid().getKey());
  }
}
