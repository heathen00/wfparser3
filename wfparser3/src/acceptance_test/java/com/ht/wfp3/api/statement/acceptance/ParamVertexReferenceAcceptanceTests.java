package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.ParamVertexReference;
import com.ht.wfp3.api.statement.StatementFactory;

public class ParamVertexReferenceAcceptanceTests {

  private StatementFactory statementFactory;

  private void assertValidParamVertexReference(Integer vertexIndex, boolean expectedIsSet,
      ParamVertexReference paramVertexReference) {
    assertNotNull(paramVertexReference);
    assertEquals(vertexIndex, paramVertexReference.getVertexIndex());
    assertEquals(expectedIsSet, paramVertexReference.isSet());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void ParamVertexReference_createParamVertexReferenceWithNullVertexIndex_nullPointerExceptionIsThrown() {
    statementFactory.createParamVertexReference(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ParamVertexReference_createParamVertexReferenceWithZeroValueVertexIndex_illegalParameterExceptionIsThrown() {
    statementFactory.createParamVertexReference(Integer.valueOf(0));
  }

  @Test
  public void ParamVertexReference_createParamVertexReferenceWithVertexIndexValueGreaterThanZero_paramVertexReferenceIsCreated() {
    Integer vertexIndex = Integer.valueOf(55);
    boolean expectedIsSet = true;

    ParamVertexReference paramVertexReference =
        statementFactory.createParamVertexReference(vertexIndex);

    assertValidParamVertexReference(vertexIndex, expectedIsSet, paramVertexReference);
  }

  @Test
  public void ParamVertexReference_createParamVertexReferenceWithVertexIndexValueLessThanZero_paramVertexReferenceIsCreated() {
    Integer vertexIndex = Integer.valueOf(-101);
    boolean expectedIsSet = true;

    ParamVertexReference paramVertexReference =
        statementFactory.createParamVertexReference(vertexIndex);

    assertValidParamVertexReference(vertexIndex, expectedIsSet, paramVertexReference);
  }

  @Test(expected = NullPointerException.class)
  public void ParamVertexReference_copyParamVertexReferenceWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyParamVertexReference(null);
  }

  @Test
  public void ParamVertexReference_copyParamVertexReference_paramVertexReferenceIsCopied() {
    Integer vertexIndex = Integer.valueOf(55);
    boolean expectedIsSet = true;
    ParamVertexReference originalParamVertexReference =
        statementFactory.createParamVertexReference(vertexIndex);

    ParamVertexReference copiedParamVertexReference =
        statementFactory.copyParamVertexReference(originalParamVertexReference);

    assertValidParamVertexReference(vertexIndex, expectedIsSet, copiedParamVertexReference);
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
