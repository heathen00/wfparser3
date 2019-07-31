package com.ht.l10n.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ht.l10n.LocalizerInstance;
import com.ht.l10n.LocalizerType;
import com.ht.l10n.StubLocalizerFactory;
import com.ht.l10n.TestableLocalizerFactory;
import com.ht.uid.UID;

import org.junit.Before;
import org.junit.Test;

public class LocalizerFieldAcceptanceTest {
  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;

  @Before
  public void setup() {
    testableLocalizerFactory = TestableLocalizerFactory.getTestableLocalizerFactory();
    testableLocalizerFactory.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
  }

  @Test
  public void LocalizerField_createFactories_factoriesCreated() {
    assertNotNull(testableLocalizerFactory);
    assertNotNull(stubLocalizerFactory);
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
    final LocalizerType expectedLocalizerType = stubLocalizerFactory
        .createStubLocalizerType(expectedGroupName, expectedTypeName, expectedInstanceName);

    LocalizerInstance localizerField =
        testableLocalizerFactory.createLocalizerInstance(expectedLocalizerType, expectedFieldName);
    assertNotNull(localizerField);
    UID<LocalizerInstance> localizerFieldUid = localizerField.getUid();

    assertNotNull(localizerFieldUid);
    assertEquals(expectedFullyQualifiedName, localizerFieldUid.getKey());
  }

  @Test
  public void LocalizerField_validateEqualsHashCodeAndCompareToMethodsForUid_theirContractsAreRespected()
      throws Exception {
    UID<LocalizerInstance> first;
    UID<LocalizerInstance> second;
    LocalizerType localizerType =
        stubLocalizerFactory.createStubLocalizerType("test.group", "test.type", "test.instance");

    // Equals.
    first = testableLocalizerFactory.createLocalizerInstance(localizerType, "test.same").getUid();
    second = testableLocalizerFactory.createLocalizerInstance(localizerType, "test.same").getUid();

    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertTrue(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) == 0);
    assertTrue(second.compareTo(first) == 0);

    // Not equal: different keys.
    first = testableLocalizerFactory.createLocalizerInstance(localizerType, "test.first").getUid();
    second = testableLocalizerFactory.createLocalizerInstance(localizerType, "test.second").getUid();

    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);

    // Not equal: null
    assertFalse(first.equals(null));
  }
}
