package com.ht.l10n.acceptance;

import static org.junit.Assert.assertNotNull;

import com.ht.l10n.Assert;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerException;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.LocalizerType;
import com.ht.l10n.StubFactory;
import com.ht.l10n.TestableLocalizerFactory;
import com.ht.uid.UID;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LocalizerFactoryAcceptanceTest {

  private TestableLocalizerFactory testableLocalizerFactory;
  private StubFactory stubFactory;
  private Assert localizerAssert;

  @Before
  public void setup() {
    testableLocalizerFactory = TestableLocalizerFactory.getTestableLocalizerFactory();
    testableLocalizerFactory.resetAll();
    stubFactory = StubFactory.createStubFactory();
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void LocalizerFactory_createFactories_factoriesCreated() {
    assertNotNull(testableLocalizerFactory);
    assertNotNull(stubFactory);
    assertNotNull(localizerAssert);
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerWithNullName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizer(null, Locale.CANADA_FRENCH);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerWithEmptyName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizer("  /t", Locale.CANADA_FRENCH);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerWithInvalidCharactersInName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizer("This name__Is.inValid!\n", Locale.CANADA_FRENCH);
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerWithNullLocale_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizer("not.important", null);
  }

  @Test
  public void LocalizerFactory_createLocalizerWithLocale_localizeIsCreated() throws Exception {
    String expectedName = "localizer.name";
    UID<Localizer> expectedLocalizerUid =
        UID.createUid(expectedName, stubFactory.createDefaultStubLocalizer());
    Locale expectedLocale = Locale.CANADA_FRENCH;
    boolean expectedIsDefined = true;

    Localizer localizer = testableLocalizerFactory.createLocalizer(expectedName, expectedLocale);

    localizerAssert.assertExpectedLocalizer(expectedName, expectedLocalizerUid, expectedLocale,
        expectedIsDefined, localizer);
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createCompositeLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerBundle(null, "does.not.matter");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createCompositeLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerBundle(
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH), null);
  }

  @Test
  public void LocalizerFactory_createCompositeLocalizerBundleWithValidParameters_localizerBundleCreatedAsSpecified()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final boolean expectedIsDefined = true;
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", expectedTargetLocale);

    LocalizerBundle localizerBundle =
        testableLocalizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizer, localizerBundle);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createCompositeLocalizerButRootLocaleResourceBundleDoesNotExist_localizerExceptionIsThrown()
      throws Exception {
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForLocaleExistsButRootLocaleDoesNot";
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    testableLocalizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerFieldWithNullLocalizerType_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerField(null, "test.field");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerFieldWithNullFieldName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"), null);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerFieldWithEmptyFieldName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"), "");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerFieldWithUnsupportedCharactersInFieldName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"),
        "SOME UNSUPPORTED\tCHARACTERS\n");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerFieldWithFieldNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"),
        ".invalid.period.at.begging");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerFieldWithFieldNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"),
        "invalid.period.at.end.");
  }

  @Test
  public void LocalizerFactory_createLocalizerFieldWithSupportedFieldName_localizerFieldIsCreated()
      throws Exception {
    final String expectedGroupName = "test.group";
    final String expectedTypeName = "test.type";
    final String expectedInstanceName = "test.instance";
    final String expectedFieldName = "valid.field.name.00";
    final String expectedFullyQualifiedName = String.join(".", expectedGroupName, expectedTypeName,
        expectedInstanceName, expectedFieldName);
    final LocalizerType expectedLocalizerType =
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance");
    final String expectedUnformattedString =
        "test unformatted string for localizerField with fieldName " + expectedFieldName;
    final String expectedFormattedString =
        "test formatted string for localizerField with fieldName " + expectedFieldName;
    final boolean expectedIsDefined = true;

    LocalizerField localizerField =
        testableLocalizerFactory.createLocalizerField(expectedLocalizerType, expectedFieldName);

    localizerAssert.assertExpectedLocalizerField(expectedFieldName, expectedFullyQualifiedName,
        expectedLocalizerType, expectedUnformattedString, expectedFormattedString,
        expectedIsDefined, localizerField);
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerTypeWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(null, "test.group", "test.type", "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerTypeWithNullGroupName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), null,
        "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithEmptyGroupName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "",
        "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInGroupName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "unsupported\ncharacters", "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithGroupNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        ".invalid.starts.with.period", "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithGroupNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "invalid.ends.with.period.", "test.type", "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerTypeWithNullTypeName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", null, "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithEmptyTypeName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", "", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInTypeName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", "unsupported characters", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithTypeNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", ".invalid.starts.with.period", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithTypeNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", "invalid.ends.with.period.", "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerTypeWithNullInstanceName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", null);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithEmptyInstanceName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", "");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInInstanceName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", "unsupported!characters");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithInstanceNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", ".invalid.starts.with.period");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithInstanceNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", "invalid.ends.with.period.");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithValidParameters_localizerTypeIsCreated()
      throws Exception {
    final String expectedGroupName = "expected.group.name";
    final String expectedTypeName = "expected.type.name";
    final String expectedInstanceName = "expected.instance.name";
    final Set<UID<LocalizerField>> expectedLocalizerFieldKeySet = Collections.emptySet();
    final Localizer expectedLocalizer = stubFactory.createDefaultStubLocalizer();
    final UID<LocalizerType> expectedLocalizerTypeUid = UID.createUid(
        String.join(".", expectedGroupName, expectedTypeName, expectedInstanceName), stubFactory
            .createStubLocalizerType(expectedGroupName, expectedTypeName, expectedInstanceName));
    final boolean expectedIsDefined = true;

    LocalizerType localizerType = testableLocalizerFactory.createLocalizerType(expectedLocalizer,
        expectedGroupName, expectedTypeName, expectedInstanceName);

    localizerAssert.assertExpectedLocalizerType(expectedGroupName, expectedTypeName,
        expectedInstanceName, expectedLocalizerFieldKeySet, expectedLocalizer,
        expectedLocalizerTypeUid, expectedIsDefined, localizerType);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerFieldWhereLocalizerTypeParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    LocalizerType externalLocalizerType =
        stubFactory.createExternalStubLocalizerType("some.group", "some.type", "some.instance");

    testableLocalizerFactory.createLocalizerField(externalLocalizerType, "some.field");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerBundleWhereLocalizerTypeParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    Localizer externalLocalizer = stubFactory.createExternalStubLocalizer();

    testableLocalizerFactory.createLocalizerBundle(externalLocalizer, "com.does.not.Matter");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWhereLocalizerTypeParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    Localizer externalLocalizer = stubFactory.createExternalStubLocalizer();

    testableLocalizerFactory.createLocalizerType(externalLocalizer, "some.group", "some.type",
        "some.instance");
  }
}
