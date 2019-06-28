package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import com.ht.common.UID;
import com.ht.l10n.Factory;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.LocalizerType;
import com.ht.l10n.StubFactory;

public class LocalizerFieldAcceptanceTest {
  private Factory localizerFactory;
  private StubFactory stubFactory;

  @Before
  public void setup() {
    localizerFactory = Factory.createFactory();
    stubFactory = StubFactory.createStubFactory();
  }

  @Test
  public void LocalizerField_createFactories_factoriesCreated() {
    assertNotNull(localizerFactory);
    assertNotNull(stubFactory);
  }

  @Test
  public void LocalizerField_createValidLocalizerFieldThenGetUid_validLocalizerFieldUidIsReturned()
      throws Exception {
    final String expectedGroupName = "test.group";
    final String expectedTypeName = "test.type";
    final String expectedInstanceName = "test.instance";
    final String expectedFieldName = "valid.field.name.00";
    final String expectedFullyQualifiedName = String.join(".", expectedGroupName, expectedTypeName,
        expectedInstanceName, expectedFieldName);
    final LocalizerType expectedLocalizerType = stubFactory
        .createStubLocalizerType(expectedGroupName, expectedTypeName, expectedInstanceName);

    LocalizerField localizerField =
        localizerFactory.createLocalizerField(expectedLocalizerType, expectedFieldName);
    assertNotNull(localizerField);
    UID<LocalizerField> localizerFieldUid = localizerField.getUid();

    assertNotNull(localizerFieldUid);
    assertEquals(expectedFullyQualifiedName, localizerFieldUid.getKey());
  }

  @Test
  public void LocalizerField_validateEqualsHashCodeAndCompareToMethodsForUid_theirContractsAreRespected()
      throws Exception {
    UID<LocalizerField> first;
    UID<LocalizerField> second;
    LocalizerType localizerType =
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance");

    // Equals.
    first = localizerFactory.createLocalizerField(localizerType, "test.same").getUid();
    second = localizerFactory.createLocalizerField(localizerType, "test.same").getUid();

    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertTrue(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) == 0);
    assertTrue(second.compareTo(first) == 0);

    // Not equal: different keys.
    first = localizerFactory.createLocalizerField(localizerType, "test.first").getUid();
    second = localizerFactory.createLocalizerField(localizerType, "test.second").getUid();

    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);

    // Not equal: null
    assertFalse(first.equals(null));
  }
}
