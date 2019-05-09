package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
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

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.
  // TODO what about vertexReferenceGroupLists that contain null members?

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
