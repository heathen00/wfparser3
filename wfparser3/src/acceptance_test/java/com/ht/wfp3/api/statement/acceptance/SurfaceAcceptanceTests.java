package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.Surface;
import com.ht.wfp3.api.statement.VertexReferenceGroup;
import com.ht.wfp3.api.statement.VertexReferenceGroupBuilder;

public class SurfaceAcceptanceTests {
  private static final String SURFACE_KEYWORD = "surf";

  private StatementFactory statementFactory;
  private VertexReferenceGroupBuilder vertexReferenceGroupBuilder;

  private VertexReferenceGroup createVertexReferenceGroupList(int geoVertexIndex,
      int texVertexIndex, int normalVertexIndex) {
    return vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(geoVertexIndex))
        .texVertexRef(Integer.valueOf(texVertexIndex))
        .normalVertexRef(Integer.valueOf(normalVertexIndex)).build();
  }

  private void assertValidSurface(BigDecimal startingParameterValueUAxis,
      BigDecimal endingParameterValueUAxis, BigDecimal startingParameterValueVAxis,
      BigDecimal endingParameterValueVAxis, List<VertexReferenceGroup> vertexReferenceGroupList,
      Surface surface) {
    assertNotNull(surface);
    assertEquals(SURFACE_KEYWORD, surface.getKeyword());
    assertEquals(startingParameterValueUAxis, surface.getStartingParameterValueUAxis());
    assertEquals(endingParameterValueUAxis, surface.getEndingParameterValueUAxis());
    assertEquals(startingParameterValueVAxis, surface.getStartingParameterValueVAxis());
    assertEquals(endingParameterValueVAxis, surface.getEndingParameterValueVAxis());
    assertEquals(vertexReferenceGroupList, surface.getVertexReferenceGroupList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
    vertexReferenceGroupBuilder = statementFactory.createVertexReferenceGroupBuilder();
  }

  @Test(expected = NullPointerException.class)
  public void Surface_createSurfaceWithNullStartingParameterValueUAxis_nullPointerExceptionIsThrown() {
    statementFactory.createSurface(null, BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d),
        BigDecimal.valueOf(4.4d), Arrays.asList(createVertexReferenceGroupList(1, 1, 1)));
  }

  @Test(expected = NullPointerException.class)
  public void Surface_createSurfaceWithNullEndingParameterValueUAxis_nullPointerExceptionIsThrown() {
    statementFactory.createSurface(BigDecimal.valueOf(1.1d), null, BigDecimal.valueOf(3.3d),
        BigDecimal.valueOf(4.4d), Arrays.asList(createVertexReferenceGroupList(1, 1, 1)));
  }

  @Test(expected = NullPointerException.class)
  public void Surface_createSurfaceWithNullStartingParamterValueVAxis_nullPointerExceptionIsThrown() {
    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d), null,
        BigDecimal.valueOf(4.4d), Arrays.asList(createVertexReferenceGroupList(1, 1, 1)));
  }

  @Test(expected = NullPointerException.class)
  public void Surface_createSurfaceWithNullEndingParameterValueVAxis_nullPointerExceptionIsThrown() {
    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), null, Arrays.asList(createVertexReferenceGroupList(1, 1, 1)));
  }

  @Test(expected = NullPointerException.class)
  public void Surface_createSurfaceWithNullVertexReferenceGroupList_nullPointerExceptionIsThrown() {
    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Surface_createSurfaceWithVertexReferenceGroupListLessThanMinimumMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d), Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Surface_createSurfaceWithVertexReferenceGroupListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroupList(1, 1, 1), null,
            createVertexReferenceGroupList(2, 2, 2)));
  }

  @Test
  public void Surface_createSurfaceWithVertexReferenceGroupListContainingTheMininumNumberOfMembers_surfaceIsCreated() {
    BigDecimal startingParameterValueUAxis = BigDecimal.valueOf(1.1d);
    BigDecimal endingParameterValueUAxis = BigDecimal.valueOf(2.2d);
    BigDecimal startingParameterValueVAxis = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValueVAxis = BigDecimal.valueOf(4.4d);
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createVertexReferenceGroupList(1, 1, 1));

    Surface surface =
        statementFactory.createSurface(startingParameterValueUAxis, endingParameterValueUAxis,
            startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList);

    assertValidSurface(startingParameterValueUAxis, endingParameterValueUAxis,
        startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList, surface);
  }

  @Test
  public void Surface_createSurfaceWithVertexReferenceGroupListContainingMorethanTheMinimumNumberOfMembers_surfaceIsCreated() {
    BigDecimal startingParameterValueUAxis = BigDecimal.valueOf(1.1d);
    BigDecimal endingParameterValueUAxis = BigDecimal.valueOf(2.2d);
    BigDecimal startingParameterValueVAxis = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValueVAxis = BigDecimal.valueOf(4.4d);
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createVertexReferenceGroupList(1, 1, 1),
            createVertexReferenceGroupList(2, 2, 2), createVertexReferenceGroupList(3, 3, 3));

    Surface surface =
        statementFactory.createSurface(startingParameterValueUAxis, endingParameterValueUAxis,
            startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList);

    assertValidSurface(startingParameterValueUAxis, endingParameterValueUAxis,
        startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList, surface);
  }

  @Test(expected = NullPointerException.class)
  public void Surface_copySurfaceWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copySurface(null);
  }

  @Test
  public void Surface_copySurface_surfaceIsCopied() {
    BigDecimal startingParameterValueUAxis = BigDecimal.valueOf(1.1d);
    BigDecimal endingParameterValueUAxis = BigDecimal.valueOf(2.2d);
    BigDecimal startingParameterValueVAxis = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValueVAxis = BigDecimal.valueOf(4.4d);
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createVertexReferenceGroupList(1, 1, 1),
            createVertexReferenceGroupList(2, 2, 2), createVertexReferenceGroupList(3, 3, 3));
    Surface originalSurface =
        statementFactory.createSurface(startingParameterValueUAxis, endingParameterValueUAxis,
            startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList);

    Surface copiedSurface = statementFactory.copySurface(originalSurface);

    assertValidSurface(startingParameterValueUAxis, endingParameterValueUAxis,
        startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList,
        copiedSurface);
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }
}
