package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.ParamVertexReference;
import com.ht.wfp3.api.statement.SpecialPoint;
import com.ht.wfp3.api.statement.StatementFactory;

public class SpecialPointAcceptanceTests {

  private static final String SP_KEYWORD = "sp";
  private StatementFactory statementFactory;

  private ParamVertexReference createParamVertexReference(int vertexIndex) {
    return statementFactory.createParamVertexReference(Integer.valueOf(vertexIndex));
  }

  private void assertValidSpecialPoint(List<ParamVertexReference> vertexReferenceList,
      SpecialPoint specialPoint) {
    assertNotNull(specialPoint);
    assertEquals(SP_KEYWORD, specialPoint.getKeyword());
    assertEquals(vertexReferenceList, specialPoint.getVertexReferenceList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void SpecialPoint_createSpecialPointWithNullVertexReferenceList_nullPointerExceptionIsThrown() {
    statementFactory.createSpecialPoint(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void SpecialPoint_createSpecialPointWithVertexReferenceListContainingLessThanTheMinimumMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createSpecialPoint(Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void SpecialPoint_createSpecialPointWithVertexReferenceListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createSpecialPoint(
        Arrays.asList(createParamVertexReference(1), null, createParamVertexReference(2)));
  }

  @Test
  public void SpecialPoint_createSpecialPointWithVertexReferenceListContainingTheMinimumNumberOfMembers_specialPointIsCreated() {
    List<ParamVertexReference> vertexReferenceList = Arrays.asList(createParamVertexReference(1));

    SpecialPoint specialPoint = statementFactory.createSpecialPoint(vertexReferenceList);

    assertValidSpecialPoint(vertexReferenceList, specialPoint);
  }

  @Test
  public void SpecialPoint_createSpecialPointWithVertexReferenceListContainingMoreThanTheMinimumNumberOfMembers_specialPointIsCreated() {
    List<ParamVertexReference> vertexReferenceList = Arrays.asList(createParamVertexReference(1),
        createParamVertexReference(2), createParamVertexReference(3));

    SpecialPoint specialPoint = statementFactory.createSpecialPoint(vertexReferenceList);

    assertValidSpecialPoint(vertexReferenceList, specialPoint);
  }

  @Test(expected = NullPointerException.class)
  public void SpecialPoint_copySpecialPointWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copySpecialPoint(null);
  }

  @Test
  public void SpecialPoint_copySpecialPoint_specialPointIsCopied() {
    List<ParamVertexReference> vertexReferenceList = Arrays.asList(createParamVertexReference(1),
        createParamVertexReference(2), createParamVertexReference(3));
    SpecialPoint originalSpecialPoint = statementFactory.createSpecialPoint(vertexReferenceList);

    SpecialPoint copiedSpecialPoint = statementFactory.copySpecialPoint(originalSpecialPoint);

    assertValidSpecialPoint(vertexReferenceList, copiedSpecialPoint);
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
