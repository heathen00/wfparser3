package com.ht.l10n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class FactoryUnitTest {
  private FactoryInternal localizerFactory;
  private StubFactory stubFactory;
  private Assert localizerAssert;

  @Before
  public void setup() {
    localizerFactory = FactoryInternal.createFactoryInternal();
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
  public void Factory_createUndefinedLocalizerBundle_undefinedLocalizerBundleCreated() {
    String expectedBundleName = "__UNDEFINED__";
    LocalizerBundle localizerBundle = localizerFactory.createUndefinedLocalizerBundle();

    assertNotNull(localizerBundle);
    assertEquals(expectedBundleName, localizerBundle.getResourceBundleName());
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createRootLocaleLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createRootLocaleLocalizerBundle(null,
        "com.ht.l10n.test.resource.TestL10nResourceBundle");
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
        "com.ht.l10n.test.resource.DoesNotExistL10nResourceBundle";

    localizerFactory.createRootLocaleLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test
  public void Factory_createRootLocaleLocalizerBundleWithExistingResourceBundle_rootLocaleLocalizerBundleIsCreated()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.ROOT;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nRootLocaleResourceBundle";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createRootLocaleLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createTargetLocalizerBundle(null,
        "com.ht.l10n.test.resource.TestL10nResourceBundle");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory
        .createTargetLocalizerBundle(localizerFactory.createLocalizer(Locale.CANADA_FRENCH), null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerBundleWithNonExistentResourceBundle_localizerExceptionIsThrown()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.DoesNotExistL10nResourceBundle";

    localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButResourceBundleForSimilarLocaleIsResolved_localizerBundleIsCreatedWithSimilarResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSimilarLocaleExists";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButRootResourceBundleIsResolved_localizerBundleIsCreatedWithRootResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.ROOT;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleButOnlyRootLocaleExists";
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButDefaultLocaleResourcebundleExists_localizerBundleIsCreatedWithDefaultLocaleResourcebundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.GERMANY;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleButOnlyDefaultLocaleExists";
    Locale.setDefault(expectedResolvedLocale);
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithExistingPropertiesResourceBundleButAlsoExistingClassResourceBundle_localizerBundleIsCreatedWithPropertiesResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleEclipsedByClass";
    Locale.setDefault(expectedResolvedLocale);
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithExistingResourceBundleForLocale_localizerBundleIsCreatedWithLocaleResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleExists";

    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createTargetLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, localizer, localizerBundle);
  }
}
