package com.ht.localizer.acceptance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import com.ht.localizer.Assert;
import com.ht.localizer.Assert.LocalizationTester;
import com.ht.localizer.Localizer;
import com.ht.localizer.LocalizerBundle;
import com.ht.localizer.LocalizerInstance;
import com.ht.localizer.LocalizerSystem;
import com.ht.localizer.StubLocalizerFactory;
import com.ht.localizer.TestableLocalizerFactory;
import com.ht.wrap.ResourceBundleWrapperConfigurator;
import com.ht.wrap.StubWrapperFactory;

public class LocalizerBundleAcceptanceTest {
  private LocalizerSystem localizerSystem;
  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private StubWrapperFactory stubWrapperFactory;
  private Assert localizerAssert;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;
  private Locale defaultTestingLocale;
  private String defaultTestingResourceBundleName;
  private Localizer defaultTestingLocalizer;
  private LocalizerInstance defaultTestingUnformattedLocalizerInstance;
  private LocalizerInstance defaultTestingFormattedLocalizerInstance;
  private String defaultTestingUnformattedString;
  private String defaultTestingUnformattedFormattedString;
  private String defaultTestingFormattedString;
  private boolean defaultTestingIsDefined;

  @Before
  public void setup() throws Exception {
    localizerSystem = LocalizerSystem.getSystem();
    testableLocalizerFactory =
        TestableLocalizerFactory.getTestableLocalizerFactory(localizerSystem);
    testableLocalizerFactory.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory(localizerSystem);
    stubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
    testableLocalizerFactory.setWrapperFactory(stubWrapperFactory);
    resourceBundleWrapperForLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForLocaleConfigurator();
    resourceBundleWrapperForRootLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForRootLocaleConfigurator();
    localizerAssert = Assert.createAssert();
    defaultTestingLocale = Locale.CANADA_FRENCH;
    defaultTestingResourceBundleName = "com.ht.l10n.test.resource.TestResourceBundle";
    defaultTestingLocalizer =
        testableLocalizerFactory.createLocalizer("testing.localizer.name", defaultTestingLocale);
    defaultTestingUnformattedLocalizerInstance =
        stubLocalizerFactory.createStubLocalizerInstance("unformatted", "testing.instance");
    defaultTestingFormattedLocalizerInstance =
        stubLocalizerFactory.createStubLocalizerInstance("formatted", "testing.instance");
    defaultTestingUnformattedString = "test unformatted string";
    defaultTestingUnformattedFormattedString = "test formatted string: %s, %d";
    defaultTestingFormattedString = "test formatted string: test_parameter, 33";
    defaultTestingIsDefined = true;
  }

  @Test
  public void LocalizerBundle_createTestingAssets_testingAssetsCreated() {
    assertNotNull(localizerSystem);
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
