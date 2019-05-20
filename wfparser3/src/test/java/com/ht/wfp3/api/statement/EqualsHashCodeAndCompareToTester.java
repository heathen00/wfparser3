package com.ht.wfp3.api.statement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualsHashCodeAndCompareToTester {
  private static final EqualsHashCodeAndCompareToTester EQUALS_HASHCODE_AND_COMPARETO_TESTER_SINGLETON =
      new EqualsHashCodeAndCompareToTester();

  public static EqualsHashCodeAndCompareToTester createEqualsHashCodeAndCompareToTester() {
    return EQUALS_HASHCODE_AND_COMPARETO_TESTER_SINGLETON;
  }

  private EqualsHashCodeAndCompareToTester() {}

  public <T extends Comparable<T>> void assertContractRespectedWhenEqual(T first, T second) {
    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertTrue(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) == 0);
    assertTrue(second.compareTo(first) == 0);
  }

  public <T extends Comparable<T>> void assertContractRespectedWhenNotEqual(
      boolean firstSortsBeforeSecond, T first, T second) {
    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    if (firstSortsBeforeSecond) {
      assertTrue(first.compareTo(second) < 0);
      assertTrue(second.compareTo(first) > 0);
    } else {
      assertTrue(first.compareTo(second) > 0);
      assertTrue(second.compareTo(first) < 0);
    }
  }

  public <T extends Comparable<T>> void assertEqualsSelf(T first) {
    assertTrue(first.equals(first));
    assertTrue(first.compareTo(first) == 0);
  }

  public <T extends Comparable<T>> void assertDoesNotEqualNull(T first) {
    assertFalse(first.equals(null));
  }
}
