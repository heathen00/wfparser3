package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerException;

public class FactoryTest {

  private Factory localizerFactory;

  private void assertExpectedLocalizerBundle(Locale expectedTargetLocale,
      Locale expectedResolvedLocale, final String expectedResourceBundleName, Localizer localizer,
      LocalizerBundle localizerBundle) {
    assertNotNull(localizerBundle);
    assertEquals(expectedResourceBundleName, localizerBundle.getBundleName());
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

  @Test(expected = NullPointerException.class)
  public void Factory_createUndefinedLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown() {
    localizerFactory.createUndefinedLocalizerBundle(null);
  }

  @Test
  public void Factory_createUndefinedLocalizerBundle_undefinedLocalizerBundleCreated() {
    String expectedBundleName = "__UNDEFINED__";
    Localizer expectedLocalizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    LocalizerBundle localizerBundle =
        localizerFactory.createUndefinedLocalizerBundle(expectedLocalizer);

    assertNotNull(localizerBundle);
    assertEquals(expectedBundleName, localizerBundle.getBundleName());
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
    localizerFactory.createLocalizerBundle(null, "com.ht.l10n.acceptance.TestL10nResourceBundle");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerBundle(localizerFactory.createLocalizer(Locale.CANADA_FRENCH),
        null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerBundleWithNonExistentResourceBundle_localizerExceptionIsThrown()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.DoesNotExistL10nResourceBundle";

    localizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);
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
        localizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);

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
        localizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);

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
        localizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);

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
        localizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);

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
        localizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createCompoundLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createCompoundLocalizerBundle(null, "does.not.matter", true, false);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createCompoundLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createCompoundLocalizerBundle(
        localizerFactory.createLocalizer(Locale.CANADA_FRENCH), null, true, false);
  }

  @Test
  public void Factory_createCompoundLocalizerBundleWithCreateRootLocaleFalseAndThrowExceptionTrue_localizerBundleCreatedAsSpecified()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForCompoundResourceBundleWithRootLocaleAndNoExceptions";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactory.createCompoundLocalizerBundle(localizer,
        expectedResourceBundleName, false, true);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createCompoundLocalizerBundleWithCreateRootLocaleTrueAndThrowExceptionTrue_localizerBundleCreatedAsSpecified()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForCompoundResourceBundleWithRootLocaleAndNoExceptions";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactory.createCompoundLocalizerBundle(localizer,
        expectedResourceBundleName, true, true);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createCompoundLocalizerBundleWithCreateRootLocaleTrueAndThrowExceptionFalse_localizerBundleCreatedAsSpecified()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForCompoundResourceBundleWithRootLocaleAndNoExceptions";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactory.createCompoundLocalizerBundle(localizer,
        expectedResourceBundleName, true, false);

    assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createCompoindLocalizerBundleWithCreateRootLocaleTrueButRootLocaleResourceBundleDoesNotExist_localizerExceptionIsThrown()
      throws Exception {
    final String expectedResourceBundleName =
        "com.ht.l10n.acceptance.TestL10ResourceBundleForLocaleExistsButRootLocaleDoesNot";
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    LocalizerBundle localizerBundle = localizerFactory.createCompoundLocalizerBundle(localizer,
        expectedResourceBundleName, true, true);
  }
}
