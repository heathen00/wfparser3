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
import com.ht.l10n.StubFactory;

public class LocalizerBundleAcceptanceTest {

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
  public void LocalizerBundle_compositeLocalizerBundleStringExistsInTargetLocale_stringFromTargetLocaleRetrieved()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);
    final String resourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
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
        "com.ht.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
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
        "com.ht.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
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
