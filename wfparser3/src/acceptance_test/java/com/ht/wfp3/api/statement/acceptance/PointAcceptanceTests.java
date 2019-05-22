package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.Point;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.VertexReferenceGroup;
import com.ht.wfp3.api.statement.VertexReferenceGroupBuilder;

public class PointAcceptanceTests {

  private static final String POINT_KEYWORD = "p";
  private StatementFactory statementFactory;
  private VertexReferenceGroupBuilder vertexReferenceGroupBuilder;

  private VertexReferenceGroup buildPointVertexReferenceGroup(int geoVertexIndex) {
    return vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(geoVertexIndex))
        .build();
  }

  private void assertValidPoint(List<VertexReferenceGroup> vertexReferenceGroupList, Point point) {
    assertNotNull(point);
    assertEquals(POINT_KEYWORD, point.getKeyword());
    assertEquals(vertexReferenceGroupList, point.getVertexReferenceGroupList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
    vertexReferenceGroupBuilder = statementFactory.createVertexReferenceGroupBuilder();
  }

  @Test(expected = NullPointerException.class)
  public void Point_createPointWithNullVertexReferenceGroupList_nullPointerExceptionIsThrown() {
    statementFactory.createPoint(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Point_createPointWithEmptyVertexReferenceGroupList_illegalArgumentExceptionIsThrown() {
    statementFactory.createPoint(Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Point_createPointWithVertexReferenceGroupListContainingNullMembers_illegalARgumentExceptionIsThrown() {
    statementFactory.createPoint(
        Arrays.asList(buildPointVertexReferenceGroup(1), null, buildPointVertexReferenceGroup(2)));
  }

  @Test
  public void Point_createPointWithOneVertexReferenceGroupInList_pointIsCreated() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(buildPointVertexReferenceGroup(33));

    Point point = statementFactory.createPoint(vertexReferenceGroupList);

    assertValidPoint(vertexReferenceGroupList, point);
  }

  @Test
  public void Point_createPointWithMoreThanOneVertexReferenceGroupInList_pointIsCreated() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(buildPointVertexReferenceGroup(33), buildPointVertexReferenceGroup(55));

    Point point = statementFactory.createPoint(vertexReferenceGroupList);

    assertValidPoint(vertexReferenceGroupList, point);
  }

  @Test(expected = NullPointerException.class)
  public void Point_copyPointWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyPoint(null);
  }

  @Test
  public void Point_copyPoint_pointIsCopied() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(buildPointVertexReferenceGroup(33));
    Point originalPoint = statementFactory.createPoint(vertexReferenceGroupList);

    Point copiedPoint = statementFactory.copyPoint(originalPoint);

    assertValidPoint(vertexReferenceGroupList, copiedPoint);
  }

  @Test
  public void Point_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Point first;
    Point second;

    first = statementFactory.createPoint(Arrays.asList(buildPointVertexReferenceGroup(234),
        buildPointVertexReferenceGroup(432), buildPointVertexReferenceGroup(5)));
    second = statementFactory.createPoint(Arrays.asList(buildPointVertexReferenceGroup(234),
        buildPointVertexReferenceGroup(432), buildPointVertexReferenceGroup(5)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different number of vertex reference groups
    first = statementFactory.createPoint(
        Arrays.asList(buildPointVertexReferenceGroup(234), buildPointVertexReferenceGroup(432),
            buildPointVertexReferenceGroup(5), buildPointVertexReferenceGroup(1234455676)));
    second = statementFactory.createPoint(Arrays.asList(buildPointVertexReferenceGroup(234),
        buildPointVertexReferenceGroup(432), buildPointVertexReferenceGroup(5)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different vertex reference group values.
    first = statementFactory.createPoint(Arrays.asList(buildPointVertexReferenceGroup(2),
        buildPointVertexReferenceGroup(432), buildPointVertexReferenceGroup(5)));
    second = statementFactory.createPoint(Arrays.asList(buildPointVertexReferenceGroup(234),
        buildPointVertexReferenceGroup(432), buildPointVertexReferenceGroup(5)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
