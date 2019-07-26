package com.ht.l10n;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class LocalizerUnitTest {
  private LocalizerFactoryInternal localizerFactoryInternal;
  private Assert localizerAssert;


  @Before
  public void setUp() throws Exception {
    localizerFactoryInternal = SystemInternal.getSystemInternal().getFactoryInternal();
    localizerFactoryInternal.resetAll();
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void Localizer_createFactories_factoriesAreCreated() {
    assertNotNull(localizerFactoryInternal);
    assertNotNull(localizerAssert);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Localizer_addLocalizerFieldToUndefinedLocalizerType_unsupportedOperationExceptionIsThrown()
      throws Exception {
    LocalizerType localizerType =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerTypeInternal(null);

    localizerFactoryInternal.createLocalizerField(localizerType, "does.not.matter");
  }

  @Test
  public void Localizer_getLocalizerTypeFromUndefinedLocalizerUsingAnyLocalizerTypeUid_undefinedLocalizerTypeReturned() {
    final String expectedGroupName = "undef.group";
    final String expectedTypeName = "undef.type";
    final String expectedInstanceName = "undef.instance";
    final boolean expectedIsDefined = false;
    LocalizerType localizerType =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerType(null);

    localizerAssert.assertExpectedLocalizerType(expectedGroupName, expectedTypeName,
        expectedInstanceName, expectedIsDefined, localizerType);
  }

  @Test
  public void Localizer_getLocalizerFieldFromUndefindLocalizerTypeUsingAnyLocalizerFieldUid_undefinedLocalizerFieldReturned() {
    final String expectedFieldName = "undef.field";
    final String expectedFullyQualifiedName = "undef.group.undef.type.undef.instance.undef.field";
    final String expectedUidKey = expectedFullyQualifiedName;
    final boolean expectedIsDefined = false;
    final LocalizerType undefinedLocalizerType =
        localizerFactoryInternal.createUndefinedLocalizer().getLocalizerType(null);

    LocalizerField localizerField = undefinedLocalizerType.getLocalizerField(null);

    localizerAssert.assertExpectedLocalizerField(expectedFieldName, expectedFullyQualifiedName,
        expectedUidKey, expectedIsDefined, localizerField);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Localizer_addLocalizerTypeToUndefinedLocalizer_unsupportedOperationExceptionIsThrown()
      throws Exception {
    Localizer localizer = localizerFactoryInternal.createUndefinedLocalizer();

    localizerFactoryInternal.createLocalizerType(localizer, "does.not.matter", "does.not.matter",
        "does.not.matter");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Localizer_addLocalizerBundleToUndefinedLocalizer_unsupportedOperationExceptionIsThrown()
      throws Exception {
    Localizer localizer = localizerFactoryInternal.createUndefinedLocalizer();

    localizerFactoryInternal.createLocalizerBundle(localizer,
        "com.ht.l10n.test.resource.TestL10nRootLocaleResourceBundle");
  }
}
