package com.ht.uid.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import com.ht.uid.StubUniqueComponent;
import com.ht.uid.UID;

public class UidAcceptanceTest {

  @Before
  public void setup() {
    UID.resetUidFactory();
  }

  @Test(expected = NullPointerException.class)
  public void UID_createUidWithNullKeyString_nullPointerExceptionIsThrown() {
    UID.createUid(null, StubUniqueComponent.createStubUniqueComponent("test.unique.component"));
  }

  @Test(expected = NullPointerException.class)
  public void UID_createUidWithNullComponent_nullPointerExceptionIsThrown() {
    UID.createUid("test.uid.key", null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void UID_createUidWithEmptyKeyString_unsupportedOperationExceptionIsThrown() {
    UID.createUid(" \t\t \n  ",
        StubUniqueComponent.createStubUniqueComponent("test.unique.component"));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void UID_createUidWithComponentThatDoesNotImplementUniqueComponent_unsupportedOperationExceptionIsThrown() {
    UID.createUid("test.uid.key", new Object());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void UID_createUidWithSameKeyTwiceButDifferentComponent_unsupportedOperationExceptionIsThrown() {
    UID.createUid("duplicate.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.unique.component.01"));
    UID.createUid("duplicate.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.unique.component.02"));
  }

  @Test
  public void UID_createUidWithValidParameters_expectedUidIsCreated() {
    final String expectedKey = "test.key";
    final StubUniqueComponent expectedComponent =
        StubUniqueComponent.createStubUniqueComponent("test.component.name");

    UID<StubUniqueComponent> uid = UID.createUid(expectedKey, expectedComponent);

    assertNotNull(uid);
    assertEquals(expectedKey, uid.getKey());
  }

  @Test
  public void UID_createTheSameUidTwice_bothUidReferencesReferenceTheSameInstance() {
    final String expectedDuplicateKey = "duplicate.uid.key";
    final String duplicateComponentName = "duplicate.component.name";
    final StubUniqueComponent firstComponent =
        StubUniqueComponent.createStubUniqueComponent(duplicateComponentName);
    final StubUniqueComponent secondComponent =
        StubUniqueComponent.createStubUniqueComponent(duplicateComponentName);

    UID<StubUniqueComponent> firstComponentUid =
        UID.createUid(expectedDuplicateKey, firstComponent);
    UID<StubUniqueComponent> secondComponentUid =
        UID.createUid(expectedDuplicateKey, secondComponent);

    assertNotNull(firstComponentUid);
    assertNotNull(secondComponentUid);
    assertTrue(firstComponentUid == secondComponentUid);
  }

  @Test
  public void UID_ensureUidRespectsEqualsHashcodeAndCompareToContracts_contractsRespected() {
    UID<StubUniqueComponent> first = null;
    UID<StubUniqueComponent> second = null;

    // Equals
    UID.resetUidFactory();
    first = UID.createUid("test.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));
    second = UID.createUid("test.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));

    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertTrue(first.equals(first));
    assertTrue(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) == 0);

    // Not equals.
    UID.resetUidFactory();
    first = UID.createUid("test.uid.key.00",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));
    second = UID.createUid("test.uid.key.01",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));

    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) < 0);

    // Null.
    UID.resetUidFactory();
    first = UID.createUid("test.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));

    assertFalse(first.equals(null));
  }
}
