package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ht.l10n.Factory;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.LocalizerType;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class LocalizerTypeTest {

  private Factory localizerFactory;
  private StubFactory stubFactory;

  @Before
  public void setup() {
    localizerFactory = Factory.createFactory();
    stubFactory = StubFactory.createStubFactory();
  }

  @Test
  public void LocalizerType_createFactories_factoriesCreated() {
    assertNotNull(localizerFactory);
    assertNotNull(stubFactory);
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerType_getLocalizerFieldWithNullUid_nullPointerExceptionThrown()
      throws Exception {
    LocalizerType localizerType = localizerFactory.createLocalizerType(
        stubFactory.createDefaultStubLocalizer(), "test.group", "test.type", "test.instance");

    localizerType.getLocalizerField(null);
  }

  @Test
  public void LocalizerType_getLocalizerFieldWhenLocalizerTypeDoesNotHaveLocalizerFieldForUid_undefinedLocalizerFieldReturned()
      throws Exception {
    String expectedLocalizerTypeGroup = "undef.group";
    String expectedLocalizerTypeType = "undef.type";
    String expectedLocalizerTypeInstance = "undef.instance";
    String expectedLocalizerFieldName = "undef.field";
    String expectedLocalizerFieldFullyQualifiedName = String.join(".", expectedLocalizerTypeGroup,
        expectedLocalizerTypeType, expectedLocalizerTypeInstance, expectedLocalizerFieldName);
    String expectedLocalizerFieldKey = expectedLocalizerFieldFullyQualifiedName;
    String expectedLocalizerTypeKey = String.join(".", expectedLocalizerTypeGroup,
        expectedLocalizerTypeType, expectedLocalizerTypeInstance);
    LocalizerType otherLocalizerType = localizerFactory.createLocalizerType(
        stubFactory.createDefaultStubLocalizer(), "other.group", "other.name", "other.instance");
    LocalizerField otherLocalizerField =
        localizerFactory.createLocalizerField(otherLocalizerType, "field.name");
    LocalizerType localizerType = localizerFactory.createLocalizerType(
        stubFactory.createDefaultStubLocalizer(), "test.group", "test.type", "test.instance");

    LocalizerField localizerField = localizerType.getLocalizerField(otherLocalizerField.getUid());

    assertNotNull(localizerField);
    assertEquals(expectedLocalizerFieldName, localizerField.getFieldName());
    assertEquals(expectedLocalizerFieldFullyQualifiedName, localizerField.getFullyQualifiedName());
    assertNotNull(localizerField.getUid());
    assertEquals(expectedLocalizerFieldKey, localizerField.getUid().getKey());
    assertNotNull(localizerField.getLocalizerType());
    assertEquals(expectedLocalizerTypeGroup, localizerField.getLocalizerType().getGroupName());
    assertEquals(expectedLocalizerTypeType, localizerField.getLocalizerType().getTypeName());
    assertEquals(expectedLocalizerTypeInstance,
        localizerField.getLocalizerType().getInstanceName());
    assertNotNull(localizerField.getLocalizerType().getUid());
    assertEquals(expectedLocalizerTypeKey, localizerField.getLocalizerType().getUid().getKey());

  }

  @Test
  @Ignore("Not implemented yet")
  public void LocalizerType_getLocalizerFieldForValidLocalizerFieldUid_specifiedLocalizerFieldIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void LocalizerType_getLocalizerFieldUidSetWhenSetIsEmpty_emptySetIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void LocalizerType_getLocalizerFieldUidSetWhenItContainsOneUid_setWithOneUidReturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void LocalizerType_getLocalizerFieldUidSetWhenItContainsMultipleUids_setWithMultipleUidsreturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void LocalizerType_getLocalizerTypeUid_localizerTypeUidIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void LocalizerType_assertLocalizerTypeUidAssertEqualsAndCompareToMethodsRespectContract_methodsContractsRespected() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void LocalizerType_addDuplicateLocalizerField_noErrorButDuplicateFieldNotAdded() {
    fail("Not yet implemented");
  }
}
