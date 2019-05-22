package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.TexVertexReference;

public class TexVertexReferenceAcceptanceTests {

  private StatementFactory statementFactory;

  private void assertValidTexVertexReference(Integer vertexIndex,
      TexVertexReference texVertexReference) {
    assertNotNull(texVertexReference);
    assertEquals(vertexIndex, texVertexReference.getVertexIndex());
    assertTrue(texVertexReference.isSet());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void TexVertexReference_createTexVertexReferenceWithNullVertexIndex_nullPointerExceptionIsThrown() {
    statementFactory.createTexVertexReference(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TexVertexReference_createTexVertexReferenceWithVertexIndexEqualToZero_invalidArgumentExceptionIsThrown() {
    statementFactory.createTexVertexReference(Integer.valueOf(0));
  }

  @Test
  public void TexVertexReference_createTexVertexReferenceWithVertexIndexLessThanZero_texVertexReferenceIsCreated() {
    Integer vertexIndex = Integer.valueOf(-1234);

    TexVertexReference texVertexReference = statementFactory.createTexVertexReference(vertexIndex);

    assertValidTexVertexReference(vertexIndex, texVertexReference);
  }

  @Test
  public void TexVertexReference_createTexVertexReferenceWithVertexIndexMoreThanZero_texVertexReferenceIsCreated() {
    Integer vertexIndex = Integer.valueOf(5);

    TexVertexReference texVertexReference = statementFactory.createTexVertexReference(vertexIndex);

    assertValidTexVertexReference(vertexIndex, texVertexReference);
  }

  @Test(expected = NullPointerException.class)
  public void TexVertexReference_copyTexVertexReferenceWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyTexVertexReference(null);
  }

  @Test
  public void TexVertexReference_copyTexVertexReference_texVertexReferenceIsCopied() {
    Integer vertexIndex = Integer.valueOf(35);
    TexVertexReference originalTexVertexReference =
        statementFactory.createTexVertexReference(vertexIndex);

    TexVertexReference copiedTexVertexReference =
        statementFactory.copyTexVertexReference(originalTexVertexReference);

    assertValidTexVertexReference(vertexIndex, copiedTexVertexReference);
  }

  @Test
  public void TexVertexReference_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    TexVertexReference first;
    TexVertexReference second;

    first = statementFactory.createTexVertexReference(Integer.valueOf(4567));
    second = statementFactory.createTexVertexReference(Integer.valueOf(4567));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different TexVertex index number
    first = statementFactory.createTexVertexReference(Integer.valueOf(4));
    second = statementFactory.createTexVertexReference(Integer.valueOf(4567));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
