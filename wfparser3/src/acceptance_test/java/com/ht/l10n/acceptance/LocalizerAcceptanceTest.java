package com.ht.l10n.acceptance;

import static org.junit.Assert.*;
import java.util.Locale;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;

public class LocalizerAcceptanceTest {
  private Factory localizerFactory;

  // TODO shouldn't the LocalizerBundle Set be an OrderedSet since the LocalizerBundle instances
  // will be checked for the localized string in a defined order? This may have implications on how
  // the LocalizerBundle instances are added to the Localizer but the API could be enhanced to
  // provide "insert()" methods afterwards.

  // TODO ensure to test that all returned Set instances are unmodifiable.

  @Before
  public void setup() {
    localizerFactory = Factory.createFactory();
  }

  @Test
  public void Localizer_createFactory_factoryIsCreated() {
    assertNotNull(localizerFactory);
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
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerBundleSetWhenLocalizerBundlesAdded_allAddedLocalizerBundlesArePresent() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not implemented yet")
  public void Localizer_getLocalizerTypeWithNullParameter_nullPointerExceptionIsThrown() {
    fail("not implemented yet");
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
