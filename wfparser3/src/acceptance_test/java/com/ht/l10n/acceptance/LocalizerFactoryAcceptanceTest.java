package com.ht.l10n.acceptance;

import static org.junit.Assert.assertNotNull;

import com.ht.l10n.Assert;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerException;
import com.ht.l10n.LocalizerInstance;
import com.ht.l10n.LocalizerType;
import com.ht.l10n.StubLocalizerFactory;
import com.ht.l10n.TestableLocalizerFactory;
import com.ht.uid.UID;
import com.ht.wrap.ResourceBundleWrapperConfigurator;
import com.ht.wrap.StubWrapperFactory;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LocalizerFactoryAcceptanceTest {

  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private StubWrapperFactory stubWrapperFactory;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;
  private Assert localizerAssert;

  @Before
  public void setup() {
    testableLocalizerFactory = TestableLocalizerFactory.getTestableLocalizerFactory();
    testableLocalizerFactory.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    stubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
    resourceBundleWrapperForLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForLocaleConfigurator();
    resourceBundleWrapperForRootLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForRootLocaleConfigurator();
    testableLocalizerFactory.setWrapperFactory(stubWrapperFactory);
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void LocalizerFactory_createTestingAssets_testingAssetsCreated() {
    assertNotNull(testableLocalizerFactory);
    assertNotNull(stubLocalizerFactory);
    assertNotNull(localizerAssert);
    assertNotNull(stubWrapperFactory);
    assertNotNull(resourceBundleWrapperForLocaleConfigurator);
    assertNotNull(resourceBundleWrapperForRootLocaleConfigurator);
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
        UID.createUid(expectedName, stubLocalizerFactory.createDefaultStubLocalizer());
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
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true);
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
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(false);
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForLocaleExistsButRootLocaleDoesNot";
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    testableLocalizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerInstanceWithNullLocalizerType_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerInstance(null, "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerInstanceWithNullInstanceName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerInstance(
        stubLocalizerFactory.createStubLocalizerType("test.group", "test.type", "test.method"),
        null);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerInstanceWithEmptyInstanceName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerInstance(
        stubLocalizerFactory.createStubLocalizerType("test.group", "test.type", "test.method"), "");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerInstanceWithUnsupportedCharactersInInstanceName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerInstance(
        stubLocalizerFactory.createStubLocalizerType("test.group", "test.type", "test.method"),
        "SOME UNSUPPORTED\tCHARACTERS\n");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerInstanceWithInstanceNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerInstance(
        stubLocalizerFactory.createStubLocalizerType("test.group", "test.type", "test.method"),
        ".invalid.period.at.begging");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerInstanceWithInstanceNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerInstance(
        stubLocalizerFactory.createStubLocalizerType("test.group", "test.type", "test.method"),
        "invalid.period.at.end.");
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWithSupportedInstanceName_localizerInstanceIsCreated()
      throws Exception {
    final String expectedGroupName = "test.group";
    final String expectedTypeName = "test.type";
    final String expectedMethodName = "test.method";
    final String expectedInstanceName = "valid.instance.name.00";
    final String expectedFullyQualifiedName = String.join(".", expectedGroupName, expectedTypeName,
        expectedMethodName, expectedInstanceName);
    final LocalizerType expectedLocalizerType =
        stubLocalizerFactory.createStubLocalizerType("test.group", "test.type", "test.method");
    final String expectedUnformattedString =
        "test unformatted string for localizerInstance with instanceName " + expectedInstanceName;
    final String expectedFormattedString =
        "test formatted string for localizerInstance with instanceName " + expectedInstanceName;
    final boolean expectedIsDefined = true;

    LocalizerInstance localizerInstance = testableLocalizerFactory
        .createLocalizerInstance(expectedLocalizerType, expectedInstanceName);

    localizerAssert.assertExpectedLocalizerInstance(expectedInstanceName,
        expectedFullyQualifiedName, expectedLocalizerType, expectedUnformattedString,
        expectedFormattedString, expectedIsDefined, localizerInstance);
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerTypeWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(null, "test.group", "test.type", "test.method");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerTypeWithNullGroupName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        null, "test.type", "test.method");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithEmptyGroupName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "", "test.type", "test.method");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInGroupName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "unsupported\ncharacters", "test.type", "test.method");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithGroupNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        ".invalid.starts.with.period", "test.type", "test.method");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithGroupNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "invalid.ends.with.period.", "test.type", "test.method");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerTypeWithNullTypeName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", null, "test.method");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithEmptyTypeName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", "", "test.method");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInTypeName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", "unsupported characters", "test.method");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithTypeNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", ".invalid.starts.with.period", "test.method");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithTypeNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", "invalid.ends.with.period.", "test.method");
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_createLocalizerTypeWithNullMethodName_nullPointerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", null);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithEmptyMethodName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", "");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInMethodName_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", "unsupported!characters");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithMethodNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", ".invalid.starts.with.period");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWithMethodNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    testableLocalizerFactory.createLocalizerType(stubLocalizerFactory.createDefaultStubLocalizer(),
        "test.group", "test.type", "invalid.ends.with.period.");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithValidParameters_localizerTypeIsCreated()
      throws Exception {
    final String expectedGroupName = "expected.group.name";
    final String expectedTypeName = "expected.type.name";
    final String expectedMethodName = "expected.method.name";
    final Set<UID<LocalizerInstance>> expectedLocalizerInstanceKeySet = Collections.emptySet();
    final Localizer expectedLocalizer = stubLocalizerFactory.createDefaultStubLocalizer();
    final UID<LocalizerType> expectedLocalizerTypeUid =
        UID.createUid(String.join(".", expectedGroupName, expectedTypeName, expectedMethodName),
            stubLocalizerFactory.createStubLocalizerType(expectedGroupName, expectedTypeName,
                expectedMethodName));
    final boolean expectedIsDefined = true;

    LocalizerType localizerType = testableLocalizerFactory.createLocalizerType(expectedLocalizer,
        expectedGroupName, expectedTypeName, expectedMethodName);

    localizerAssert.assertExpectedLocalizerType(expectedGroupName, expectedTypeName,
        expectedMethodName, expectedLocalizerInstanceKeySet, expectedLocalizer,
        expectedLocalizerTypeUid, expectedIsDefined, localizerType);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerInstanceWhereLocalizerTypeParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    LocalizerType externalLocalizerType = stubLocalizerFactory
        .createExternalStubLocalizerType("some.group", "some.type", "some.method");

    testableLocalizerFactory.createLocalizerInstance(externalLocalizerType, "some.instance");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerBundleWhereLocalizerTypeParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    Localizer externalLocalizer = stubLocalizerFactory.createPublishedStubLocalizer();

    testableLocalizerFactory.createLocalizerBundle(externalLocalizer, "com.does.not.Matter");
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerFactory_createLocalizerTypeWhereLocalizerTypeParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    Localizer externalLocalizer = stubLocalizerFactory.createPublishedStubLocalizer();

    testableLocalizerFactory.createLocalizerType(externalLocalizer, "some.group", "some.type",
        "some.method");
  }
}
