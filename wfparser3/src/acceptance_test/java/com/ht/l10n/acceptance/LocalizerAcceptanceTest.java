package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.StubFactory;

import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
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
  public void Localizer_setLocaleToNull_nullPointerExceptionIsThrown() {
    Localizer localize = localizerFactory.createLocalizer(Locale.CHINESE);
    localize.setLocale(null);
  }

  @Test
  public void Localizer_setLocaleToANewLocale_localeChangedToNewLocale() {
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
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerTypeForNonExistentLocalizerType_undefinedLocalizerTypeIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerTypeForExistentLocalizerType_requestedLocalizerTypeIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerTypeKeySetWhenNoLocalizerTypesAdded_emptySetIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerTypeKeySetWhenLocalizerTypesAdded_allAddedLocalizerTypesKeysArePresent() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerFieldWithNullParameter_nullPointerExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerFieldForNonExistentLocalizerField_undefinedLocalizerFieldIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerFieldForExistentLocalizerField_requestedLocalizerFieldIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_addLocalizerFieldsWithSameFieldNameButDifferntLocalizerTypesAndGetBothLocalizerFields_bothRequestedLocalizerFieldsAreReturned() {
    // TODO write test for getLocalizerField where you have two LocalizerField instances that have
    // the
    // same field name but belong to two different LocalizerType instances. It must be supported and
    // behave as expected. That is, the correct LocalizerField for the correct LocalizerType is
    // always
    // returned. This behaviour should be compatible with getLocalizerKeySet, too, since the
    // LocalizerField UID keys are defined as the LocalizerField fully qualified names.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerFieldKeySetWhenLocalizerKeysButNoLocalizerFields_emptySetIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerFieldKeySetWhenNoLocalizerKeysAndNoLocalizerFields_emptySetIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerFieldKeySetWhenLocalizerFieldsForLocalizerTypesAdded_allLocalizerFieldsForAllLocalizerTypesAreReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_addOneLocalizerBundleThenSetLocaleToNewSupportedLocale_LocalizerBundleReloadedForNewLocale() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_addLocalizerBundleThenSetLocaleToNewUnsupportedLocale_localizerExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_addMultipleLocalizerBundlesThenSetLocaleToNewSupportedLocale_allLocalizerBundlesReloadedForNewLocale() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_addMultipleLocalizerBundlesThenSetLocaleToNewUnsupportedLocale_localizerExceptionIsThrown() {
    fail("not implemented yet");
  }
}
