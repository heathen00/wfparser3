package com.ht.l10n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class LocalizerBundleUnitTest {
  private LocalizerFactoryInternal localizerFactory;
  private StubFactory stubFactory;
  private Assert localizerAssert;

  @Before
  public void setup() {
    localizerFactory = SystemInternal.getSystemInternal().getFactoryInternal();
    stubFactory = StubFactory.createStubFactory();
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void Factory_createFactories_factoriesCreated() {
    assertNotNull(localizerFactory);
    assertNotNull(stubFactory);
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
    LocalizerField localizerField =
        stubFactory.createStubLocalizerField("testField00", "testInstance00");

    LocalizerBundleInternal undefinedLocalizerBundle =
        localizerFactory.createUndefinedLocalizerBundle();

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedBundleName, expectedIsDefined, undefinedLocalizerBundle);
    assertEquals(expectedUnformattedString,
        undefinedLocalizerBundle.getUnformattedString(localizerField));
    assertEquals(expectedFormattedString,
        undefinedLocalizerBundle.getFormattedString(localizerField, Integer.valueOf(22), "test"));
  }

  @Test
  public void LocalizerBundle_getStringsForMultipleFieldInstancesFromUndefinedLocalizerBundle_stringsReturnedByUndefinedLocalizerBundleAlwaysUndefined()
      throws Exception {
    LocalizerField localizerFieldOne =
        stubFactory.createStubLocalizerField("testField01", "testInstance01");
    LocalizerField localizerFieldTwo =
        stubFactory.createStubLocalizerField("testField02", "testInstance02");
    String expectedString = "UNDEFINED";

    LocalizerBundleInternal undefinedLocalizerBundle =
        localizerFactory.createUndefinedLocalizerBundle();

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
        localizerFactory.createLocalizerInternal("localizer.name", expectedTargetLocale);
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nRootLocaleResourceBundle";
    final boolean expectedIsDefined = true;
    final String expectedUnformattedString =
        "this is a test unformatted string for the root locale";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for the root locale: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for the root locale: test_parameter, 33";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "one");
    final LocalizerField formattedField = stubFactory.createStubLocalizerField("formatted", "one");

    LocalizerBundleInternal rootLocalizerBundle = localizerFactory
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
    final LocalizerInternal localizerInternal =
        localizerFactory.createLocalizerInternal("localizer.name", Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.ht.l10n.test.resource.TestL10nRootLocaleResourceBundle";
    final LocalizerField nonExistentUnformattedField =
        stubFactory.createStubLocalizerField("unformatted.does.not.exist", "one");
    LocalizerBundleInternal rootLocalizerBundle = null;

    try {
      rootLocalizerBundle =
          localizerFactory.createRootLocaleLocalizerBundle(localizerInternal, resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place: " + le);
    }

    rootLocalizerBundle.getUnformattedString(nonExistentUnformattedField);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerBundle_getNonExistentformattedStringFromRootLocalizerBundle_localizerExceptionIsThrown()
      throws Exception {
    final LocalizerInternal localizerInternal =
        localizerFactory.createLocalizerInternal("localizer.name", Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.ht.l10n.test.resource.TestL10nRootLocaleResourceBundle";
    final LocalizerField nonExistentFormattedField =
        stubFactory.createStubLocalizerField("formatted.does.not.exist", "one");
    LocalizerBundleInternal rootLocalizerBundle = null;

    try {
      rootLocalizerBundle =
          localizerFactory.createRootLocaleLocalizerBundle(localizerInternal, resourceBundleName);
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
        localizerFactory.createLocalizerInternal("localizer.name", expectedTargetLocale);
    final String resourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleExists";
    final String expectedUnformattedString = "this is a test unformatted string for Locale fr_CA";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for Locale fr_CA: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for Locale fr_CA: test_parameter, 33";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "one");
    final LocalizerField formattedField = stubFactory.createStubLocalizerField("formatted", "one");

    LocalizerBundleInternal localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizerInternal, resourceBundleName);

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
    final LocalizerInternal localizerInternal =
        localizerFactory.createLocalizerInternal("localizer.name", Locale.CANADA_FRENCH);
    final String resourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleExists";
    final LocalizerField nonExistentUnformattedField =
        stubFactory.createStubLocalizerField("unformatted.does.not.exist", "one");
    LocalizerBundleInternal localizerBundle = null;

    try {
      localizerBundle =
          localizerFactory.createTargetLocalizerBundle(localizerInternal, resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place");
    }

    localizerBundle.getUnformattedString(nonExistentUnformattedField);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerBundle_getNonExistentformattedStringFromLocalizerBundleForLocale_localizerExceptionIsThrown()
      throws Exception {
    final LocalizerInternal localizerInternal =
        localizerFactory.createLocalizerInternal("localizer.name", Locale.CANADA_FRENCH);
    final String resourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleExists";
    final LocalizerField nonExistentFormattedField =
        stubFactory.createStubLocalizerField("formatted.does.not.exist", "one");
    LocalizerBundleInternal localizerBundle = null;

    try {
      localizerBundle =
          localizerFactory.createTargetLocalizerBundle(localizerInternal, resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place");
    }

    localizerBundle.getFormattedString(nonExistentFormattedField, "param01", Long.valueOf(23l));
  }

  @Test
  public void LocalizerBundle_getExistingStringsFromLocalizerBundleForSimilarLocale_stringsForSimilarLocaleAreRetreived()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.FRENCH;
    final LocalizerInternal localizerInternal =
        localizerFactory.createLocalizerInternal("localizer.name", expectedTargetLocale);
    final String resourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSimilarLocaleExists";
    final String expectedUnformattedString = "this is a test unformatted string for Locale fr";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for Locale fr: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for Locale fr: test_parameter, 33";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "one");
    final LocalizerField formattedField = stubFactory.createStubLocalizerField("formatted", "one");

    LocalizerBundleInternal localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizerInternal, resourceBundleName);

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
}
