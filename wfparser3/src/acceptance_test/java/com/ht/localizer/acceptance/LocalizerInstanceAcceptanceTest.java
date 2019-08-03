package com.ht.localizer.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.ht.localizer.LocalizerInstance;
import com.ht.localizer.LocalizerType;
import com.ht.localizer.StubLocalizerFactory;
import com.ht.localizer.TestableLocalizerFactory;
import com.ht.uid.UID;

import org.junit.Before;
import org.junit.Test;

public class LocalizerInstanceAcceptanceTest {
  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;

  @Before
  public void setup() {
    testableLocalizerFactory = TestableLocalizerFactory.getTestableLocalizerFactory();
    testableLocalizerFactory.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
  }

  @Test
  public void LocalizerInstance_createFactories_factoriesCreated() {
    assertNotNull(testableLocalizerFactory);
    assertNotNull(stubLocalizerFactory);
  }

  @Test
  public void LocalizerInstance_createValidLocalizerInstanceThenGetUid_validLocalizerInstanceUidIsReturned()
      throws Exception {
    final String expectedGroupName = "test.group";
    final String expectedTypeName = "test.type";
    final String expectedMethodName = "test.method";
    final String expectedInstanceName = "valid.instance.name.00";
    final String expectedFullyQualifiedName = String.join(".", expectedGroupName, expectedTypeName,
        expectedMethodName, expectedInstanceName);
    final LocalizerType expectedLocalizerType = stubLocalizerFactory
        .createStubLocalizerType(expectedGroupName, expectedTypeName, expectedMethodName);

    LocalizerInstance localizerInstance = testableLocalizerFactory
        .createLocalizerInstance(expectedLocalizerType, expectedInstanceName);
    assertNotNull(localizerInstance);
    UID<LocalizerInstance> localizerInstanceUid = localizerInstance.getUid();

    assertNotNull(localizerInstanceUid);
    assertEquals(expectedFullyQualifiedName, localizerInstanceUid.getKey());
  }

  @Test
  public void LocalizerInstance_validateEqualsHashCodeAndCompareToMethodsForUid_theirContractsAreRespected()
      throws Exception {
    UID<LocalizerInstance> first;
    UID<LocalizerInstance> second;
    LocalizerType localizerType =
        stubLocalizerFactory.createStubLocalizerType("test.group", "test.type", "test.method");

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
    second =
        testableLocalizerFactory.createLocalizerInstance(localizerType, "test.second").getUid();

    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);

    // Not equal: null
    assertFalse(first.equals(null));
  }
}
