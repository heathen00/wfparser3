package com.ht.localizer.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.ht.localizer.Assert;
import com.ht.localizer.Localizer;
import com.ht.localizer.LocalizerFactory;
import com.ht.localizer.StubLocalizerFactory;
import com.ht.localizer.LocalizerSystem;
import com.ht.uid.UID;

import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LocalizerSystemAcceptanceTest {
  private LocalizerSystem localizerSystem;
  private LocalizerFactory localizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private Assert localizerAssert;
  private Localizer undefinedLocalizer;

  @Before
  public void setUp() throws Exception {
    localizerSystem = LocalizerSystem.getSystem();
    localizerSystem.resetAll();
    localizerFactory = localizerSystem.getLocalizerFactory();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    localizerAssert = Assert.createAssert();

    undefinedLocalizer = stubLocalizerFactory.createUndefinedLocalizer();
  }

  @Test
  public void System_createTestingAssets_testingAssetsCreated() {
    assertNotNull(localizerSystem);
    assertNotNull(localizerFactory);
    assertNotNull(stubLocalizerFactory);
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
        UID.createUid(expectedName, stubLocalizerFactory.createDefaultStubLocalizer());
    Locale expectedLocale = undefinedLocalizer.getLocale();
    boolean expectedIsDefined = false;
    UID<Localizer> nonExistentLocalizerUid =
        UID.createUid("not.important", stubLocalizerFactory.createDefaultStubLocalizer());

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
    localizerFactory.createLocalizer("test.localizer.name.00", Locale.CANADA_FRENCH);
    localizerFactory.createLocalizer("test.localizer.name.01", Locale.CANADA_FRENCH);
    final Localizer expectedLocalizer = undefinedLocalizer;
    final UID<Localizer> localizerUid = UID.createUid("non.existent.localizer.uid",
        stubLocalizerFactory.createDefaultStubLocalizer());

    Localizer localizer = localizerSystem.getLocalizer(localizerUid);

    localizerAssert.assertExpectedLocalizer(expectedLocalizer.getName(), expectedLocalizer.getUid(),
        expectedLocalizer.getLocale(), expectedLocalizer.isDefined(), localizer);
  }
}
