package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ht.common.UID;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.LocalizerType;
import com.ht.l10n.StubFactory;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LocalizerAcceptanceTest {
  private Factory localizerFactory;
  private StubFactory stubFactory;

  // TODO shouldn't the LocalizerBundle Set be an OrderedSet since the LocalizerBundle instances
  // will be checked for the localized string in a defined order? This may have implications on how
  // the LocalizerBundle instances are added to the Localizer but the API could be enhanced to
  // provide "insert()" methods afterwards.

  // TODO ensure to test that all returned Set instances are unmodifiable.

  @Before
  public void setup() {
    localizerFactory = Factory.createFactory();
    stubFactory = StubFactory.createStubFactory();
  }

  @Test
  public void Localizer_createFactories_factoriesAreCreated() {
    assertNotNull(localizerFactory);
    assertNotNull(stubFactory);
  }

  @Test(expected = NullPointerException.class)
  public void Localizer_setLocaleToNull_nullPointerExceptionIsThrown() throws Exception {
    Localizer localize = localizerFactory.createLocalizer(Locale.CHINESE);
    localize.setLocale(null);
  }

  @Test
  public void Localizer_setLocaleToANewLocale_localeChangedToNewLocale() throws Exception {
    Locale originalLocale = Locale.CHINESE;
    Locale newLocale = Locale.CANADA_FRENCH;
    Localizer localize = localizerFactory.createLocalizer(originalLocale);

    assertNotNull(localize);
    assertNotNull(localize.getLocale());
    assertEquals(originalLocale, localize.getLocale());

    localize.setLocale(newLocale);
    assertNotNull(localize.getLocale());
    assertEquals(newLocale, localize.getLocale());
  }

  @Test
  public void Localizer_getLocalizerBundleSetWhenNoLocalizerBundlesAdded_emptySetIsReturned() {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    assertNotNull(localizer);
    assertTrue(localizer.getLocalizerBundleSet().isEmpty());
  }

  @Test
  public void Localizer_getLocalizerBundleSetWhenLocalizerBundlesAdded_allAddedLocalizerBundlesArePresent()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    final int expectedLocalizerBundleSetSize = 2;
    final LocalizerBundle expectedLocalizerBundle00 = localizerFactory
        .createLocalizerBundle(localizer, "com.ht.l10n.test.resource.TestResourceBundle00");
    final LocalizerBundle expectedLocalizerBundle01 = localizerFactory
        .createLocalizerBundle(localizer, "com.ht.l10n.test.resource.TestResourceBundle01");

    assertNotNull(localizer);
    assertNotNull(expectedLocalizerBundle00);
    assertNotNull(expectedLocalizerBundle01);
    assertNotNull(localizer.getLocalizerBundleSet());
    assertTrue(expectedLocalizerBundleSetSize == localizer.getLocalizerBundleSet().size());
    assertTrue(localizer.getLocalizerBundleSet().contains(expectedLocalizerBundle00));
    assertTrue(localizer.getLocalizerBundleSet().contains(expectedLocalizerBundle01));
    boolean isModifiable = true;
    try {
      Set<LocalizerBundle> unmodifiableLocalizerBundleSet = localizer.getLocalizerBundleSet();
      unmodifiableLocalizerBundleSet.clear();
    } catch (UnsupportedOperationException uoe) {
      isModifiable = false;
    }
    assertFalse(isModifiable);
  }

  @Test(expected = NullPointerException.class)
  public void Localizer_getLocalizerTypeWithNullParameter_nullPointerExceptionIsThrown() {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    localizer.getLocalizerType(null);
  }

  @Test
  public void Localizer_getLocalizerTypeForNonExistentLocalizerType_undefinedLocalizerTypeIsReturned()
      throws Exception {
    Localizer someOtherLocalizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    LocalizerType someOtherocalizerType = localizerFactory.createLocalizerType(someOtherLocalizer,
        "test.group", "test.type", "test.instance");
    UID<LocalizerType> localizerTypeUid = someOtherocalizerType.getUid();
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    LocalizerType localizerType = localizer.getLocalizerType(localizerTypeUid);

    assertNotNull(localizerType);
    assertFalse(localizerType.isDefined());
  }

  @Test
  public void Localizer_getLocalizerTypeForExistentLocalizerType_requestedLocalizerTypeIsReturned()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    LocalizerType expectedLocalizerType =
        localizerFactory.createLocalizerType(localizer, "test.group", "test.type", "test.instance");

    LocalizerType localizerType = localizer.getLocalizerType(expectedLocalizerType.getUid());

    assertNotNull(localizerType);
    assertEquals(expectedLocalizerType, localizerType);
  }

  @Test
  public void Localizer_getLocalizerTypeKeySetWhenNoLocalizerTypesAdded_emptySetIsReturned() {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    assertNotNull(localizer.getLocalizerTypeKeySet());
    assertTrue(localizer.getLocalizerTypeKeySet().isEmpty());
  }

  @Test
  public void Localizer_getLocalizerTypeKeySetWhenLocalizerTypesAdded_allAddedLocalizerTypesKeysArePresent()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    LocalizerType expectedLocalizerType00 = localizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.intance.00");
    LocalizerType expectedLocalizerType01 = localizerFactory.createLocalizerType(localizer,
        "test.group", "test.name", "test.instance.01");

    Set<UID<LocalizerType>> localizerTypeUidSet = localizer.getLocalizerTypeKeySet();

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
  public void Localizer_getLocalizerFieldWithNullParameter_nullPointerExceptionIsThrown() {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    localizer.getLocalizerField(null);
  }

  @Test
  public void Localizer_getLocalizerFieldForNonExistentLocalizerField_undefinedLocalizerFieldIsReturned()
      throws Exception {
    Localizer someOtherLocalizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    LocalizerType someOtherLocalizerType = localizerFactory.createLocalizerType(someOtherLocalizer,
        "other.group", "other.type", "other.instance");
    LocalizerField someOtherLocalizerField =
        localizerFactory.createLocalizerField(someOtherLocalizerType, "other.field");
    Localizer localizer = localizerFactory.createLocalizer((Locale.CANADA_FRENCH));

    LocalizerField localizerField = localizer.getLocalizerField(someOtherLocalizerField.getUid());

    assertNotNull(localizerField);
    assertFalse(localizerField.isDefined());
  }

  @Test
  public void Localizer_getLocalizerFieldForExistentLocalizerField_requestedLocalizerFieldIsReturned()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    LocalizerType localizerType = localizerFactory.createLocalizerType(localizer, "test.group",
        "test.type", "test.instance.00");
    LocalizerField expectedLocalizerField =
        localizerFactory.createLocalizerField(localizerType, "test.field.00");

    LocalizerField localizerField = localizer.getLocalizerField(expectedLocalizerField.getUid());

    assertNotNull(localizerField);
    assertEquals(expectedLocalizerField, localizerField);
  }

  @Test
  public void Localizer_addLocalizerFieldsWithSameFieldNameButDifferntLocalizerTypesAndGetBothLocalizerFields_bothRequestedLocalizerFieldsAreReturned()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    LocalizerType localizerType00 = localizerFactory.createLocalizerType(localizer, "test.group",
        "test.type", "test.instance.00");
    LocalizerField localizerField00 =
        localizerFactory.createLocalizerField(localizerType00, "test.field");
    LocalizerType localizerType01 = localizerFactory.createLocalizerType(localizer, "test.group",
        "test.type", "test.instance.01");
    LocalizerField localizerField01 =
        localizerFactory.createLocalizerField(localizerType01, "test.field");

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
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    localizerFactory.createLocalizerType(localizer, "test.group", "test.type", "test.instance.00");
    localizerFactory.createLocalizerType(localizer, "test.group", "test.type", "test.instance.01");
    localizerFactory.createLocalizerType(localizer, "test.group", "test.type", "test.instance.02");

    Set<UID<LocalizerField>> localizerFieldUidSet = localizer.getLocalizerFieldKeySet();

    assertNotNull(localizerFieldUidSet);
    assertTrue(localizerFieldUidSet.isEmpty());
  }

  @Test
  public void Localizer_getLocalizerFieldKeySetWhenNoLocalizerKeysAndNoLocalizerFields_emptySetIsReturned() {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    Set<UID<LocalizerField>> localizerFieldUidSet = localizer.getLocalizerFieldKeySet();

    assertNotNull(localizerFieldUidSet);
    assertTrue(localizerFieldUidSet.isEmpty());
  }

  @Test
  public void Localizer_getLocalizerFieldKeySetWhenLocalizerFieldsForLocalizerTypesAdded_allLocalizerFieldsForAllLocalizerTypesAreReturned()
      throws Exception {
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);
    LocalizerType localizerType00 = localizerFactory.createLocalizerType(localizer, "test.group",
        "test.type", "test.instance.00");
    LocalizerField localizerField00 =
        localizerFactory.createLocalizerField(localizerType00, "test.field.00");
    LocalizerField localizerField01 =
        localizerFactory.createLocalizerField(localizerType00, "test.field.01");
    LocalizerType localizerType01 = localizerFactory.createLocalizerType(localizer, "test.group",
        "test.type", "test.instance.01");
    LocalizerField localizerField02 =
        localizerFactory.createLocalizerField(localizerType01, "test.field.02");

    Set<UID<LocalizerField>> localizerFieldUidSet = localizer.getLocalizerFieldKeySet();

    assertNotNull(localizerFieldUidSet);
    for (UID<LocalizerField> expectedLocalizerFieldUid : Arrays.asList(localizerField00.getUid(),
        localizerField01.getUid(), localizerField02.getUid())) {
      assertTrue(localizerFieldUidSet.contains(expectedLocalizerFieldUid));
    }
  }

  @Test
  public void Localizer_addOneLocalizerBundleThenSetLocaleToNewSupportedLocale_LocalizerBundleReloadedForNewLocale()
      throws Exception {
    Locale startingLocale = Locale.ENGLISH;
    Locale expectedLocale = Locale.CANADA_FRENCH;
    Localizer localizer = localizerFactory.createLocalizer(startingLocale);
    LocalizerBundle localizerBundle = localizerFactory.createLocalizerBundle(localizer,
        "com.ht.l10n.test.resource.TestL10nResourceBundleChangeToExistingLocale");
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
    final Locale startingLocale = Locale.ENGLISH;
    final Locale expectedLocale = Locale.CANADA_FRENCH;
    final Localizer localizer = localizerFactory.createLocalizer(startingLocale);
    final String localizerBundleName00 =
        "com.ht.l10n.test.resource.TestL10nResourceBundleTestMultiple00";
    final String localizerBundleName01 =
        "com.ht.l10n.test.resource.TestL10nResourceBundleTestMultiple01";
    LocalizerBundle localizerBundle00 =
        localizerFactory.createLocalizerBundle(localizer, localizerBundleName00);
    assertNotNull(localizerBundle00);
    assertEquals(startingLocale, localizerBundle00.getTargetLocale());
    assertEquals(startingLocale, localizerBundle00.getResolvedLocale());
    LocalizerBundle localizerBundle01 =
        localizerFactory.createLocalizerBundle(localizer, localizerBundleName01);
    assertNotNull(localizerBundle00);
    assertEquals(startingLocale, localizerBundle00.getTargetLocale());
    assertEquals(startingLocale, localizerBundle00.getResolvedLocale());

    localizer.setLocale(expectedLocale);

    assertEquals(expectedLocale, localizerBundle00.getTargetLocale());
    assertEquals(expectedLocale, localizerBundle00.getResolvedLocale());
    assertEquals(expectedLocale, localizerBundle01.getTargetLocale());
    assertEquals(expectedLocale, localizerBundle01.getResolvedLocale());
  }
}
