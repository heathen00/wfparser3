package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.NormalVertexReference;
import com.ht.wfp3.api.statement.StatementFactory;

public class NormalVertexReferenceAcceptanceTests {

  private StatementFactory statementFactory;

  private void assertValidNormalVertexReference(Integer vertexIndex, boolean expectedIsSet,
      NormalVertexReference normalVertexReference) {
    assertNotNull(normalVertexReference);
    assertEquals(vertexIndex, normalVertexReference.getVertexIndex());
    assertEquals(expectedIsSet, normalVertexReference.isSet());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void NormalVertexReference_createNormalVertexReferenceWithNullVertexIndex_nullPointerExceptionIsThrown() {
    statementFactory.createNormalVertexReference(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NormalVertexReference_createNormalVertexReferenceWithVertexIndexEqualToZero_illegalArgumentExceptionIsThrown() {
    statementFactory.createNormalVertexReference(Integer.valueOf(0));
  }

  @Test
  public void NormalVertexReference_createNormalVertexReferenceWithVertexIndexGreaterThanZero_normalVertexReferenceIsCreated() {
    Integer vertexIndex = Integer.valueOf(44);
    boolean expectedIsSet = true;

    NormalVertexReference normalVertexReference =
        statementFactory.createNormalVertexReference(vertexIndex);

    assertValidNormalVertexReference(vertexIndex, expectedIsSet, normalVertexReference);
  }

  @Test
  public void NormalVertexReference_createNormalVertexReferenceWithVertexIndexLessThanZero_normalVertexReferenceIsCreated() {
    Integer vertexIndex = Integer.valueOf(-1);
    boolean expectedIsSet = true;

    NormalVertexReference normalVertexReference =
        statementFactory.createNormalVertexReference(vertexIndex);

    assertValidNormalVertexReference(vertexIndex, expectedIsSet, normalVertexReference);
  }

  @Test(expected = NullPointerException.class)
  public void NormalVertexReference_copyNormalVertexReferenceWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyNormalVertexReference(null);
  }

  @Test
  public void NormalVertexReference_copyNormalVertexReference_normalVertexReferenceIsCopied() {
    Integer vertexIndex = Integer.valueOf(32);
    boolean expectedIsSet = true;
    NormalVertexReference originalNormalVertexReference =
        statementFactory.createNormalVertexReference(vertexIndex);

    NormalVertexReference copiedNormalVertexReference =
        statementFactory.copyNormalVertexReference(originalNormalVertexReference);

    assertValidNormalVertexReference(vertexIndex, expectedIsSet, copiedNormalVertexReference);
  }

  @Test
  public void NormalVertexReference_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    NormalVertexReference first;
    NormalVertexReference second;

    first = statementFactory.createNormalVertexReference(Integer.valueOf(3));
    second = statementFactory.createNormalVertexReference(Integer.valueOf(3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // Not equal: different vertex index
    first = statementFactory.createNormalVertexReference(Integer.valueOf(3));
    second = statementFactory.createNormalVertexReference(Integer.valueOf(-3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
