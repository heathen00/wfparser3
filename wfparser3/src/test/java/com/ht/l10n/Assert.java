package com.ht.l10n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ht.uid.UID;

import java.util.Locale;
import java.util.Set;

public final class Assert {
  public static final class LocalizationTester {
    public static LocalizationTester createLocalizationTester(LocalizerBundle localizerBundle,
        LocalizerField localizerField, String expectedUnformattedLocalizedString,
        String expectedFormattedLocalizedString, Object... parameters) {
      return new LocalizationTester((LocalizerBundleInternal) localizerBundle, localizerField,
          expectedUnformattedLocalizedString, expectedFormattedLocalizedString, parameters);
    }

    private final LocalizerBundleInternal localizerBundleInternal;
    private final LocalizerField localizerField;
    private final String expectedUnformattedLocalizedString;
    private final String expectedFormattedLocalizedString;
    private final Object[] parameters;

    private LocalizationTester(LocalizerBundleInternal localizerBundle2,
        LocalizerField localizerField, String expectedUnformattedLocalizedString,
        String expectedFormattedLocalizedString, Object... parameters) {
      this.localizerBundleInternal = localizerBundle2;
      this.localizerField = localizerField;
      this.expectedUnformattedLocalizedString = expectedUnformattedLocalizedString;
      this.expectedFormattedLocalizedString = expectedFormattedLocalizedString;
      this.parameters = parameters;
    }

    public void assertExpectedStringFormattingAndLocalization() {
      try {
        assertEquals(expectedUnformattedLocalizedString,
            localizerBundleInternal.getUnformattedString(localizerField));
        assertEquals(expectedFormattedLocalizedString,
            localizerBundleInternal.getFormattedString(localizerField, parameters));
      } catch (LocalizerException e) {
        fail("Unexpected exception:\n" + e);
      }
    }
  }

  public static final Assert createAssert() {
    return new Assert();
  }

  private Assert() {}

  public void assertSetIsUnmodifiable(Set<?> unmodifiableSet) {
    assertNotNull(unmodifiableSet);
    boolean isModifiable = true;
    try {
      unmodifiableSet.clear();
    } catch (UnsupportedOperationException uoe) {
      isModifiable = false;
    }
    assertFalse(isModifiable);
  }

  public void assertExpectedLocalizerBundle(Locale expectedTargetLocale,
      Locale expectedResolvedLocale, final String expectedResourceBundleName,
      boolean expectedIsDefined, Localizer localizer, LocalizerBundle localizerBundle) {
    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerBundle);
    assertEquals(expectedTargetLocale, localizer.getLocale());
  }

  public void assertExpectedLocalizerBundle(Locale expectedTargetLocale,
      Locale expectedResolvedLocale, String expectedResourceBundleName, boolean expectedIsDefined,
      LocalizerBundle localizerBundle) {
    assertNotNull(localizerBundle);
    assertEquals(expectedResourceBundleName, localizerBundle.getResourceBundleName());
    assertEquals(expectedTargetLocale, localizerBundle.getTargetLocale());
    assertEquals(expectedResolvedLocale, localizerBundle.getResolvedLocale());
    assertEquals(expectedIsDefined, localizerBundle.isDefined());
  }

  public void assertExpectedLocalizerField(final String expectedFieldName,
      final String expectedFullyQualifiedName, final LocalizerType expectedLocalizerType,
      final String expectedUnformattedString, final String expectedFormattedString,
      final boolean expectedIsDefined, LocalizerField localizerField) throws LocalizerException {

    assertExpectedLocalizerField(expectedFieldName, expectedFullyQualifiedName,
        expectedFullyQualifiedName, expectedIsDefined, localizerField);
    assertEquals(expectedLocalizerType, localizerField.getLocalizerType());
    assertEquals(expectedUnformattedString, localizerField.getUnformattedString());
    assertEquals(expectedFormattedString, localizerField.getFormattedString());
    assertEquals(expectedIsDefined, localizerField.isDefined());
  }

  public void assertExpectedLocalizerField(final String expectedFieldName,
      final String expectedFullyQualifiedName, final String expectedUidKey,
      final boolean expectedIsDefined, final LocalizerField localizerField) {
    assertNotNull(localizerField);
    assertEquals(expectedFieldName, localizerField.getFieldName());
    assertEquals(expectedFullyQualifiedName, localizerField.getFullyQualifiedName());
    assertNotNull(localizerField.getUid());
    assertEquals(expectedUidKey, localizerField.getUid().getKey());
    assertEquals(expectedIsDefined, localizerField.isDefined());
  }

  public void assertExpectedLocalizerType(final String expectedGroupName,
      final String expectedTypeName, final String expectedInstanceName,
      final Set<UID<LocalizerField>> expectedLocalizerFieldKeySet,
      final Localizer expectedLocalizer, final UID<LocalizerType> expectedLocalizerTypeUid,
      boolean expectedIsDefined, LocalizerType localizerType) {
    assertExpectedLocalizerType(expectedGroupName, expectedTypeName, expectedInstanceName,
        expectedIsDefined, localizerType);
    assertEquals(expectedLocalizerFieldKeySet, localizerType.getLocalizerFieldUidSet());
    assertEquals(expectedLocalizerTypeUid, localizerType.getUid());
  }

  public void assertExpectedLocalizerType(String expectedGroupName, String expectedTypeName,
      String expectedInstanceName, boolean expectedIsDefined, LocalizerType localizerType) {
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
    assertEquals(expectedIsDefined, localizerType.isDefined());
    assertSetIsUnmodifiable(localizerType.getLocalizerFieldUidSet());
  }

  public void assertExpectedLocalizer(String expectedName, UID<Localizer> expectedLocalizerUid,
      Locale expectedLocale, boolean expectedIsDefined, Localizer localizer) {
    assertNotNull(localizer);
    assertEquals(expectedName, localizer.getName());
    assertEquals(expectedLocalizerUid, localizer.getUid());
    assertEquals(expectedLocale, localizer.getLocale());
    assertEquals(expectedIsDefined, localizer.isDefined());
    assertSetIsUnmodifiable(localizer.getLocalizerBundleSet());
    assertSetIsUnmodifiable(localizer.getLocalizerFieldUidSet());
    assertSetIsUnmodifiable(localizer.getLocalizerTypeUidSet());
  }
}
