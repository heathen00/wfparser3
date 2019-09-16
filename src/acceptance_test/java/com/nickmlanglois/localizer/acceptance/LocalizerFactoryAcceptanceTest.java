package com.nickmlanglois.localizer.acceptance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import com.nickmlanglois.localizer.Assert;
import com.nickmlanglois.localizer.Localizer;
import com.nickmlanglois.localizer.LocalizerBundle;
import com.nickmlanglois.localizer.LocalizerException;
import com.nickmlanglois.localizer.LocalizerInstance;
import com.nickmlanglois.localizer.LocalizerType;
import com.nickmlanglois.localizer.StubLocalizerFactory;
import com.nickmlanglois.localizer.TestableLocalizerFactory;
import com.nickmlanglois.uid.StubUidFactory;
import com.nickmlanglois.uid.Uid;
import com.nickmlanglois.uid.UidFactory;
import com.nickmlanglois.wrap.ResourceBundleWrapperConfigurator;
import com.nickmlanglois.wrap.StubWrapperFactory;

public class LocalizerFactoryAcceptanceTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private Localizer stubLocalizer;
  private LocalizerType stubLocalizerType;
  private StubWrapperFactory stubWrapperFactory;
  private StubUidFactory stubUidFactory;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;
  private Assert localizerAssert;

  @Before
  public void setup() throws Exception {
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    stubLocalizer = stubLocalizerFactory.createLocalizer("stub.name", Locale.CANADA_FRENCH);
    stubLocalizerType = stubLocalizerFactory.createLocalizerType(stubLocalizer, "stub.group",
        "stub.type", "stub.method.name");

    stubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();

    stubUidFactory = StubUidFactory.createStubUidFactory();

    testableLocalizerFactory =
        TestableLocalizerFactory.getTestableLocalizerFactory(stubWrapperFactory, stubUidFactory);
    resourceBundleWrapperForLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForLocaleConfigurator();
    resourceBundleWrapperForRootLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForRootLocaleConfigurator();
    testableLocalizerFactory.resetAll();

    localizerAssert = Assert.createAssert();
  }

  @Test
  public void LocalizerFactory_createTestingAssets_testingAssetsCreated() {
    assertNotNull(stubLocalizerFactory);
    assertNotNull(stubLocalizer);
    assertNotNull(stubLocalizerType);
    assertNotNull(stubWrapperFactory);
    assertNotNull(stubUidFactory);
    assertNotNull(resourceBundleWrapperForLocaleConfigurator);
    assertNotNull(resourceBundleWrapperForRootLocaleConfigurator);
    assertNotNull(testableLocalizerFactory);

    assertNotNull(localizerAssert);


  }

  @Test
  public void LocalizerFactory_createLocalizerWithNullName_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("name cannot be null");

    testableLocalizerFactory.createLocalizer(null, Locale.CANADA_FRENCH);
  }

  @Test
  public void LocalizerFactory_createLocalizerWithEmptyName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("name can only contain the characters: ");

    testableLocalizerFactory.createLocalizer("  /t", Locale.CANADA_FRENCH);
  }

  @Test
  public void LocalizerFactory_createLocalizerWithInvalidCharactersInName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("name can only contain the characters: ");

    testableLocalizerFactory.createLocalizer("This name__Is.inValid!\n", Locale.CANADA_FRENCH);
  }

  @Test
  public void LocalizerFactory_createLocalizerWithNullLocale_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("locale cannot be null");

    testableLocalizerFactory.createLocalizer("not.important", null);
  }

  @Test
  public void LocalizerFactory_createLocalizerWithLocale_localizeIsCreated() throws Exception {
    String expectedName = "localizer.name";
    Locale expectedLocale = Locale.CANADA_FRENCH;
    Uid<Localizer> expectedLocalizerUid =
        UidFactory.createUidFactory().createUid(expectedName, stubLocalizer);
    boolean expectedIsDefined = true;

    Localizer localizer = testableLocalizerFactory.createLocalizer(expectedName, expectedLocale);

    localizerAssert.assertExpectedLocalizer(expectedName, expectedLocalizerUid, expectedLocale,
        expectedIsDefined, localizer);
  }

  @Test
  public void LocalizerFactory_createCompositeLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("localizer cannot be null");

    testableLocalizerFactory.createLocalizerBundle(null, "does.not.matter");
  }

  @Test
  public void LocalizerFactory_createCompositeLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("resourceBundleName cannot be null");

    testableLocalizerFactory.createLocalizerBundle(
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH), null);
  }

  @Test
  public void LocalizerFactory_createCompositeLocalizerBundleWithValidParameters_localizerBundleCreatedAsSpecified()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.nickmlanglois.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final boolean expectedIsDefined = true;
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", expectedTargetLocale);

    LocalizerBundle localizerBundle =
        testableLocalizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizer, localizerBundle);
  }

  @Test
  public void LocalizerFactory_createCompositeLocalizerButRootLocaleResourceBundleDoesNotExist_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("resource bundle does not exist");

    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(false);
    final String expectedResourceBundleName =
        "com.nickmlanglois.l10n.test.resource.TestL10ResourceBundleForLocaleExistsButRootLocaleDoesNot";
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("localizer.name", Locale.CANADA_FRENCH);

    testableLocalizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWithNullLocalizerType_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("localizerType cannot be null");

    testableLocalizerFactory.createLocalizerInstance(null, "test.instance");
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWithNullInstanceName_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("instanceName cannot be null");

    testableLocalizerFactory.createLocalizerInstance(stubLocalizerType, null);
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWithEmptyInstanceName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("instanceName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerInstance(stubLocalizerType, "");
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWithUnsupportedCharactersInInstanceName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("instanceName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerInstance(stubLocalizerType,
        "SOME UNSUPPORTED\tCHARACTERS\n");
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWithInstanceNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("instanceName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerInstance(stubLocalizerType,
        ".invalid.period.at.begging");
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWithInstanceNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("instanceName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerInstance(stubLocalizerType, "invalid.period.at.end.");
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWithSupportedInstanceName_localizerInstanceIsCreated()
      throws Exception {
    final String expectedGroupName = "test.group";
    final String expectedTypeName = "test.type";
    final String expectedMethodName = "test.method";
    final String expectedInstanceName = "valid.instance.name.00";
    final String expectedFullyQualifiedName = String.join(".", expectedGroupName, expectedTypeName,
        expectedMethodName, expectedInstanceName);
    final String expectedUidKey = expectedFullyQualifiedName;
    final LocalizerType expectedLocalizerType = stubLocalizerFactory
        .createLocalizerType(stubLocalizer, "test.group", "test.type", "test.method");
    final boolean expectedIsDefined = true;

    LocalizerInstance localizerInstance = testableLocalizerFactory
        .createLocalizerInstance(expectedLocalizerType, expectedInstanceName);

    localizerAssert.assertExpectedLocalizerInstance(expectedInstanceName,
        expectedFullyQualifiedName, expectedUidKey, expectedIsDefined, localizerInstance);
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("localizer cannot be null");

    testableLocalizerFactory.createLocalizerType(null, "test.group", "test.type", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithNullGroupName_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("groupName cannot be null");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, null, "test.type", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithEmptyGroupName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("groupName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "", "test.type", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInGroupName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("groupName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "unsupported\ncharacters",
        "test.type", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithGroupNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("groupName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, ".invalid.starts.with.period",
        "test.type", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithGroupNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("groupName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "invalid.ends.with.period.",
        "test.type", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithNullTypeName_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("typeName cannot be null");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group", null, "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithEmptyTypeName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("typeName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group", "", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInTypeName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("typeName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group",
        "unsupported characters", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithTypeNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("typeName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group",
        ".invalid.starts.with.period", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithTypeNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("typeName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group",
        "invalid.ends.with.period.", "test.method");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithNullMethodName_nullPointerExceptionIsThrown()
      throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("methodName cannot be null");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group", "test.type", null);
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithEmptyMethodName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("methodName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group", "test.type", "");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithUnsupportedCharactersInMethodName_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("methodName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group", "test.type",
        "unsupported!characters");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithMethodNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("methodName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group", "test.type",
        ".invalid.starts.with.period");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithMethodNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("methodName can only contain the characters: ");

    testableLocalizerFactory.createLocalizerType(stubLocalizer, "test.group", "test.type",
        "invalid.ends.with.period.");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWithValidParameters_localizerTypeIsCreated()
      throws Exception {
    final String expectedGroupName = "expected.group.name";
    final String expectedTypeName = "expected.type.name";
    final String expectedMethodName = "expected.method.name";
    final Set<Uid<LocalizerInstance>> expectedLocalizerInstanceKeySet = Collections.emptySet();
    final Localizer expectedLocalizer = stubLocalizer;
    final Uid<LocalizerType> expectedLocalizerTypeUid = UidFactory.createUidFactory().createUid(
        String.join(".", expectedGroupName, expectedTypeName, expectedMethodName),
        stubLocalizerFactory.createLocalizerType(stubLocalizer, expectedGroupName, expectedTypeName,
            expectedMethodName));
    final boolean expectedIsDefined = true;

    LocalizerType localizerType = testableLocalizerFactory.createLocalizerType(expectedLocalizer,
        expectedGroupName, expectedTypeName, expectedMethodName);

    localizerAssert.assertExpectedLocalizerType(expectedGroupName, expectedTypeName,
        expectedMethodName, expectedLocalizerInstanceKeySet, expectedLocalizer,
        expectedLocalizerTypeUid, expectedIsDefined, localizerType);
  }

  @Test
  public void LocalizerFactory_createLocalizerInstanceWhereLocalizerTypeParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("unknown LocalizerType implementation");

    LocalizerType externalLocalizerType = Mockito.mock(LocalizerType.class);
    testableLocalizerFactory.createLocalizerInstance(externalLocalizerType, "some.instance");
  }

  @Test
  public void LocalizerFactory_createLocalizerBundleWhereLocalizerParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("unknown Localizer implementation");

    Localizer externalLocalizer = new Localizer() {

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public Uid<Localizer> getUid() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public void setLocale(Locale locale) throws LocalizerException {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public Set<Uid<LocalizerType>> getLocalizerTypeUidSet() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public LocalizerType getLocalizerType(Uid<LocalizerType> typeUid) {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid) {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public SortedSet<LocalizerBundle> getLocalizerBundleSet() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public Locale getLocale() {
        throw new UnsupportedOperationException("method not supported by stub");
      }
    };

    testableLocalizerFactory.createLocalizerBundle(externalLocalizer, "com.does.not.Matter");
  }

  @Test
  public void LocalizerFactory_createLocalizerTypeWhereLocalizerTypeParameterIsUnknownExternalImplementation_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("unknown Localizer implementation");

    Localizer externalLocalizer = Mockito.mock(Localizer.class);
    testableLocalizerFactory.createLocalizerType(externalLocalizer, "some.group", "some.type",
        "some.method");
  }

  @Test
  public void LocalizerFactory_createTheSameLocalizerTwice_bothLocalizersAreTheSameInstance()
      throws Exception {
    final String expectedLocalizerName = "same.localizer.twice";
    final Locale expectedLocalizerLocale = Locale.CANADA_FRENCH;

    Localizer firstLocalizer =
        testableLocalizerFactory.createLocalizer(expectedLocalizerName, expectedLocalizerLocale);
    Localizer secondLocalizer =
        testableLocalizerFactory.createLocalizer(expectedLocalizerName, expectedLocalizerLocale);

    assertTrue(firstLocalizer == secondLocalizer);
  }

  @Test
  public void LocalizerFactory_createTheSameLocalizerTwiceButWithDifferentLocales_localizerExceptionIsThrown()
      throws Exception {
    thrown.expect(LocalizerException.class);
    thrown.expectMessage("attempt to create existing Localizer but with different Locale");

    final String expectedLocalizerName = "same.localizer.name";
    final Locale expectedFirstLocale = Locale.GERMAN;
    final Locale expectedSecondLocale = Locale.ITALIAN;
    testableLocalizerFactory.createLocalizer(expectedLocalizerName, expectedFirstLocale);

    testableLocalizerFactory.createLocalizer(expectedLocalizerName, expectedSecondLocale);
  }

  @Test
  public void LocalizerFactory_createTheSameLocalizerTypeTwice_bothLocalizerTypesAreTheSameInstance()
      throws Exception {
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("test.localizer", Locale.CANADA_FRENCH);

    final String expectedGroupName = "same.group.name";
    final String expectedTypeName = "same.type.name";
    final String expectedMethodName = "same.method.name";

    LocalizerType firstLocalizerType = testableLocalizerFactory.createLocalizerType(localizer,
        expectedGroupName, expectedTypeName, expectedMethodName);
    LocalizerType secondLocalizerType = testableLocalizerFactory.createLocalizerType(localizer,
        expectedGroupName, expectedTypeName, expectedMethodName);

    assertTrue(firstLocalizerType == secondLocalizerType);
  }

  @Test
  public void LocalizerFactory_createTheSameLocalizerInstanceTwice_bothLocalizerInstancesAreTheSameInstance()
      throws Exception {
    final Localizer localizer =
        testableLocalizerFactory.createLocalizer("test.localizer", Locale.CANADA_FRENCH);
    final LocalizerType localizerType = testableLocalizerFactory.createLocalizerType(localizer,
        "test.group", "test.type", "test.method");
    final String expectedInstanceName = "same.instance";

    LocalizerInstance firstLocalizerInstance =
        testableLocalizerFactory.createLocalizerInstance(localizerType, expectedInstanceName);
    LocalizerInstance secondLocalizerInstance =
        testableLocalizerFactory.createLocalizerInstance(localizerType, expectedInstanceName);

    assertTrue(firstLocalizerInstance == secondLocalizerInstance);
  }

  @Test
  public void LocalizerFactory_createTheSameLocalizerBundleForSameLocalizerTwice_bothLocalizerBundlesAreTheSameInstance()
      throws Exception {
    resourceBundleWrapperForLocaleConfigurator.doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.doesResourceBundleExist(true);
    Localizer localizer =
        testableLocalizerFactory.createLocalizer("same.localizer", Locale.CANADA_FRENCH);
    final String expectedResourceBundleBaseName = "some.test.ResourceBundle";

    LocalizerBundle firstLocalizerBundle =
        testableLocalizerFactory.createLocalizerBundle(localizer, expectedResourceBundleBaseName);
    LocalizerBundle secondLocalizerBundle =
        testableLocalizerFactory.createLocalizerBundle(localizer, expectedResourceBundleBaseName);

    assertTrue(firstLocalizerBundle == secondLocalizerBundle);
  }
}
