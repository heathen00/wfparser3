package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.Line;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.VertexReferenceGroup;

public class LineAcceptanceTests {

  private static final String LINE_KEYWORD = "l";

  private StatementFactory statementFactory;


  private void assertValidLine(List<VertexReferenceGroup> vertexReferenceGroupList, Line line) {
    assertNotNull(line);
    assertEquals(LINE_KEYWORD, line.getKeyword());
    assertEquals(vertexReferenceGroupList, line.getVertexReferenceGroupList());
  }

  private VertexReferenceGroup createVertexReferenceGroup(int geoVertexIndex, int texVertexInde) {
    return statementFactory.createVertexReferenceGroupBuilder().clear()
        .geoVertexRef(Integer.valueOf(geoVertexIndex)).texVertexRef(Integer.valueOf(texVertexInde))
        .build();
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void Line_createLineWithNullVertexReferenceGroupList_nullPointerExceptionIsThrown() {
    statementFactory.createLine(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Line_createLineWithLessThanTwoVertexReferenceGroupsInVertexReferenceGroupList_illegalArgumentExceptionIsThrown() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createVertexReferenceGroup(1, 1));

    statementFactory.createLine(vertexReferenceGroupList);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Line_createLineWithVertexReferenceGroupListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createLine(
        Arrays.asList(createVertexReferenceGroup(1, 1), null, createVertexReferenceGroup(3, 3)));
  }

  @Test
  public void Line_createLineWithTwoVertexReferenceGroupsInVertexReferenceGroupList_lineIsCreated() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createVertexReferenceGroup(1, 1), createVertexReferenceGroup(2, 2));

    Line line = statementFactory.createLine(vertexReferenceGroupList);

    assertValidLine(vertexReferenceGroupList, line);
  }

  @Test
  public void Line_createLineWithMoreThanTwoVertexReferenceGroupsInVertexReferenceGroupList_lineIsCreated() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createVertexReferenceGroup(1, 1), createVertexReferenceGroup(2, 2),
            createVertexReferenceGroup(3, 3));

    Line line = statementFactory.createLine(vertexReferenceGroupList);

    assertValidLine(vertexReferenceGroupList, line);
  }

  @Test(expected = NullPointerException.class)
  public void Line_copyLineWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyLine(null);
  }

  @Test
  public void Line_copyLine_lineIsCopied() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createVertexReferenceGroup(1, 1), createVertexReferenceGroup(2, 2),
            createVertexReferenceGroup(3, 3));
    Line originalLine = statementFactory.createLine(vertexReferenceGroupList);

    Line copiedLine = statementFactory.copyLine(originalLine);

    assertValidLine(vertexReferenceGroupList, copiedLine);
  }

  @Test
  public void Line_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Line first;
    Line second;

    first = statementFactory.createLine(
        Arrays.asList(createVertexReferenceGroup(1, 1), createVertexReferenceGroup(2, 2)));
    second = statementFactory.createLine(
        Arrays.asList(createVertexReferenceGroup(1, 1), createVertexReferenceGroup(2, 2)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal different number of vertex reference groups.
    first = statementFactory.createLine(
        Arrays.asList(createVertexReferenceGroup(1, 1), createVertexReferenceGroup(2, 2)));
    second = statementFactory.createLine(Arrays.asList(createVertexReferenceGroup(1, 1),
        createVertexReferenceGroup(2, 2), createVertexReferenceGroup(3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different values for vertex reference group
    first = statementFactory.createLine(
        Arrays.asList(createVertexReferenceGroup(100, 1), createVertexReferenceGroup(2, 2)));
    second = statementFactory.createLine(
        Arrays.asList(createVertexReferenceGroup(1, 1), createVertexReferenceGroup(2, 2)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
