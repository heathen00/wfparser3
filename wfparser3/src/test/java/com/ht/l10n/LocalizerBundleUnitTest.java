package com.ht.l10n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ht.wrap.ResourceBundleWrapperConfigurator;
import com.ht.wrap.StubWrapperFactory;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class LocalizerBundleUnitTest {
  private LocalizerFactoryInternal localizerFactoryInternal;
  private StubLocalizerFactory stubLocalizerFactory;
  private StubWrapperFactory stubWrapperFactory;
  private Assert localizerAssert;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;

  @Before
  public void setup() {
    localizerFactoryInternal = SystemInternal.getSystemInternal().getFactoryInternal();
    localizerFactoryInternal.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    stubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
    localizerFactoryInternal.setWrapperFactory(stubWrapperFactory);
    resourceBundleWrapperForLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForLocaleConfigurator();
    resourceBundleWrapperForRootLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForRootLocaleConfigurator();
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void Factory_createTestingAssetts_testingAssetsAreCreated() {
    assertNotNull(localizerFactoryInternal);
    assertNotNull(stubLocalizerFactory);
    assertNotNull(stubWrapperFactory);
    assertNotNull(resourceBundleWrapperForLocaleConfigurator);
    assertNotNull(resourceBundleWrapperForRootLocaleConfigurator);
    assertNotNull(localizerAssert);
  }

  @Test
  public void LocalizerBundle_createUndefinedLocalizerBundle_undefinedLocalizerBundleCreated()
      throws Exception {
    final String expectedBundleName = "__UNDEFINED__";
    final Locale expectedTargetLocale =
        new Locale.Builder().setLanguage("xx").setRegion("ZZ").build();
    final Locale expectedResolvedLocale = expectedTargetLocale;
    final String expectedFormattedString = "UNDEFINED";
    final String expectedUnformattedString = "UNDEFINED";
    final boolean expectedIsDefined = false;
    LocalizerInstance localizerField =
        stubLocalizerFactory.createStubLocalizerField("testField00", "testInstance00");

    LocalizerBundleInternal undefinedLocalizerBundle =
        localizerFactoryInternal.createUndefinedLocalizerBundle();

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedBundleName, expectedIsDefined, undefinedLocalizerBundle);
    assertEquals(expectedUnformattedString,
        undefinedLocalizerBundle.getUnformattedString(localizerField));
    assertEquals(expectedFormattedString,
        undefinedLocalizerBundle.getFormattedString(localizerField, Integer.valueOf(22), "test"));
  }

  @Test
  public void LocalizerBundle_getStringsForMultipleFieldInstancesFromUndefinedLocalizerBundle_returnedStringsAlwaysUndefined()
      throws Exception {
    LocalizerInstance localizerFieldOne =
        stubLocalizerFactory.createStubLocalizerField("testField01", "testInstance01");
    LocalizerInstance localizerFieldTwo =
        stubLocalizerFactory.createStubLocalizerField("testField02", "testInstance02");
    String expectedString = "UNDEFINED";

    LocalizerBundleInternal undefinedLocalizerBundle =
        localizerFactoryInternal.createUndefinedLocalizerBundle();

    assertNotNull(undefinedLocalizerBundle);
    assertEquals(expectedString, undefinedLocalizerBundle.getFormattedString(localizerFieldOne));
    assertEquals(expectedString, undefinedLocalizerBundle.getUnformattedString(localizerFieldOne));
    assertEquals(expectedString, undefinedLocalizerBundle.getFormattedString(localizerFieldTwo,
        "blah", Double.valueOf(1.1d)));
    assertEquals(expectedString, undefinedLocalizerBundle.getUnformattedString(localizerFieldTwo));
  }

  @Test
  public void LocalizerBundle_getExistingStringsFromRootLocalizerBundle_stringsAreRetreived()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.ROOT;
    final LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal("localizer.name", expectedTargetLocale);
    final String expectedResourceBundleName = "com.resource.bundle.name.DoesNotMatter";
    final boolean expectedIsDefined = true;
    final String expectedUnformattedString =
        "this is a test unformatted string for the root locale";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for the root locale: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for the root locale: test_parameter, 33";
    final LocalizerInstance unformattedField =
        stubLocalizerFactory.createStubLocalizerField("unformatted", "one");
    final LocalizerInstance formattedField =
        stubLocalizerFactory.createStubLocalizerField("formatted", "one");
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(true)
        .addLocalizedString(unformattedField.getFullyQualifiedName(), expectedUnformattedString)
        .addLocalizedString(formattedField.getFullyQualifiedName(),
            expectedUnformattedFormattedString);

    LocalizerBundleInternal rootLocalizerBundle = localizerFactoryInternal
        .createRootLocaleLocalizerBundle(localizerInternal, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerInternal, rootLocalizerBundle);;
    assertEquals(expectedUnformattedString,
        rootLocalizerBundle.getUnformattedString(unformattedField));
    assertEquals(expectedFormattedString, rootLocalizerBundle.getFormattedString(formattedField,
        "test_parameter", Integer.valueOf(33)));
    assertEquals(expectedUnformattedFormattedString,
        rootLocalizerBundle.getUnformattedString(formattedField));
    assertEquals(expectedUnformattedString, rootLocalizerBundle.getFormattedString(unformattedField,
        "another_parameter", Double.valueOf(33.4d)));
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerBundle_getNonExistentUnformattedStringFromRootLocalizerBundle_localizerExceptionIsThrown()
      throws Exception {
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(false);
    final LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal("localizer.name", Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.resource.bundle.name.DoesNotMatter";
    final LocalizerInstance nonExistentUnformattedField =
        stubLocalizerFactory.createStubLocalizerField("unformatted.does.not.exist", "one");
    LocalizerBundleInternal rootLocalizerBundle = null;

    try {
      rootLocalizerBundle = localizerFactoryInternal
          .createRootLocaleLocalizerBundle(localizerInternal, resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place: " + le);
    }

    rootLocalizerBundle.getUnformattedString(nonExistentUnformattedField);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerBundle_getNonExistentFormattedStringFromRootLocalizerBundle_localizerExceptionIsThrown()
      throws Exception {
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(false);
    final LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal("localizer.name", Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.resource.bundle.name.DoesNotMatter";
    final LocalizerInstance nonExistentFormattedField =
        stubLocalizerFactory.createStubLocalizerField("formatted.does.not.exist", "one");
    LocalizerBundleInternal rootLocalizerBundle = null;

    try {
      rootLocalizerBundle = localizerFactoryInternal
          .createRootLocaleLocalizerBundle(localizerInternal, resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place");
    }

    rootLocalizerBundle.getFormattedString(nonExistentFormattedField, "param01", Long.valueOf(23l));
  }

  @Test
  public void LocalizerBundle_getExistingStringsFromLocalizerBundleForLocale_stringsForLocaleAreRetreived()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal("localizer.name", expectedTargetLocale);
    final String resourceBundleName = "com.resource.bundle.name.DoesNotMatter";
    final String expectedUnformattedString = "this is a test unformatted string for Locale fr_CA";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for Locale fr_CA: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for Locale fr_CA: test_parameter, 33";
    final LocalizerInstance unformattedField =
        stubLocalizerFactory.createStubLocalizerField("unformatted", "one");
    final LocalizerInstance formattedField =
        stubLocalizerFactory.createStubLocalizerField("formatted", "one");
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(true)
        .addLocalizedString(unformattedField.getFullyQualifiedName(), expectedUnformattedString)
        .addLocalizedString(formattedField.getFullyQualifiedName(),
            expectedUnformattedFormattedString);

    LocalizerBundleInternal localizerBundle =
        localizerFactoryInternal.createTargetLocalizerBundle(localizerInternal, resourceBundleName);

    assertNotNull(localizerBundle);
    assertEquals(expectedTargetLocale, localizerBundle.getTargetLocale());
    assertEquals(expectedResolvedLocale, localizerBundle.getResolvedLocale());
    assertEquals(expectedUnformattedString, localizerBundle.getUnformattedString(unformattedField));
    assertEquals(expectedFormattedString,
        localizerBundle.getFormattedString(formattedField, "test_parameter", Integer.valueOf(33)));
    assertEquals(expectedUnformattedFormattedString,
        localizerBundle.getUnformattedString(formattedField));
    assertEquals(expectedUnformattedString, localizerBundle.getFormattedString(unformattedField,
        "another_parameter", Double.valueOf(33.4d)));
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerBundle_getNonExistentUnformattedStringFromLocalizerBundleForLocale_localizerExceptionIsThrown()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(false);
    final LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal("localizer.name", Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.resource.bundle.name.DoesNotMatter";
    final LocalizerInstance nonExistentUnformattedField =
        stubLocalizerFactory.createStubLocalizerField("unformatted.does.not.exist", "one");
    LocalizerBundleInternal localizerBundle = null;

    try {
      localizerBundle = localizerFactoryInternal.createTargetLocalizerBundle(localizerInternal,
          resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place");
    }

    localizerBundle.getUnformattedString(nonExistentUnformattedField);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerBundle_getNonExistentformattedStringFromLocalizerBundleForLocale_localizerExceptionIsThrown()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true)
        .doesLocalizedStringExist(false);
    final LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal("localizer.name", Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.resource.bundle.name.DoesNotMatter";
    final LocalizerInstance nonExistentFormattedField =
        stubLocalizerFactory.createStubLocalizerField("formatted.does.not.exist", "one");
    LocalizerBundleInternal localizerBundle = null;

    try {
      localizerBundle = localizerFactoryInternal.createTargetLocalizerBundle(localizerInternal,
          resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place");
    }

    localizerBundle.getFormattedString(nonExistentFormattedField, "param01", Long.valueOf(23l));
  }
}
