package com.nickmlanglois.localizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import com.nickmlanglois.localizer.Localizer;
import com.nickmlanglois.localizer.LocalizerBundle;
import com.nickmlanglois.localizer.LocalizerBundleInternal;
import com.nickmlanglois.localizer.LocalizerException;
import com.nickmlanglois.localizer.LocalizerInstance;
import com.nickmlanglois.localizer.LocalizerType;
import com.nickmlanglois.uid.Uid;
import java.util.Locale;
import java.util.Set;

public final class Assert {
  public static final class LocalizationTester {
    public static LocalizationTester createLocalizationTester(LocalizerBundle localizerBundle,
        LocalizerInstance localizerInstance, String expectedUnformattedLocalizedString,
        String expectedFormattedLocalizedString, Object... parameters) {
      return new LocalizationTester((LocalizerBundleInternal) localizerBundle, localizerInstance,
          expectedUnformattedLocalizedString, expectedFormattedLocalizedString, parameters);
    }

    private final LocalizerBundleInternal localizerBundleInternal;
    private final LocalizerInstance localizerInstance;
    private final String expectedUnformattedLocalizedString;
    private final String expectedFormattedLocalizedString;
    private final Object[] parameters;

    private LocalizationTester(LocalizerBundleInternal localizerBundle2,
        LocalizerInstance localizerInstance, String expectedUnformattedLocalizedString,
        String expectedFormattedLocalizedString, Object... parameters) {
      this.localizerBundleInternal = localizerBundle2;
      this.localizerInstance = localizerInstance;
      this.expectedUnformattedLocalizedString = expectedUnformattedLocalizedString;
      this.expectedFormattedLocalizedString = expectedFormattedLocalizedString;
      this.parameters = parameters;
    }

    public void assertExpectedStringFormattingAndLocalization() {
      try {
        assertEquals(expectedUnformattedLocalizedString,
            localizerBundleInternal.getUnformattedString(localizerInstance));
        assertEquals(expectedFormattedLocalizedString,
            localizerBundleInternal.getFormattedString(localizerInstance, parameters));
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

  public void assertExpectedLocalizerInstance(final String expectedInstanceName,
      final String expectedFullyQualifiedName, final LocalizerType expectedLocalizerType,
      final String expectedUnformattedString, final String expectedFormattedString,
      final boolean expectedIsDefined, LocalizerInstance localizerInstance)
      throws LocalizerException {

    assertExpectedLocalizerInstance(expectedInstanceName, expectedFullyQualifiedName,
        expectedFullyQualifiedName, expectedIsDefined, localizerInstance);
    assertEquals(expectedLocalizerType, localizerInstance.getLocalizerType());
    assertEquals(expectedUnformattedString, localizerInstance.getUnformattedString());
    assertEquals(expectedFormattedString, localizerInstance.getFormattedString());
    assertEquals(expectedIsDefined, localizerInstance.isDefined());
  }

  public void assertExpectedLocalizerInstance(final String expectedInstanceName,
      final String expectedFullyQualifiedName, final String expectedUidKey,
      final boolean expectedIsDefined, final LocalizerInstance localizerInstance) {
    assertNotNull(localizerInstance);
    assertEquals(expectedInstanceName, localizerInstance.getInstanceName());
    assertEquals(expectedFullyQualifiedName, localizerInstance.getFullyQualifiedName());
    assertNotNull(localizerInstance.getUid());
    assertEquals(expectedUidKey, localizerInstance.getUid().getKey());
    assertEquals(expectedIsDefined, localizerInstance.isDefined());
  }

  public void assertExpectedLocalizerType(final String expectedGroupName,
      final String expectedTypeName, final String expectedMethodName,
      final Set<Uid<LocalizerInstance>> expectedLocalizerInstanceKeySet,
      final Localizer expectedLocalizer, final Uid<LocalizerType> expectedLocalizerTypeUid,
      boolean expectedIsDefined, LocalizerType localizerType) {
    assertExpectedLocalizerType(expectedGroupName, expectedTypeName, expectedMethodName,
        expectedIsDefined, localizerType);
    assertEquals(expectedLocalizerInstanceKeySet, localizerType.getLocalizerInstanceUidSet());
    assertEquals(expectedLocalizerTypeUid, localizerType.getUid());
  }

  public void assertExpectedLocalizerType(String expectedGroupName, String expectedTypeName,
      String expectedMethodName, boolean expectedIsDefined, LocalizerType localizerType) {
    final String expectedFullyQualifiedName =
        String.join(".", expectedGroupName, expectedTypeName, expectedMethodName);
    final String expectedUidKey = expectedFullyQualifiedName;
    assertNotNull(localizerType);
    assertEquals(expectedGroupName, localizerType.getGroupName());
    assertEquals(expectedTypeName, localizerType.getTypeName());
    assertEquals(expectedMethodName, localizerType.getMethodName());
    assertEquals(expectedFullyQualifiedName, localizerType.getFullyQualifiedName());
    assertNotNull(localizerType.getUid());
    assertEquals(expectedUidKey, localizerType.getUid().getKey());
    assertEquals(expectedIsDefined, localizerType.isDefined());
    assertSetIsUnmodifiable(localizerType.getLocalizerInstanceUidSet());
  }

  public void assertExpectedLocalizer(String expectedName, Uid<Localizer> expectedLocalizerUid,
      Locale expectedLocale, boolean expectedIsDefined, Localizer localizer) {
    assertNotNull(localizer);
    assertEquals(expectedName, localizer.getName());
    assertEquals(expectedLocalizerUid, localizer.getUid());
    assertEquals(expectedLocale, localizer.getLocale());
    assertEquals(expectedIsDefined, localizer.isDefined());
    assertSetIsUnmodifiable(localizer.getLocalizerBundleSet());
    assertSetIsUnmodifiable(localizer.getLocalizerInstanceUidSet());
    assertSetIsUnmodifiable(localizer.getLocalizerTypeUidSet());
  }
}
