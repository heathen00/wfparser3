package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.TexVertexReference;

public class TexVertexReferenceAcceptanceTests {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void TexVertexReference_createTexVertexReferenceWithNullVertexIndex_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("vertexIndex cannot be null");

    statementFactory.createTexVertexReference(null);
  }

  @Test
  public void TexVertexReference_createTexVertexReferenceWithVertexIndexEqualToZero_invalidArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexIndex cannot equal 0");

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

  @Test
  public void TexVertexReference_copyTexVertexReferenceWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("texVertexReference cannot be null");

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
