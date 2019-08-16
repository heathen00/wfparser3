package com.ht.localizer.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ht.localizer.Assert;
import com.ht.localizer.Localizer;
import com.ht.localizer.LocalizerBundle;
import com.ht.localizer.LocalizerException;
import com.ht.localizer.LocalizerInstance;
import com.ht.localizer.LocalizerType;
import com.ht.localizer.StubLocalizerFactory;
import com.ht.localizer.TestableLocalizerFactory;
import com.ht.uid.Uid;
import com.ht.uid.UidFactory;
import com.ht.wrap.ResourceBundleWrapperConfigurator;
import com.ht.wrap.StubWrapperFactory;
import com.ht.wrap.WrapperFactory;

public class LocalizerAcceptanceTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private Localizer stubLocalizer;
  private StubWrapperFactory stubWrapperFactory;
  private Assert localizerAssert;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;


  @Before
  public void setup() throws Exception {
    testableLocalizerFactory = TestableLocalizerFactory.getTestableLocalizerFactory(
        WrapperFactory.createWrapperFactory(), UidFactory.createUidFactory());
    testableLocalizerFactory.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    stubLocalizer = stubLocalizerFactory.createLocalizer("stub.localizer", Locale.CANADA_FRENCH);
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
    assertNotNull(stubLocalizer);
    assertNotNull(stubWrapperFactory);
    assertNotNull(resourceBundleWrapperForLocaleConfigurator);
    assertNotNull(resourceBundleWrapperForRootLocaleConfigurator);
    assertNotNull(localizerAssert);
  }

  @Test
  public void Localizer_setLocaleToNull_nullPointerExceptionIsThrown() throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("locale cannot be null");

    Localizer localize = testableLocalizerFactory.createLocalizer("localizer.name", Locale.CHINESE);
    localize.setLocale(null);
  }

  @Test
  public void Localizer_setLocaleToANewLocale_localeChangedToNewLocale() throws Exception {
    final String expectedName = "localizer.name";
    final Uid<Localizer> expectedLocalizerUid =
        UidFactory.createUidFactory().createUid(expectedName, stubLocalizer);
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

  @Test
  public void Localizer_getLocalizerTypeWithNullParameter_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("typeUid cannot be null");

    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    localizer.getLocalizerType(null);
  }

  @Test
  public void Localizer_getLocalizerTypeForNonExistentLocalizerType_undefinedLocalizerTypeIsReturned()
      throws Exception {
    Localizer someOtherLocalizer =
        testableLocalizerFactory.createLocalizer("localizer.name.one", Locale.CANADA_FRENCH);
    LocalizerType someOtherocalizerType = testableLocalizerFactory
        .createLocalizerType(someOtherLocalizer, "test.group", "test.type", "test.method");
    Uid<LocalizerType> localizerTypeUid = someOtherocalizerType.getUid();
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name.two", Locale.CANADA_FRENCH);

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
        "test.group", "test.type", "test.method");

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
        "test.group", "test.type", "test.method.00");
    LocalizerType expectedLocalizerType01 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.name", "test.method.01");

    Set<Uid<LocalizerType>> localizerTypeUidSet = localizer.getLocalizerTypeUidSet();

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

  @Test
  public void Localizer_getLocalizerInstanceWithNullParameter_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("instanceUid cannot be null");

    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    localizer.getLocalizerInstance(null);
  }

  @Test
  public void Localizer_getLocalizerInstanceForNonExistentLocalizerInstance_undefinedLocalizerInstanceIsReturned()
      throws Exception {
    Localizer someOtherLocalizer =
        testableLocalizerFactory.createLocalizer("localizer.name.alpha", Locale.CANADA_FRENCH);
    LocalizerType someOtherLocalizerType = testableLocalizerFactory
        .createLocalizerType(someOtherLocalizer, "other.group", "other.type", "other.method");
    LocalizerInstance someOtherLocalizerInstance =
        testableLocalizerFactory.createLocalizerInstance(someOtherLocalizerType, "other.instance");
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name.beta", Locale.CANADA_FRENCH);

    LocalizerInstance localizerInstance =
        localizer.getLocalizerInstance(someOtherLocalizerInstance.getUid());

    assertNotNull(localizerInstance);
    assertFalse(localizerInstance.isDefined());
  }

  @Test
  public void Localizer_getLocalizerInstanceForExistentLocalizerInstance_requestedLocalizerInstanceIsReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType localizerType = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.method.00");
    LocalizerInstance expectedLocalizerInstance =
        testableLocalizerFactory.createLocalizerInstance(localizerType, "test.instance.00");

    LocalizerInstance localizerInstance =
        localizer.getLocalizerInstance(expectedLocalizerInstance.getUid());

    assertNotNull(localizerInstance);
    assertEquals(expectedLocalizerInstance, localizerInstance);
  }

  @Test
  public void Localizer_addLocalizerInstancesWithSameInstanceNameButDifferntLocalizerTypesAndGetBothLocalizerInstances_bothRequestedLocalizerInstancesAreReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType localizerType00 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.method.00");
    LocalizerInstance localizerInstance00 =
        testableLocalizerFactory.createLocalizerInstance(localizerType00, "test.instance");
    LocalizerType localizerType01 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.method.01");
    LocalizerInstance localizerInstance01 =
        testableLocalizerFactory.createLocalizerInstance(localizerType01, "test.instance");

    for (LocalizerInstance expectedLocalizerInstance : Arrays.asList(localizerInstance00,
        localizerInstance01)) {
      assertNotNull(localizer.getLocalizerInstance(expectedLocalizerInstance.getUid()));
      assertEquals(expectedLocalizerInstance,
          localizer.getLocalizerInstance(expectedLocalizerInstance.getUid()));
    }
  }

  @Test
  public void Localizer_getLocalizerInstanceKeySetWhenLocalizerKeysButNoLocalizerInstances_emptySetIsReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    testableLocalizerFactory.createLocalizerType(localizer, "test.group", "test.type",
        "test.method.00");
    testableLocalizerFactory.createLocalizerType(localizer, "test.group", "test.type",
        "test.method.01");
    testableLocalizerFactory.createLocalizerType(localizer, "test.group", "test.type",
        "test.method.02");

    Set<Uid<LocalizerInstance>> localizerInstanceUidSet = localizer.getLocalizerInstanceUidSet();

    assertNotNull(localizerInstanceUidSet);
    assertTrue(localizerInstanceUidSet.isEmpty());
  }

  @Test
  public void Localizer_getLocalizerInstanceKeySetWhenNoLocalizerKeysAndNoLocalizerInstances_emptySetIsReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    Set<Uid<LocalizerInstance>> localizerInstanceUidSet = localizer.getLocalizerInstanceUidSet();

    assertNotNull(localizerInstanceUidSet);
    assertTrue(localizerInstanceUidSet.isEmpty());
  }

  @Test
  public void Localizer_getLocalizerInstanceKeySetWhenLocalizerInstancesForLocalizerTypesAdded_allLocalizerInstancesForAllLocalizerTypesAreReturned()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);
    LocalizerType localizerType00 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.method.00");
    LocalizerInstance localizerInstance00 =
        testableLocalizerFactory.createLocalizerInstance(localizerType00, "test.instance.00");
    LocalizerInstance localizerInstance01 =
        testableLocalizerFactory.createLocalizerInstance(localizerType00, "test.instance.01");
    LocalizerType localizerType01 = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.method.01");
    LocalizerInstance localizerInstance02 =
        testableLocalizerFactory.createLocalizerInstance(localizerType01, "test.instance.02");

    Set<Uid<LocalizerInstance>> localizerInstanceUidSet = localizer.getLocalizerInstanceUidSet();

    assertNotNull(localizerInstanceUidSet);
    for (Uid<LocalizerInstance> expectedLocalizerInstanceUid : Arrays.asList(
        localizerInstance00.getUid(), localizerInstance01.getUid(), localizerInstance02.getUid())) {
      assertTrue(localizerInstanceUidSet.contains(expectedLocalizerInstanceUid));
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
