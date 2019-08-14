package com.ht.localizer.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import com.ht.localizer.Assert;
import com.ht.localizer.Localizer;
import com.ht.localizer.LocalizerSystem;
import com.ht.localizer.StubLocalizerFactory;
import com.ht.localizer.TestableLocalizerFactory;
import com.ht.uid.UID;

public class LocalizerSystemAcceptanceTest {
  private LocalizerSystem localizerSystem;
  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private Localizer stubLocalizer;
  private Assert localizerAssert;
  private Localizer undefinedLocalizer;

  @Before
  public void setUp() throws Exception {
    localizerSystem = LocalizerSystem.getSystem();
    localizerSystem.resetAll();
    testableLocalizerFactory =
        TestableLocalizerFactory.getTestableLocalizerFactory(localizerSystem);
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    stubLocalizer = stubLocalizerFactory.createLocalizer("stub.localizer", Locale.CANADA_FRENCH);
    localizerAssert = Assert.createAssert();

    undefinedLocalizer = testableLocalizerFactory.getUndefinedLocalizer();
  }

  @Test
  public void System_createTestingAssets_testingAssetsCreated() {
    assertNotNull(localizerSystem);
    assertNotNull(testableLocalizerFactory);
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
        testableLocalizerFactory.getUidFactory().createUid(expectedName, stubLocalizer);
    Locale expectedLocale = undefinedLocalizer.getLocale();
    boolean expectedIsDefined = false;
    UID<Localizer> nonExistentLocalizerUid =
        testableLocalizerFactory.getUidFactory().createUid("not.important", stubLocalizer);

    Localizer localizer = localizerSystem.getLocalizer(nonExistentLocalizerUid);

    localizerAssert.assertExpectedLocalizer(expectedName, expectedLocalizerUid, expectedLocale,
        expectedIsDefined, localizer);
  }

  @Test
  public void System_getLocalizerSetWhenOneLocalizerCreated_localizerSetContainingExpectedLocalizerIsReturned()
      throws Exception {
    final int expectedSetSize = 1;
    final Localizer expectedLocalizer =
        testableLocalizerFactory.createLocalizer("test.localizer.name", Locale.CANADA_FRENCH);
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
        testableLocalizerFactory.createLocalizer("test.localizer.name", Locale.CANADA_FRENCH);
    final UID<Localizer> expectedLocalizerUid = expectedLocalizer.getUid();

    Localizer localizer = localizerSystem.getLocalizer(expectedLocalizerUid);

    localizerAssert.assertExpectedLocalizer(expectedLocalizer.getName(), expectedLocalizerUid,
        expectedLocalizer.getLocale(), expectedLocalizer.isDefined(), localizer);
  }

  @Test
  public void System_getLocalizerUsingLocalizerUIDForNonExistingLocalizerWhenLocalizersCreated_undefinedLocalizerIsReturned()
      throws Exception {
    testableLocalizerFactory.createLocalizer("test.localizer.name.00", Locale.CANADA_FRENCH);
    testableLocalizerFactory.createLocalizer("test.localizer.name.01", Locale.CANADA_FRENCH);
    final Localizer expectedLocalizer = undefinedLocalizer;
    final UID<Localizer> localizerUid = testableLocalizerFactory.getUidFactory()
        .createUid("non.existent.localizer.uid", stubLocalizer);

    Localizer localizer = localizerSystem.getLocalizer(localizerUid);

    localizerAssert.assertExpectedLocalizer(expectedLocalizer.getName(), expectedLocalizer.getUid(),
        expectedLocalizer.getLocale(), expectedLocalizer.isDefined(), localizer);
  }
}

