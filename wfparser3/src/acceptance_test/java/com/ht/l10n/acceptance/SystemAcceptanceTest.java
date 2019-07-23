package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ht.common.UID;
import com.ht.l10n.Assert;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;
import com.ht.l10n.StubFactory;
import com.ht.l10n.System;

import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class SystemAcceptanceTest {
  private System localizerSystem;
  private Factory localizerFactory;
  private StubFactory localizerStubFactory;
  private Assert localizerAssert;
  private Localizer undefinedLocalizer;

  @Before
  public void setUp() throws Exception {
    localizerSystem = System.getSystem();
    localizerSystem.resetAll();
    localizerFactory = localizerSystem.getFactory();
    localizerStubFactory = StubFactory.createStubFactory();
    localizerAssert = Assert.createAssert();

    undefinedLocalizer = localizerStubFactory.createUndefinedLocalizer();
  }

  @Test
  public void System_createReusedInstances_reusedInstancesCreated() {
    assertNotNull(localizerSystem);
    assertNotNull(localizerFactory);
    assertNotNull(localizerStubFactory);
    assertNotNull(localizerAssert);

  }

  @Test
  public void System_getLocalizerSetWhenNoLocalizersCreated_emptyLocalizerSetReturned() {
    Set<UID<Localizer>> localizerSet = localizerSystem.getLocalizerUidSet();

    assertTrue(localizerSet.isEmpty());
    localizerAssert.assertSetIsUnmodifiable(localizerSet);
  }

  @Test(expected = NullPointerException.class)
  public void System_getLocalizerWithNullLocalizerUID_nullPointerExceptionIsThrown() {
    localizerSystem.getLocalizer(null);
  }

  @Test
  public void System_getLocalizerWithLocalizerUIDWhenNoLocalizersDefined_undefinedLocalizerIsReturned() {
    String expectedName = "UNDEFINED";
    UID<Localizer> expectedLocalizerUid =
        UID.createUid(expectedName, localizerStubFactory.createDefaultStubLocalizer());
    Locale expectedLocale = undefinedLocalizer.getLocale();
    boolean expectedIsDefined = false;
    UID<Localizer> nonExistentLocalizerUid =
        UID.createUid("not.important", localizerStubFactory.createDefaultStubLocalizer());

    Localizer localizer = localizerSystem.getLocalizer(nonExistentLocalizerUid);

    localizerAssert.assertExpectedLocalizer(expectedName, expectedLocalizerUid, expectedLocale,
        expectedIsDefined, localizer);
  }

  @Test
  public void System_getLocalizerSetWhenOneLocalizerCreated_localizerSetContainingExpectedLocalizerIsReturned()
      throws Exception {
    final int expectedSetSize = 1;
    final Localizer expectedLocalizer =
        localizerFactory.createLocalizer("test.localizer.name", Locale.CANADA_FRENCH);
    final UID<Localizer> expectedLocalizerUid = expectedLocalizer.getUid();

    Set<UID<Localizer>> localizerKeySet = localizerSystem.getLocalizerUidSet();

    assertNotNull(localizerKeySet);
    assertEquals(expectedSetSize, localizerKeySet.size());
    assertTrue(localizerKeySet.contains(expectedLocalizerUid));
  }

  @Test
  public void System_getLocalizerUsingLocalizerUIDForExistingLocalizerWhenLocalizersCreated_expectedLocalizerIsReturned()
      throws Exception {
    final Localizer expectedLocalizer =
        localizerFactory.createLocalizer("test.localizer.name", Locale.CANADA_FRENCH);
    final UID<Localizer> expectedLocalizerUid = expectedLocalizer.getUid();

    Localizer localizer = localizerSystem.getLocalizer(expectedLocalizerUid);

    localizerAssert.assertExpectedLocalizer(expectedLocalizer.getName(), expectedLocalizerUid,
        expectedLocalizer.getLocale(), expectedLocalizer.isDefined(), localizer);
  }

  @Test
  public void System_getLocalizerUsingLocalizerUIDForNonExistingLocalizerWhenLocalizersCreated_undefinedLocalizerIsReturned()
      throws Exception {
    localizerFactory.createLocalizer("test.localzer.name.00", Locale.CANADA_FRENCH);
    localizerFactory.createLocalizer("test.localizer.name.01", Locale.CANADA_FRENCH);
    final Localizer expectedLocalizer = undefinedLocalizer;
    final UID<Localizer> localizerUid = UID.createUid("non.existent.localizer.uid",
        localizerStubFactory.createDefaultStubLocalizer());

    Localizer localizer = localizerSystem.getLocalizer(localizerUid);

    localizerAssert.assertExpectedLocalizer(expectedLocalizer.getName(), expectedLocalizer.getUid(),
        expectedLocalizer.getLocale(), expectedLocalizer.isDefined(), localizer);
  }
}

