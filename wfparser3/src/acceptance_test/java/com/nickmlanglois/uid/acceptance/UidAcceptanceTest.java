package com.nickmlanglois.uid.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.uid.StubUniqueComponent;
import com.nickmlanglois.uid.TestableUidFactory;
import com.nickmlanglois.uid.Uid;

public class UidAcceptanceTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private TestableUidFactory testableUidFactory;

  @Before
  public void setup() {
    testableUidFactory = TestableUidFactory.getTestableUidFactory();
    testableUidFactory.resetAll();
  }

  @Test
  public void UID_createUidWithNullKeyString_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("key cannot be null");

    testableUidFactory.createUid(null,
        StubUniqueComponent.createStubUniqueComponent("test.unique.component"));
  }

  @Test
  public void UID_createUidWithNullComponent_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("component cannot be null");

    testableUidFactory.createUid("test.uid.key", null);
  }

  @Test
  public void UID_createUidWithEmptyKeyString_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("key cannot be empty");

    testableUidFactory.createUid(" \t\t \n  ",
        StubUniqueComponent.createStubUniqueComponent("test.unique.component"));
  }

  @Test
  public void UID_createUidWithComponentThatDoesNotImplementUniqueComponent_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("component must implement");

    testableUidFactory.createUid("test.uid.key", new Object());
  }

  @Test
  public void UID_createUidWithSameKeyTwiceButDifferentComponent_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("already exists but with different component");

    testableUidFactory.createUid("duplicate.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.unique.component.01"));
    testableUidFactory.createUid("duplicate.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.unique.component.02"));
  }

  @Test
  public void UID_createUidWithValidParameters_expectedUidIsCreated() {
    final String expectedKey = "test.key";
    final StubUniqueComponent expectedComponent =
        StubUniqueComponent.createStubUniqueComponent("test.component.name");

    Uid<StubUniqueComponent> uid = testableUidFactory.createUid(expectedKey, expectedComponent);

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

    Uid<StubUniqueComponent> firstComponentUid =
        testableUidFactory.createUid(expectedDuplicateKey, firstComponent);
    Uid<StubUniqueComponent> secondComponentUid =
        testableUidFactory.createUid(expectedDuplicateKey, secondComponent);

    assertNotNull(firstComponentUid);
    assertNotNull(secondComponentUid);
    assertTrue(firstComponentUid == secondComponentUid);
  }

  @Test
  public void UID_ensureUidRespectsEqualsHashcodeAndCompareToContracts_contractsRespected() {
    Uid<StubUniqueComponent> first = null;
    Uid<StubUniqueComponent> second = null;

    // Equals
    testableUidFactory.resetAll();
    first = testableUidFactory.createUid("test.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));
    second = testableUidFactory.createUid("test.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));

    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertTrue(first.equals(first));
    assertTrue(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) == 0);

    // Not equals.
    testableUidFactory.resetAll();
    first = testableUidFactory.createUid("test.uid.key.00",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));
    second = testableUidFactory.createUid("test.uid.key.01",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));

    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) < 0);

    // Null.
    testableUidFactory.resetAll();
    first = testableUidFactory.createUid("test.uid.key",
        StubUniqueComponent.createStubUniqueComponent("test.component.name"));

    assertFalse(first.equals(null));
  }
}
