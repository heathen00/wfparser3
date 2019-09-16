package com.nickmlanglois.localizer.acceptance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import com.nickmlanglois.localizer.Assert;
import com.nickmlanglois.localizer.Localizer;
import com.nickmlanglois.localizer.LocalizerBundle;
import com.nickmlanglois.localizer.LocalizerInstance;
import com.nickmlanglois.localizer.LocalizerType;
import com.nickmlanglois.localizer.StubLocalizerFactory;
import com.nickmlanglois.localizer.TestableLocalizerFactory;
import com.nickmlanglois.localizer.Assert.LocalizationTester;
import com.nickmlanglois.uid.UidFactory;
import com.nickmlanglois.wrap.ResourceBundleWrapperConfigurator;
import com.nickmlanglois.wrap.StubWrapperFactory;
import com.nickmlanglois.wrap.WrapperFactory;

public class LocalizerBundleAcceptanceTest {
  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private StubWrapperFactory stubWrapperFactory;
  private Assert localizerAssert;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;
  private Locale defaultTestingLocale;
  private String defaultTestingResourceBundleName;
  private Localizer defaultTestingLocalizer;
  private LocalizerType defaultTestingLocalizerType;
  private LocalizerInstance defaultTestingUnformattedLocalizerInstance;
  private LocalizerInstance defaultTestingFormattedLocalizerInstance;
  private String defaultTestingUnformattedString;
  private String defaultTestingUnformattedFormattedString;
  private String defaultTestingFormattedString;
  private boolean defaultTestingIsDefined;

  @Before
  public void setup() throws Exception {
    testableLocalizerFactory = TestableLocalizerFactory.getTestableLocalizerFactory(
        WrapperFactory.createWrapperFactory(), UidFactory.createUidFactory());
    testableLocalizerFactory.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    stubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
    testableLocalizerFactory.setWrapperFactory(stubWrapperFactory);
    resourceBundleWrapperForLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForLocaleConfigurator();
    resourceBundleWrapperForRootLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForRootLocaleConfigurator();
    localizerAssert = Assert.createAssert();
    defaultTestingLocale = Locale.CANADA_FRENCH;
    defaultTestingResourceBundleName = "com.nickmlanglois.l10n.test.resource.TestResourceBundle";
    defaultTestingLocalizer =
        testableLocalizerFactory.createLocalizer("testing.localizer.name", defaultTestingLocale);
    defaultTestingLocalizerType = testableLocalizerFactory.createLocalizerType(
        defaultTestingLocalizer, "testing.group", "testing.type", "testing.method");
    defaultTestingUnformattedLocalizerInstance =

        stubLocalizerFactory.createLocalizerInstance(defaultTestingLocalizerType, "unformatted");
    defaultTestingFormattedLocalizerInstance =
        stubLocalizerFactory.createLocalizerInstance(defaultTestingLocalizerType, "formatted");
    defaultTestingUnformattedString = "test unformatted string";
    defaultTestingUnformattedFormattedString = "test formatted string: %s, %d";
    defaultTestingFormattedString = "test formatted string: test_parameter, 33";
    defaultTestingIsDefined = true;
  }

  @Test
  public void LocalizerBundle_createTestingAssets_testingAssetsCreated() {
    assertNotNull(testableLocalizerFactory);
    assertNotNull(stubLocalizerFactory);
    assertNotNull(stubWrapperFactory);
    assertNotNull(resourceBundleWrapperForLocaleConfigurator);
    assertNotNull(resourceBundleWrapperForRootLocaleConfigurator);
    assertNotNull(localizerAssert);
    assertNotNull(defaultTestingLocale);
    assertNotNull(defaultTestingResourceBundleName);
    assertNotNull(defaultTestingLocalizer);
    assertNotNull(defaultTestingUnformattedLocalizerInstance);
    assertNotNull(defaultTestingFormattedLocalizerInstance);
    assertNotNull(defaultTestingUnformattedString);
    assertNotNull(defaultTestingUnformattedFormattedString);
    assertNotNull(defaultTestingFormattedString);
    assertTrue(defaultTestingIsDefined);
  }

  @Test
  public void LocalizerBundle_compositeLocalizerBundleStringExistsInTargetLocale_stringFromTargetLocaleRetrieved()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(true)
        .addLocalizedString(defaultTestingUnformattedLocalizerInstance.getFullyQualifiedName(),
            defaultTestingUnformattedString)
        .addLocalizedString(defaultTestingFormattedLocalizerInstance.getFullyQualifiedName(),
            defaultTestingUnformattedFormattedString);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(false);

    LocalizerBundle localizerBundle = testableLocalizerFactory
        .createLocalizerBundle(defaultTestingLocalizer, defaultTestingResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(defaultTestingLocale, defaultTestingLocale,
        defaultTestingResourceBundleName, defaultTestingIsDefined, defaultTestingLocalizer,
        localizerBundle);
    LocalizationTester unformattedLocalizedStringTester =
        LocalizationTester.createLocalizationTester(localizerBundle,
            defaultTestingUnformattedLocalizerInstance, defaultTestingUnformattedString,
            defaultTestingUnformattedString, "does not matter", Integer.valueOf(13456));
    LocalizationTester formattedLocalizedStringTester =
        LocalizationTester.createLocalizationTester(localizerBundle,
            defaultTestingFormattedLocalizerInstance, defaultTestingUnformattedFormattedString,
            defaultTestingFormattedString, "test_parameter", Integer.valueOf(33));
    unformattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
    formattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
  }

  @Test
  public void LocalizerBundle_compositeLocalizerBundleStringNotInTargetLocaleButIsInRootLocale_stringFromRootLocaleRetrieved()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(false);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(true)
        .addLocalizedString(defaultTestingUnformattedLocalizerInstance.getFullyQualifiedName(),
            defaultTestingUnformattedString)
        .addLocalizedString(defaultTestingFormattedLocalizerInstance.getFullyQualifiedName(),
            defaultTestingUnformattedFormattedString);

    LocalizerBundle localizerBundle = testableLocalizerFactory
        .createLocalizerBundle(defaultTestingLocalizer, defaultTestingResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(defaultTestingLocale, defaultTestingLocale,
        defaultTestingResourceBundleName, defaultTestingIsDefined, defaultTestingLocalizer,
        localizerBundle);
    LocalizationTester unformattedLocalizedStringTester =
        LocalizationTester.createLocalizationTester(localizerBundle,
            defaultTestingUnformattedLocalizerInstance, defaultTestingUnformattedString,
            defaultTestingUnformattedString, "does not matter", Integer.valueOf(13456));
    LocalizationTester formattedLocalizedStringTester =
        LocalizationTester.createLocalizationTester(localizerBundle,
            defaultTestingFormattedLocalizerInstance, defaultTestingUnformattedFormattedString,
            defaultTestingFormattedString, "test_parameter", Integer.valueOf(33));
    unformattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
    formattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
  }

  @Test
  public void LocalizerBundle_compositeLocalizerBundleStringNotInTargetNorInRootLocale_undefinedStringRetreived()
      throws Exception {
    final String expectedUnformattedString = "UNDEFINED";
    final String expectedUnformattedFormattedString = "UNDEFINED";
    final String expectedFormattedString = "UNDEFINED";
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(false);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(false);

    LocalizerBundle localizerBundle = testableLocalizerFactory
        .createLocalizerBundle(defaultTestingLocalizer, defaultTestingResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(defaultTestingLocale, defaultTestingLocale,
        defaultTestingResourceBundleName, defaultTestingIsDefined, defaultTestingLocalizer,
        localizerBundle);
    LocalizationTester unformattedLocalizedStringTester =
        LocalizationTester.createLocalizationTester(localizerBundle,
            defaultTestingUnformattedLocalizerInstance, expectedUnformattedString,
            expectedUnformattedString, "does not matter", Integer.valueOf(13456));
    LocalizationTester formattedLocalizedStringTester =
        LocalizationTester.createLocalizationTester(localizerBundle,
            defaultTestingFormattedLocalizerInstance, expectedUnformattedFormattedString,
            expectedFormattedString, "test_parameter", Integer.valueOf(33));
    unformattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
    formattedLocalizedStringTester.assertExpectedStringFormattingAndLocalization();
  }
}
