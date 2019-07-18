package com.ht.l10n;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class FactoryUnitTest {
  private FactoryInternal localizerFactoryInternal;
  private StubFactory stubFactory;
  private Assert localizerAssert;
  private Locale expectedUndefinedLocale;

  @Before
  public void setup() {
    localizerFactoryInternal = FactoryInternal.createFactoryInternal();
    stubFactory = StubFactory.createStubFactory();
    localizerAssert = Assert.createAssert();

    final String expectedUndefinedLanguage = "xx";
    final String expectedUndefinedRegion = "ZZ";
    expectedUndefinedLocale = (new Locale.Builder()).setLanguage(expectedUndefinedLanguage)
        .setRegion(expectedUndefinedRegion).build();
  }

  @Test
  public void Factory_createFactories_factoriesCreated() {
    assertNotNull(localizerFactoryInternal);
    assertNotNull(stubFactory);
    assertNotNull(localizerAssert);
    assertNotNull(expectedUndefinedLocale);
  }

  @Test
  public void Factory_createUndefinedLocalizerBundle_undefinedLocalizerBundleCreated() {
    final String expectedResourceBundleName = "__UNDEFINED__";
    final Locale expectedTargetLocale = expectedUndefinedLocale;
    final Locale expectedResolvedLocale = expectedUndefinedLocale;
    final boolean expectedIsDefined = false;
    LocalizerBundle localizerBundle = localizerFactoryInternal.createUndefinedLocalizerBundle();

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerBundle);
  }

  @Test
  public void Factory_createUndefinedLocalizer_undefinedLocalizerCreated() {
    final boolean expectedIsDefined = false;

    Localizer localizer = localizerFactoryInternal.createUndefinedLocalizer();

    localizerAssert.assertExpectedLocalizer(expectedUndefinedLocale, expectedIsDefined, localizer);
  }

  @Test
  public void Factory_createUndefinedLocalizerField_undefinedLocalizerFieldCreated()
      throws Exception {
    final String expectedFieldName = "undef.field";
    final String expectedFullyQualifiedName = "undef.group.undef.type.undef.instance.undef.field";
    final LocalizerType expectedLocalizerType =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerType(null);
    final String expectedUnformattedString = "UNDEFINED";
    final String expectedFormattedString = "UNDEFINED";
    final boolean expectedIsDefined = false;

    LocalizerField localizerField =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerField(null);

    localizerAssert.assertExpectedLocalizerField(expectedFieldName, expectedFullyQualifiedName,
        expectedLocalizerType, expectedUnformattedString, expectedFormattedString,
        expectedIsDefined, localizerField);
  }

  @Test
  public void Factory_createUndefinedLocalizerType_undefinedLocalizerTypeCreated() {
    final String expectedGroupName = "undef.group";
    final String expectedTypeName = "undef.type";
    final String expectedInstanceName = "undef.instance";
    final boolean expectedIsDefined = false;

    LocalizerType localizerType =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerType(null);

    localizerAssert.assertExpectedLocalizerType(expectedGroupName, expectedTypeName,
        expectedInstanceName, expectedIsDefined, localizerType);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createRootLocaleLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactoryInternal.createRootLocaleLocalizerBundle(null,
        "com.ht.l10n.test.resource.TestL10nResourceBundle");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createRootLocaleLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactoryInternal.createRootLocaleLocalizerBundle(
        localizerFactoryInternal.createLocalizerInternal(Locale.CANADA_FRENCH), null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createRootLocaleLocalizerBundleWithNonExistentResourceBundle_localizerExceptionIsThrown()
      throws Exception {
    LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal(Locale.CANADA_FRENCH);
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.DoesNotExistL10nResourceBundle";

    localizerFactoryInternal.createRootLocaleLocalizerBundle(localizerInternal,
        expectedResourceBundleName);
  }

  @Test
  public void Factory_createRootLocaleLocalizerBundleWithExistingResourceBundle_rootLocaleLocalizerBundleIsCreated()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.ROOT;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nRootLocaleResourceBundle";
    final boolean expectedIsDefined = true;
    LocalizerInternal localizerIntrnal =
        localizerFactoryInternal.createLocalizerInternal(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactoryInternal
        .createRootLocaleLocalizerBundle(localizerIntrnal, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerIntrnal, localizerBundle);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactoryInternal.createTargetLocalizerBundle(null,
        "com.ht.l10n.test.resource.TestL10nResourceBundle");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactoryInternal.createTargetLocalizerBundle(
        localizerFactoryInternal.createLocalizerInternal(Locale.CANADA_FRENCH), null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerBundleWithNonExistentResourceBundle_localizerExceptionIsThrown()
      throws Exception {
    LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal(Locale.CANADA_FRENCH);
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.DoesNotExistL10nResourceBundle";

    localizerFactoryInternal.createTargetLocalizerBundle(localizerInternal,
        expectedResourceBundleName);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButResourceBundleForSimilarLocaleIsResolved_localizerBundleIsCreatedWithSimilarResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSimilarLocaleExists";
    final boolean expectedIsDefined = true;
    LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactoryInternal
        .createTargetLocalizerBundle(localizerInternal, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerInternal, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButRootResourceBundleIsResolved_localizerBundleIsCreatedWithRootResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.ROOT;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleButOnlyRootLocaleExists";
    final boolean expectedIsDefined = true;
    LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactoryInternal
        .createTargetLocalizerBundle(localizerInternal, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerInternal, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithNonExistentResourceBundleForLocaleButDefaultLocaleResourcebundleExists_localizerBundleIsCreatedWithDefaultLocaleResourcebundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.GERMANY;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleButOnlyDefaultLocaleExists";
    final boolean expectedIsDefined = true;
    Locale.setDefault(expectedResolvedLocale);
    LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactoryInternal
        .createTargetLocalizerBundle(localizerInternal, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerInternal, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithExistingPropertiesResourceBundleButAlsoExistingClassResourceBundle_localizerBundleIsCreatedWithPropertiesResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleEclipsedByClass";
    final boolean expectedIsDefined = true;
    Locale.setDefault(expectedResolvedLocale);
    LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactoryInternal
        .createTargetLocalizerBundle(localizerInternal, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerInternal, localizerBundle);
  }

  @Test
  public void Factory_createLocalizerBundleWithExistingResourceBundleForLocale_localizerBundleIsCreatedWithLocaleResourceBundle()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10nResourceBundleForSpecifiedLocaleExists";
    final boolean expectedIsDefined = true;

    LocalizerInternal localizerInternal =
        localizerFactoryInternal.createLocalizerInternal(expectedTargetLocale);

    LocalizerBundle localizerBundle = localizerFactoryInternal
        .createTargetLocalizerBundle(localizerInternal, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizerInternal, localizerBundle);
  }

  // Motivation: The Localizer and LocalizerType are both passed in as parameters to the Factory
  // create
  // methods. It is possible for the client to have implemented its own Localizer and LocalizerType
  // classes and passed them in. Simply using these externally implemented parameters without
  // checking first would cause unexpected behaviour. These external implementations may break the
  // implementation contracts, such as the value object pattern. Alternatively, checking to ensure
  // that these parameters are valid and copying them may also cause unintended side effects for
  // example creating/introducing unknown Localizers or LocalizerTypes into the system. However,
  // if the externally implemented types could be resolved to a unique instance of Localizer or
  // LocalizerType, that may be acceptable. This implies that I need some way to uniquely identify
  // the Localizer and LocalizerType instances. This is trivial for LocalizerType since each
  // instance already has a UID. I will need to do the same for Localizer, though. This in turn
  // implies that I need some means to retrieve known Localizer instances. For this, I'll likely
  // introduce a System object where both the Factory and list of Localizer instances can be found.
  // TODO implement the localizer data find and link for Localizer and LocalizerType.
  // TODO implement the safe copy functionality using the find and link functionality.

}
