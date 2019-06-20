package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import com.ht.common.UID;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerException;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.LocalizerType;

public class FactoryTest {

  // TODO need to ensure there are restrictions on how to name resource bundles: only use upper and
  // lower case letters, numbers, and periods. Cannot start or end with a period

  private Factory localizerFactory;

  private void assertExpectedLocalizerBundle(Locale expectedTargetLocale,
      Locale expectedResolvedLocale, final String expectedResourceBundleName, Localizer localizer,
      LocalizerBundle localizerBundle) {
    assertNotNull(localizerBundle);
    assertEquals(expectedResourceBundleName, localizerBundle.getResourceBundleName());
    assertEquals(expectedTargetLocale, localizer.getLocale());
    assertEquals(expectedTargetLocale, localizerBundle.getTargetLocale());
    assertEquals(expectedResolvedLocale, localizerBundle.getResolvedLocale());
  }

  @Before
  public void setup() {
    localizerFactory = Factory.createFactory();
  }

  @Test
  public void Factory_createFactory_factoryCreated() {
    assertNotNull(localizerFactory);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizeWithNullLocale_nullPointerExceptionIsThrown() {
    localizerFactory.createLocalizer(null);
  }

  @Test
  public void Factory_createLocalizeWithLocale_localizeIsCreated() {
    Locale expectedLocale = Locale.CANADA_FRENCH;

    Localizer localize = localizerFactory.createLocalizer(expectedLocale);

    assertNotNull(localize);
    Locale locale = localize.getLocale();
    assertNotNull(locale);
    assertEquals(expectedLocale, locale);
  }

  @Test
  public void Factory_createUndefinedLocalizerBundle_undefinedLocalizerBundleCreated() {
    String expectedBundleName = "__UNDEFINED__";
    LocalizerBundle localizerBundle = localizerFactory.createUndefinedLocalizerBundle();

    assertNotNull(localizerBundle);
    assertEquals(expectedBundleName, localizerBundle.getResourceBundleName());
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createRootLocaleLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createRootLocaleLocalizerBundle(null,
        "com.ht.l10n.acceptance.TestL10nResourceBundle");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createRootLocaleLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createRootLocaleLocalizerBundle(
        localizerFactory.createLocalizer(Locale.CANADA_FRENCH), null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createRootLocaleLocalizerBundleWithNonExistentResourceBundle_localizerExceptionIsThrown()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.DoesNotExistL10nResourceBundle";

    localizerFactory.createRootLocaleLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test
  public void Factory_createRootLocaleLocalizerBundleWithExistingResourceBundle_rootLocaleLocalizerBundleIsCreated()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.ROOT;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10nRootLocaleResourceBundle";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createRootLocaleLocalizerBundle(localizer, expectedResourceBundleName);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createTargetLocalizerBundle(null,
        "com.ht.l10n.acceptance.TestL10nResourceBundle");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory
        .createTargetLocalizerBundle(localizerFactory.createLocalizer(Locale.CANADA_FRENCH), null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerBundleWithNonExistentResourceBundle_localizerExceptionIsThrown()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.DoesNotExistL10nResourceBundle";

    localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButResourceBundleForSimilarLocaleIsResolved_localizerBundleIsCreatedWithSimilarResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSimilarLocaleExists";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButRootResourceBundleIsResolved_localizerBundleIsCreatedWithRootResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.ROOT;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSpecifiedLocaleButOnlyRootLocaleExists";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButDefaultLocaleResourcebundleExists_localizerBundleIsCreatedWithDefaultLocaleResourcebundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.GERMANY;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSpecifiedLocaleButOnlyDefaultLocaleExists";
    Locale.setDefault(expectedResolvedLocale);
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithExistingPropertiesResourceBundleButAlsoExistingClassResourceBundle_localizerBundleIsCreatedWithPropertiesResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSpecifiedLocaleEclipsedByClass";
    Locale.setDefault(expectedResolvedLocale);
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithExistingResourceBundleForLocale_localizerBundleIsCreatedWithLocaleResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSpecifiedLocaleExists";

    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createCompositeLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createCompositeLocalizerBundle(null, "does.not.matter");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createCompositeLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createCompositeLocalizerBundle(
        localizerFactory.createLocalizer(Locale.CANADA_FRENCH), null);
  }

  @Test
  public void Factory_createCompositeLocalizerBundleWithValidParameters_localizerBundleCreatedAsSpecified()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createCompositeLocalizerBundle(localizer, expectedResourceBundleName);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createCompositeLocalizerButRootLocaleResourceBundleDoesNotExist_localizerExceptionIsThrown()
      throws Exception {
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForLocaleExistsButRootLocaleDoesNot";
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    localizerFactory.createCompositeLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerFieldWithNullLocalizerType_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(null, "test.field");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerFieldWithNullFieldName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        createStubLocalizerType("test.group", "test.type", "test.instance"), null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerFieldWithEmptyFieldName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        createStubLocalizerType("test.group", "test.type", "test.instance"), "");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerFieldWithUnsupportedCharactersInFieldName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        createStubLocalizerType("test.group", "test.type", "test.instance"),
        "SOME UNSUPPORTED\tCHARACTERS\n");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerFieldWithFieldNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        createStubLocalizerType("test.group", "test.type", "test.instance"),
        ".invalid.period.at.begging");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerFieldWithFieldNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        createStubLocalizerType("test.group", "test.type", "test.instance"),
        "invalid.period.at.end.");
  }

  @Test
  public void Factory_createLocalizerFieldWithSupportedFieldName_localizerFieldIsCreated()
      throws Exception {
    final String expectedGroupName = "test.group";
    final String expectedTypeName = "test.type";
    final String expectedInstanceName = "test.instance";
    final String expectedFieldName = "valid.field.name.00";
    final String expectedFullyQualifiedName = String.join(".", expectedGroupName, expectedTypeName,
        expectedInstanceName, expectedFieldName);
    final LocalizerType expectedLocalizerType =
        createStubLocalizerType("test.group", "test.type", "test.instance");
    final String expectedUnformattedString =
        "test unformatted string for localizerField with fieldName " + expectedFieldName;
    final String expectedFormattedString =
        "test formatted string for localizerField with fieldName " + expectedFieldName;

    LocalizerField localizerField =
        localizerFactory.createLocalizerField(expectedLocalizerType, expectedFieldName);

    assertNotNull(localizerField);
    assertEquals(expectedLocalizerType, localizerField.getLocalizerType());
    assertEquals(expectedFieldName, localizerField.getFieldName());
    assertEquals(expectedFullyQualifiedName, localizerField.getFullyQualifiedName());
    assertEquals(expectedUnformattedString, localizerField.getUnformattedString());
    assertEquals(expectedFormattedString, localizerField.getFormattedString());
  }

  private LocalizerType createStubLocalizerType(String groupName, String typeName,
      String instanceName) {
    return new LocalizerType() {
      final String myGroupName = groupName;
      final String myTypeName = typeName;
      final String myInstanceName = instanceName;
      final Localizer myLocalizer = createDefaultStubLocalizer();

      @Override
      public String getTypeName() {
        return myTypeName;
      }

      @Override
      public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Localizer getLocalizer() {
        return myLocalizer;
      }

      @Override
      public String getInstanceName() {
        return myInstanceName;
      }

      @Override
      public String getGroupName() {
        return myGroupName;
      }
    };
  }

  private Localizer createDefaultStubLocalizer() {
    return new Localizer() {
      final Set<LocalizerBundle> myLocalizerBundleSet = initLocalizerBundleSet();

      private Set<LocalizerBundle> initLocalizerBundleSet() {
        Set<LocalizerBundle> localizerBundleSet = new HashSet<>();
        localizerBundleSet.add(createDefaultStubLocalizerBundle());
        return localizerBundleSet;
      }

      @Override
      public void setLocale(Locale locale) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<UID<LocalizerType>> getLocalizerTypeKeySet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<LocalizerBundle> getLocalizerBundleSet() {
        return Collections.unmodifiableSet(myLocalizerBundleSet);
      }

      @Override
      public Locale getLocale() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }
    };
  }

  private LocalizerBundle createDefaultStubLocalizerBundle() {
    return new LocalizerBundle() {

      @Override
      public String getUnformattedString(LocalizerField localizerField) throws LocalizerException {
        return "test unformatted string for localizerField with fieldName "
            + localizerField.getFieldName();
      }

      @Override
      public Locale getTargetLocale() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public String getResourceBundleName() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Locale getResolvedLocale() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public String getFormattedString(LocalizerField localizerField, Object... parameters)
          throws LocalizerException {
        return "test formatted string for localizerField with fieldName "
            + localizerField.getFieldName();
      }
    };
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerTypeWithNullGroupName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(null, "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithEmptyGroupName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("", "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithUnsupportedCharactersInGroupName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("unsupported\ncharacters", "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithGroupNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(".invalid.starts.with.period", "test.type",
        "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithGroupNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("invalid.ends.with.period.", "test.type", "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerTypeWithNullTypeName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", null, "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithEmptyTypeName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", "", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithUnsupportedCharactersInTypeName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", "unsupported characters", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithTypeNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", ".invalid.starts.with.period",
        "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithTypeNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", "invalid.ends.with.period.",
        "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerTypeWithNullInstanceName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", "test.type", null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithEmptyInstanceName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", "test.type", "");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithUnsupportedCharactersInInstanceName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", "test.type", "unsupported!characters");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithInstanceNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", "test.type", ".invalid.starts.with.period");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithInstanceNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType("test.group", "test.type", "invalid.ends.with.period.");
  }

  @Test
  public void Factory_createLocalizerTypeWithValidParameters_localizerTypeIsCreated()
      throws Exception {
    final String expectedGroupName = "expected.group.name";
    final String expectedTypeName = "expected.type.name";
    final String expectedInstanceName = "expected.instance.name";
    final Set<UID<LocalizerField>> expectedLocalizerFieldKeySet = Collections.emptySet();

    LocalizerType localizerType = localizerFactory.createLocalizerType(expectedGroupName,
        expectedTypeName, expectedInstanceName);

    assertNotNull(localizerType);
    assertEquals(expectedGroupName, localizerType.getGroupName());
    assertEquals(expectedTypeName, localizerType.getTypeName());
    assertEquals(expectedInstanceName, localizerType.getInstanceName());
    assertEquals(expectedLocalizerFieldKeySet, localizerType.getLocalizerFieldKeySet());
  }
}
