package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ht.l10n.Assert;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerException;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.LocalizerType;
import com.ht.l10n.StubLocalizerFactory;
import com.ht.l10n.TestableLocalizerFactory;
import com.ht.uid.UID;
import com.ht.wrap.ResourceBundleWrapperConfigurator;
import com.ht.wrap.StubWrapperFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LocalizerAcceptanceTest {
  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private StubWrapperFactory stubWrapperFactory;
  private Assert localizerAssert;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;


  @Before
  public void setup() {
    testableLocalizerFactory = TestableLocalizerFactory.getTestableLocalizerFactory();
    testableLocalizerFactory.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    stubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
    resourceBundleWrapperForLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForLocaleConfigurator();
    resourceBundleWrapperForRootLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForRootLocaleConfigurator();
    testableLocalizerFactory.setWrapperFactory(stubWrapperFactory);
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void Localizer_createTestingAssets_testingAssetsAreCreated() {
    assertNotNull(testableLocalizerFactory);
    assertNotNull(stubLocalizerFactory);
    assertNotNull(stubWrapperFactory);
    assertNotNull(resourceBundleWrapperForLocaleConfigurator);
    assertNotNull(resourceBundleWrapperForRootLocaleConfigurator);
    assertNotNull(localizerAssert);
  }

  @Test(expected = NullPointerException.class)
  public void Localizer_setLocaleToNull_nullPointerExceptionIsThrown() throws Exception {
    Localizer localize = testableLocalizerFactory.createLocalizer("localizer.name", Locale.CHINESE);
    localize.setLocale(null);
  }

  @Test
  public void Localizer_setLocaleToANewLocale_localeChangedToNewLocale() throws Exception {
    final String expectedName = "localizer.name";
    final UID<Localizer> expectedLocalizerUid =
        UID.createUid(expectedName, stubLocalizerFactory.createDefaultStubLocalizer());
    final Locale originalLocale = Locale.CHINESE;
    final Locale newLocale = Locale.CANADA_FRENCH;
    final boolean expectedIsDefined = true;
    Localizer localizer = testableLocalizerFactory.createLocalizer(expectedName, originalLocale);

    localizerAssert.assertExpectedLocalizer(expectedName, expectedLocalizerUid, originalLocale,
        expectedIsDefined, localizer);

    localizer.setLocale(newLocale);

    localizerAssert.assertExpectedLocalizer(expectedName, expectedLocalizerUid, newLocale,
        expectedIsDefined, localizer);
  }

  @Test
  public void Localizer_getLocalizerBundleSetWhenNoLocalizerBundlesAdded_emptySetIsReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    assertNotNull(localizer);
    assertTrue(localizer.getLocalizerBundleSet().isEmpty());
  }

  @Test
  public void Localizer_getLocalizerBundleSetWhenLocalizerBundlesAdded_allAddedLocalizerBundlesArePresent()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    final LocalizerBundle expectedLocalizerBundle00 = testableLocalizerFactory
        .createLocalizerBundle(localizer, "com.resource.bundle.name.DoesNotMatter00");
    final LocalizerBundle expectedLocalizerBundle01 = testableLocalizerFactory
        .createLocalizerBundle(localizer, "com.resource.bundle.name.DoesNotMatter01");
    List<LocalizerBundle> expectedLocalizerBundleList =
        Arrays.asList(expectedLocalizerBundle00, expectedLocalizerBundle01);
    final int expectedLocalizerBundleSetSize = expectedLocalizerBundleList.size();

    assertNotNull(localizer);
    assertNotNull(expectedLocalizerBundle00);
    assertNotNull(expectedLocalizerBundle01);
    assertNotNull(localizer.getLocalizerBundleSet());
    assertTrue(expectedLocalizerBundleSetSize == localizer.getLocalizerBundleSet().size());
    int i = 0;
    for (LocalizerBundle localizerBundle : localizer.getLocalizerBundleSet()) {
      assertEquals(expectedLocalizerBundleList.get(i), localizerBundle);
      i++;
    }


    assertTrue(localizer.getLocalizerBundleSet().contains(expectedLocalizerBundle00));
    assertTrue(localizer.getLocalizerBundleSet().contains(expectedLocalizerBundle01));
    localizerAssert.assertSetIsUnmodifiable(localizer.getLocalizerBundleSet());
  }

  @Test(expected = NullPointerException.class)
  public void Localizer_getLocalizerTypeWithNullParameter_nullPointerExceptionIsThrown()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    localizer.getLocalizerType(null);
  }

  @Test
  public void Localizer_getLocalizerTypeForNonExistentLocalizerType_undefinedLocalizerTypeIsReturned()
      throws Exception {
    Localizer someOtherLocalizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType someOtherocalizerType = testableLocalizerFactory
        .createLocalizerType(someOtherLocalizer, "test.group", "test.type", "test.instance");
    UID<LocalizerType> localizerTypeUid = someOtherocalizerType.getUid();
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    LocalizerType localizerType = localizer.getLocalizerType(localizerTypeUid);

    assertNotNull(localizerType);
    assertFalse(localizerType.isDefined());
  }

  @Test
  public void Localizer_getLocalizerTypeForExistentLocalizerType_requestedLocalizerTypeIsReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType expectedLocalizerType = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.instance");

    LocalizerType localizerType = localizer.getLocalizerType(expectedLocalizerType.getUid());

    assertNotNull(localizerType);
    assertEquals(expectedLocalizerType, localizerType);
  }

  @Test
  public void Localizer_getLocalizerTypeKeySetWhenNoLocalizerTypesAdded_emptySetIsReturned()
      throws LocalizerException {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    assertNotNull(localizer.getLocalizerTypeUidSet());
    assertTrue(localizer.getLocalizerTypeUidSet().isEmpty());
  }

  @Test
  public void Localizer_getLocalizerTypeKeySetWhenLocalizerTypesAdded_allAddedLocalizerTypesKeysArePresent()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType expectedLocalizerType00 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.intance.00");
    LocalizerType expectedLocalizerType01 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.name", "test.instance.01");

    Set<UID<LocalizerType>> localizerTypeUidSet = localizer.getLocalizerTypeUidSet();

    assertNotNull(localizerTypeUidSet);
    assertTrue(localizerTypeUidSet.contains(expectedLocalizerType00.getUid()));
    assertTrue(localizerTypeUidSet.contains(expectedLocalizerType01.getUid()));
    boolean isModifiable = true;
    try {
      localizerTypeUidSet.clear();
    } catch (UnsupportedOperationException uoe) {
      isModifiable = false;
    }
    assertFalse(isModifiable);
  }

  @Test(expected = NullPointerException.class)
  public void Localizer_getLocalizerFieldWithNullParameter_nullPointerExceptionIsThrown()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    localizer.getLocalizerField(null);
  }

  @Test
  public void Localizer_getLocalizerFieldForNonExistentLocalizerField_undefinedLocalizerFieldIsReturned()
      throws Exception {
    Localizer someOtherLocalizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType someOtherLocalizerType = testableLocalizerFactory
        .createLocalizerType(someOtherLocalizer, "other.group", "other.type", "other.instance");
    LocalizerField someOtherLocalizerField =
        testableLocalizerFactory.createLocalizerField(someOtherLocalizerType, "other.field");
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    LocalizerField localizerField = localizer.getLocalizerField(someOtherLocalizerField.getUid());

    assertNotNull(localizerField);
    assertFalse(localizerField.isDefined());
  }

  @Test
  public void Localizer_getLocalizerFieldForExistentLocalizerField_requestedLocalizerFieldIsReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType localizerType = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.instance.00");
    LocalizerField expectedLocalizerField =
        testableLocalizerFactory.createLocalizerField(localizerType, "test.field.00");

    LocalizerField localizerField = localizer.getLocalizerField(expectedLocalizerField.getUid());

    assertNotNull(localizerField);
    assertEquals(expectedLocalizerField, localizerField);
  }

  @Test
  public void Localizer_addLocalizerFieldsWithSameFieldNameButDifferntLocalizerTypesAndGetBothLocalizerFields_bothRequestedLocalizerFieldsAreReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType localizerType00 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.instance.00");
    LocalizerField localizerField00 =
        testableLocalizerFactory.createLocalizerField(localizerType00, "test.field");
    LocalizerType localizerType01 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.instance.01");
    LocalizerField localizerField01 =
        testableLocalizerFactory.createLocalizerField(localizerType01, "test.field");

    for (LocalizerField expectedLocalizerField : Arrays.asList(localizerField00,
        localizerField01)) {
      assertNotNull(localizer.getLocalizerField(expectedLocalizerField.getUid()));
      assertEquals(expectedLocalizerField,
          localizer.getLocalizerField(expectedLocalizerField.getUid()));
    }
  }

  @Test
  public void Localizer_getLocalizerFieldKeySetWhenLocalizerKeysButNoLocalizerFields_emptySetIsReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    testableLocalizerFactory.createLocalizerType(localizer, "test.group", "test.type",
        "test.instance.00");
    testableLocalizerFactory.createLocalizerType(localizer, "test.group", "test.type",
        "test.instance.01");
    testableLocalizerFactory.createLocalizerType(localizer, "test.group", "test.type",
        "test.instance.02");

    Set<UID<LocalizerField>> localizerFieldUidSet = localizer.getLocalizerFieldUidSet();

    assertNotNull(localizerFieldUidSet);
    assertTrue(localizerFieldUidSet.isEmpty());
  }

  @Test
  public void Localizer_getLocalizerFieldKeySetWhenNoLocalizerKeysAndNoLocalizerFields_emptySetIsReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    Set<UID<LocalizerField>> localizerFieldUidSet = localizer.getLocalizerFieldUidSet();

    assertNotNull(localizerFieldUidSet);
    assertTrue(localizerFieldUidSet.isEmpty());
  }

  @Test
  public void Localizer_getLocalizerFieldKeySetWhenLocalizerFieldsForLocalizerTypesAdded_allLocalizerFieldsForAllLocalizerTypesAreReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType localizerType00 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.instance.00");
    LocalizerField localizerField00 =
        testableLocalizerFactory.createLocalizerField(localizerType00, "test.field.00");
    LocalizerField localizerField01 =
        testableLocalizerFactory.createLocalizerField(localizerType00, "test.field.01");
    LocalizerType localizerType01 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.instance.01");
    LocalizerField localizerField02 =
        testableLocalizerFactory.createLocalizerField(localizerType01, "test.field.02");

    Set<UID<LocalizerField>> localizerFieldUidSet = localizer.getLocalizerFieldUidSet();

    assertNotNull(localizerFieldUidSet);
    for (UID<LocalizerField> expectedLocalizerFieldUid : Arrays.asList(localizerField00.getUid(),
        localizerField01.getUid(), localizerField02.getUid())) {
      assertTrue(localizerFieldUidSet.contains(expectedLocalizerFieldUid));
    }
  }

  @Test
  public void Localizer_addOneLocalizerBundleThenSetLocaleToNewSupportedLocale_LocalizerBundleReloadedForNewLocale()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    Locale startingLocale = Locale.ENGLISH;
    Locale expectedLocale = Locale.CANADA_FRENCH;
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", startingLocale);
    LocalizerBundle localizerBundle = testableLocalizerFactory.createLocalizerBundle(localizer,
        "com.resource.bundle.name.DoesNotMatter");
    assertNotNull(localizerBundle);
    assertEquals(startingLocale, localizerBundle.getTargetLocale());
    assertEquals(startingLocale, localizerBundle.getResolvedLocale());

    localizer.setLocale(expectedLocale);

    assertEquals(expectedLocale, localizerBundle.getTargetLocale());
    assertEquals(expectedLocale, localizerBundle.getResolvedLocale());
  }

  @Test
  public void Localizer_addMultipleLocalizerBundlesThenSetLocaleToNewSupportedLocale_allLocalizerBundlesReloadedForNewLocale()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    final Locale startingLocale = Locale.ENGLISH;
    final Locale expectedLocale = Locale.CANADA_FRENCH;
    final Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", startingLocale);
    final String localizerBundleName00 = "com.resource.bundle.name.DoesNotMatter00";
    final String localizerBundleName01 = "com.resource.bundle.name.DoesNotMatter01";
    LocalizerBundle localizerBundle00 =
        testableLocalizerFactory.createLocalizerBundle(localizer, localizerBundleName00);
    assertNotNull(localizerBundle00);
    assertEquals(startingLocale, localizerBundle00.getTargetLocale());
    assertEquals(startingLocale, localizerBundle00.getResolvedLocale());
    LocalizerBundle localizerBundle01 =
        testableLocalizerFactory.createLocalizerBundle(localizer, localizerBundleName01);
    assertNotNull(localizerBundle01);
    assertEquals(startingLocale, localizerBundle01.getTargetLocale());
    assertEquals(startingLocale, localizerBundle01.getResolvedLocale());

    localizer.setLocale(expectedLocale);

    assertEquals(expectedLocale, localizerBundle00.getTargetLocale());
    assertEquals(expectedLocale, localizerBundle00.getResolvedLocale());
    assertEquals(expectedLocale, localizerBundle01.getTargetLocale());
    assertEquals(expectedLocale, localizerBundle01.getResolvedLocale());
  }
}
