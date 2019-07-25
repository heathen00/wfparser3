package com.ht.l10n.acceptance;

import static org.junit.Assert.assertNotNull;

import com.ht.l10n.Assert;
import com.ht.l10n.Assert.LocalizationTester;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.StubFactory;
import com.ht.l10n.TestableLocalizerFactory;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class LocalizerBundleAcceptanceTest {

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
  public void LocalizerBundle_createFactories_factoriesAreCreated() {
    assertNotNull(testableLocalizerFactory);
    assertNotNull(stubFactory);
    assertNotNull(localizerAssert);
  }

  @Test
  public void LocalizerBundle_compositeLocalizerBundleStringExistsInTargetLocale_stringFromTargetLocaleRetrieved()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final Localizer expectedLocalizer =
        testableLocalizerFactory.createLocalizer("localizer.name", expectedTargetLocale);
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final String expectedUnformattedString =
        "this is a test unformatted string for composite resource bundle for Locale fr_CA";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for composite resource bundle for Locale fr_CA: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for composite resource bundle for Locale fr_CA: test_parameter, 33";
    final boolean expectedIsDefined = true;
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "exists.in.target.locale");
    final LocalizerField formattedField =
        stubFactory.createStubLocalizerField("formatted", "exists.in.target.locale");

    LocalizerBundle localizerBundle = testableLocalizerFactory
        .createLocalizerBundle(expectedLocalizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, expectedLocalizer, localizerBundle);
    LocalizationTester unformattedLocalizedStringTester = LocalizationTester
        .createLocalizationTester(localizerBundle, unformattedField, expectedUnformattedString,
            expectedUnformattedString, "does not matter", Integer.valueOf(13456));
    LocalizationTester formattedLocalizedStringTester = LocalizationTester.createLocalizationTester(
        localizerBundle, formattedField, expectedUnformattedFormattedString,
        expectedFormattedString, "test_parameter", Integer.valueOf(33));
    unformattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
    formattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
  }

  @Test
  public void LocalizerBundle_compositeLocalizerBundleStringNotInTargetLocaleButIsInRootLocale_stringFromRootLocaleRetrieved()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final Localizer expectedLocalizer =
        testableLocalizerFactory.createLocalizer("localizer.name", expectedTargetLocale);
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final String expectedUnformattedString =
        "this is a test unformatted string for composite resource bundle for root Locale";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for composite resource bundle for root Locale: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for composite resource bundle for root Locale: test_parameter, 33";
    final boolean expectedIsDefined = true;
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "exists.in.root.locale");
    final LocalizerField formattedField =
        stubFactory.createStubLocalizerField("formatted", "exists.in.root.locale");

    LocalizerBundle localizerBundle = testableLocalizerFactory
        .createLocalizerBundle(expectedLocalizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, expectedLocalizer, localizerBundle);
    LocalizationTester unformattedLocalizedStringTester = LocalizationTester
        .createLocalizationTester(localizerBundle, unformattedField, expectedUnformattedString,
            expectedUnformattedString, "does not matter", Integer.valueOf(13456));
    LocalizationTester formattedLocalizedStringTester = LocalizationTester.createLocalizationTester(
        localizerBundle, formattedField, expectedUnformattedFormattedString,
        expectedFormattedString, "test_parameter", Integer.valueOf(33));
    unformattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
    formattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
  }

  @Test
  public void LocalizerBundle_compositeLocalierBundleStringNotInTargetNorInRootLocale_undefinedStringRetreived()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final Localizer expectedLocalizer =
        testableLocalizerFactory.createLocalizer("localizer.name", expectedTargetLocale);
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final String expectedUnformattedString = "UNDEFINED";
    final String expectedUnformattedFormattedString = "UNDEFINED";
    final String expectedFormattedString = "UNDEFINED";
    final boolean expectedIsDefined = true;
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "not.defined");
    final LocalizerField formattedField =
        stubFactory.createStubLocalizerField("formatted", "not.defined");

    LocalizerBundle localizerBundle = testableLocalizerFactory
        .createLocalizerBundle(expectedLocalizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, expectedLocalizer, localizerBundle);
    LocalizationTester unformattedLocalizedStringTester = LocalizationTester
        .createLocalizationTester(localizerBundle, unformattedField, expectedUnformattedString,
            expectedUnformattedString, "does not matter", Integer.valueOf(13456));
    LocalizationTester formattedLocalizedStringTester = LocalizationTester.createLocalizationTester(
        localizerBundle, formattedField, expectedUnformattedFormattedString,
        expectedFormattedString, "test_parameter", Integer.valueOf(33));
    unformattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
    formattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
  }
}
