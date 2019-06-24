package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerException;
import com.ht.l10n.LocalizerField;

public class LocalizerBundleTest {

  private Factory localizerFactory;
  private StubFactory stubFactory;

  @Before
  public void setup() {
    localizerFactory = Factory.createFactory();
    stubFactory = StubFactory.createStubFactory();
  }

  @Test
  public void LocalizerBundle_createFactories_factoriesAreCreated() {
    assertNotNull(localizerFactory);
    assertNotNull(stubFactory);
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
    LocalizerField localizerField =
        stubFactory.createStubLocalizerField("testField00", "testInstance00");

    LocalizerBundle undefinedLocalizerBundle = localizerFactory.createUndefinedLocalizerBundle();

    assertNotNull(undefinedLocalizerBundle);
    assertEquals(expectedBundleName, undefinedLocalizerBundle.getResourceBundleName());
    assertEquals(expectedTargetLocale, undefinedLocalizerBundle.getTargetLocale());
    assertEquals(expectedResolvedLocale, undefinedLocalizerBundle.getResolvedLocale());
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

    LocalizerBundle undefinedLocalizerBundle = localizerFactory.createUndefinedLocalizerBundle();

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
    final Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.ht.l10n.acceptance.TestL10nRootLocaleResourceBundle";
    final String expectedUnformattedString =
        "this is a test unformatted string for the root locale";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for the root locale: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for the root locale: test_parameter, 33";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "one");
    final LocalizerField formattedField = stubFactory.createStubLocalizerField("formatted", "one");

    LocalizerBundle rootLocalizerBundle =
        localizerFactory.createRootLocaleLocalizerBundle(localizer, resourceBundleName);

    assertNotNull(rootLocalizerBundle);
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
    final Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.ht.l10n.acceptance.TestL10nRootLocaleResourceBundle";
    final LocalizerField nonExistentUnformattedField =
        stubFactory.createStubLocalizerField("unformatted.does.not.exist", "one");
    LocalizerBundle rootLocalizerBundle = null;

    try {
      rootLocalizerBundle =
          localizerFactory.createRootLocaleLocalizerBundle(localizer, resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place");
    }

    rootLocalizerBundle.getUnformattedString(nonExistentUnformattedField);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerBundle_getNonExistentformattedStringFromRootLocalizerBundle_localizerExceptionIsThrown()
      throws Exception {
    final Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String resourceBundleName = "com.ht.l10n.acceptance.TestL10nRootLocaleResourceBundle";
    final LocalizerField nonExistentFormattedField =
        stubFactory.createStubLocalizerField("formatted.does.not.exist", "one");
    LocalizerBundle rootLocalizerBundle = null;

    try {
      rootLocalizerBundle =
          localizerFactory.createRootLocaleLocalizerBundle(localizer, resourceBundleName);
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
    final Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);
    final String resourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSpecifiedLocaleExists";
    final String expectedUnformattedString = "this is a test unformatted string for Locale fr_CA";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for Locale fr_CA: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for Locale fr_CA: test_parameter, 33";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "one");
    final LocalizerField formattedField = stubFactory.createStubLocalizerField("formatted", "one");

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, resourceBundleName);

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

    final Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String resourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSpecifiedLocaleExists";
    final LocalizerField nonExistentUnformattedField =
        stubFactory.createStubLocalizerField("unformatted.does.not.exist", "one");
    LocalizerBundle localizerBundle = null;

    try {
      localizerBundle = localizerFactory.createTargetLocalizerBundle(localizer, resourceBundleName);
    } catch (LocalizerException le) {
      fail("exception occurred at wrong place");
    }

    localizerBundle.getUnformattedString(nonExistentUnformattedField);
  }

  @Test(expected = LocalizerException.class)
  public void LocalizerBundle_getNonExistentformattedStringFromLocalizerBundleForLocale_localizerExceptionIsThrown()
      throws Exception {
    final Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String resourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSpecifiedLocaleExists";
    final LocalizerField nonExistentFormattedField =
        stubFactory.createStubLocalizerField("formatted.does.not.exist", "one");
    LocalizerBundle localizerBundle = null;

    try {
      localizerBundle = localizerFactory.createTargetLocalizerBundle(localizer, resourceBundleName);
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
    final Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);
    final String resourceBundleName =
        "com.ht.l10n.acceptance.TestL10nResourceBundleForSimilarLocaleExists";
    final String expectedUnformattedString = "this is a test unformatted string for Locale fr";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for Locale fr: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for Locale fr: test_parameter, 33";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "one");
    final LocalizerField formattedField = stubFactory.createStubLocalizerField("formatted", "one");

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, resourceBundleName);

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

  @Test
  public void LocalizerBundle_compositeLocalizerBundleStringExistsInTargetLocale_stringFromTargetLocaleRetrieved()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);
    final String resourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final String expectedUnformattedString =
        "this is a test unformatted string for composite resource bundle for Locale fr_CA";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for composite resource bundle for Locale fr_CA: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for composite resource bundle for Locale fr_CA: test_parameter, 33";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "exists.in.target.locale");
    final LocalizerField formattedField =
        stubFactory.createStubLocalizerField("formatted", "exists.in.target.locale");

    LocalizerBundle localizerBundle =
        localizerFactory.createCompositeLocalizerBundle(localizer, resourceBundleName);

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

  @Test
  public void LocalizerBundle_compositeLocalizerBundleStringNotInTargetLocaleButIsInRootLocale_stringFromRootLocaleRetrieved()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);
    final String resourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final String expectedUnformattedString =
        "this is a test unformatted string for composite resource bundle for root Locale";
    final String expectedUnformattedFormattedString =
        "this is a test formatted string for composite resource bundle for root Locale: %s, %d";
    final String expectedFormattedString =
        "this is a test formatted string for composite resource bundle for root Locale: test_parameter, 33";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "exists.in.root.locale");
    final LocalizerField formattedField =
        stubFactory.createStubLocalizerField("formatted", "exists.in.root.locale");

    LocalizerBundle localizerBundle =
        localizerFactory.createCompositeLocalizerBundle(localizer, resourceBundleName);

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

  @Test
  public void LocalizerBundle_compositeLocalierBundleStringNotInTargetNorInRootLocale_undefinedStringRetreived()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);
    final String resourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final String expectedUnformattedString = "UNDEFINED";
    final String expectedUnformattedFormattedString = "UNDEFINED";
    final String expectedFormattedString = "UNDEFINED";
    final LocalizerField unformattedField =
        stubFactory.createStubLocalizerField("unformatted", "not.defined");
    final LocalizerField formattedField =
        stubFactory.createStubLocalizerField("formatted", "not.defined");

    LocalizerBundle localizerBundle =
        localizerFactory.createCompositeLocalizerBundle(localizer, resourceBundleName);

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
