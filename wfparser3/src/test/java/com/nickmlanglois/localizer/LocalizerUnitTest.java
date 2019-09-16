package com.nickmlanglois.localizer;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.localizer.Localizer;
import com.nickmlanglois.localizer.LocalizerFactoryInternal;
import com.nickmlanglois.localizer.LocalizerFactoryInternalImp;
import com.nickmlanglois.localizer.LocalizerInstance;
import com.nickmlanglois.localizer.LocalizerType;
import com.nickmlanglois.uid.UidFactory;
import com.nickmlanglois.wrap.ResourceBundleWrapperConfigurator;
import com.nickmlanglois.wrap.StubWrapperFactory;
import com.nickmlanglois.wrap.WrapperFactory;

public class LocalizerUnitTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private LocalizerFactoryInternal localizerFactoryInternal;
  private StubWrapperFactory stubWrapperFactory;
  private Assert localizerAssert;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForLocaleConfigurator;
  private ResourceBundleWrapperConfigurator resourceBundleWrapperForRootLocaleConfigurator;


  @Before
  public void setUp() throws Exception {
    localizerFactoryInternal = new LocalizerFactoryInternalImp(
        WrapperFactory.createWrapperFactory(), UidFactory.createUidFactory());
    localizerFactoryInternal.resetAll();
    stubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
    resourceBundleWrapperForLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForLocaleConfigurator();
    resourceBundleWrapperForRootLocaleConfigurator =
        stubWrapperFactory.getResourceBundleWrapperForRootLocaleConfigurator();
    localizerFactoryInternal.setWrapperFactory(stubWrapperFactory);
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void Localizer_createTestingAssets_testingAssetsAreCreated() {
    assertNotNull(localizerFactoryInternal);
    assertNotNull(stubWrapperFactory);
    assertNotNull(resourceBundleWrapperForLocaleConfigurator);
    assertNotNull(resourceBundleWrapperForRootLocaleConfigurator);
    assertNotNull(localizerAssert);
  }

  @Test
  public void Localizer_addLocalizerInstanceToUndefinedLocalizerType_unsupportedOperationExceptionIsThrown()
      throws Exception {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot add localizer instance to undefined localizer type");

    LocalizerType localizerType =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerTypeInternal(null);

    localizerFactoryInternal.createLocalizerInstance(localizerType, "does.not.matter");
  }

  @Test
  public void Localizer_getLocalizerTypeFromUndefinedLocalizerUsingAnyLocalizerTypeUid_undefinedLocalizerTypeReturned() {
    final String expectedGroupName = "undef.group";
    final String expectedTypeName = "undef.type";
    final String expectedMethodName = "undef.method";
    final boolean expectedIsDefined = false;
    LocalizerType localizerType =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerType(null);

    localizerAssert.assertExpectedLocalizerType(expectedGroupName, expectedTypeName,
        expectedMethodName, expectedIsDefined, localizerType);
  }

  @Test
  public void Localizer_getLocalizerInstanceFromUndefindLocalizerTypeUsingAnyLocalizerInstanceUid_undefinedLocalizerInstanceReturned() {
    final String expectedInstanceName = "undef.instance";
    final String expectedFullyQualifiedName = "undef.group.undef.type.undef.method.undef.instance";
    final String expectedUidKey = expectedFullyQualifiedName;
    final boolean expectedIsDefined = false;
    final LocalizerType undefinedLocalizerType =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerType(null);

    LocalizerInstance localizerInstance = undefinedLocalizerType.getLocalizerInstance(null);

    localizerAssert.assertExpectedLocalizerInstance(expectedInstanceName,
        expectedFullyQualifiedName, expectedUidKey, expectedIsDefined, localizerInstance);
  }

  @Test
  public void Localizer_addLocalizerTypeToUndefinedLocalizer_unsupportedOperationExceptionIsThrown()
      throws Exception {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot add localizer type to undefined localizer");

    Localizer localizer = localizerFactoryInternal.createUndefinedLocalizer();

    localizerFactoryInternal.createLocalizerType(localizer, "does.not.matter", "does.not.matter",
        "does.not.matter");
  }

  @Test
  public void Localizer_addLocalizerBundleToUndefinedLocalizer_unsupportedOperationExceptionIsThrown()
      throws Exception {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot add localizer bundle to undefined localizer");

    resourceBundleWrapperForLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    resourceBundleWrapperForRootLocaleConfigurator.resetAll().doesResourceBundleExist(true);
    Localizer localizer = localizerFactoryInternal.createUndefinedLocalizer();

    localizerFactoryInternal.createLocalizerBundle(localizer,
        "com.resource.bundle.name.DoesNotMatter");
  }
}
