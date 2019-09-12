package com.ht.event.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public final class AssertNaturalOrder {
  public enum Relation {
    EQ {

      @SuppressWarnings("unchecked")
      @Override
      <T> void assertExpectedRelation(NaturalOrder<T> leftOperand, NaturalOrder<T> rightOperand) {
        assertValidOperand(leftOperand);
        assertValidOperand(rightOperand);
        assertCommonBehaviours(leftOperand);
        assertCommonBehaviours(rightOperand);
        assertTrue(leftOperand.equals(rightOperand));
        assertTrue(rightOperand.equals(leftOperand));
        assertTrue(leftOperand.hashCode() == rightOperand.hashCode());
        assertTrue(leftOperand.compareTo((T) rightOperand) == 0);
        assertTrue(rightOperand.compareTo((T) leftOperand) == 0);
      }
    },
    LT {

      @SuppressWarnings("unchecked")
      @Override
      <T> void assertExpectedRelation(NaturalOrder<T> leftOperand, NaturalOrder<T> rightOperand) {
        assertValidOperand(leftOperand);
        assertValidOperand(rightOperand);
        assertCommonBehaviours(leftOperand);
        assertCommonBehaviours(rightOperand);
        assertFalse(leftOperand.equals(rightOperand));
        assertFalse(rightOperand.equals(leftOperand));
        assertFalse(leftOperand.hashCode() == rightOperand.hashCode());
        assertTrue(leftOperand.compareTo((T) rightOperand) < 0);
        assertTrue(rightOperand.compareTo((T) leftOperand) > 0);
      }
    },
    GT {
      @SuppressWarnings("unchecked")
      @Override
      <T> void assertExpectedRelation(NaturalOrder<T> leftOperand, NaturalOrder<T> rightOperand) {
        assertValidOperand(leftOperand);
        assertValidOperand(rightOperand);
        assertCommonBehaviours(leftOperand);
        assertCommonBehaviours(rightOperand);
        assertFalse(leftOperand.equals(rightOperand));
        assertFalse(rightOperand.equals(leftOperand));
        assertFalse(leftOperand.hashCode() == rightOperand.hashCode());
        assertTrue(leftOperand.compareTo((T) rightOperand) > 0);
        assertTrue(rightOperand.compareTo((T) leftOperand) < 0);
      }
    };

    abstract <T> void assertExpectedRelation(NaturalOrder<T> leftOperand,
        NaturalOrder<T> rightOperand);

    <T> void assertValidOperand(NaturalOrder<T> operand) {
      assertNotNull(operand);
    }

    <T> void assertCommonBehaviours(NaturalOrder<T> operand) {
      assertFalse(operand.equals(null));
      try {
        operand.compareTo(null);
      } catch (NullPointerException npe) {
      } catch (Exception e) {
        fail("operand.compareTo(null) did not throw NullPointerException");
      }
    }
  }

  public static AssertNaturalOrder createAssertNaturalOrder() {
    return new AssertNaturalOrder();
  }

  public <T> void assertExpectedRelation(NaturalOrder<T> leftOperand, Relation relation,
      NaturalOrder<T> rightOperand) {
    assertNotNull(leftOperand);
    assertNotNull(rightOperand);
    relation.assertExpectedRelation(leftOperand, rightOperand);
  }
}
