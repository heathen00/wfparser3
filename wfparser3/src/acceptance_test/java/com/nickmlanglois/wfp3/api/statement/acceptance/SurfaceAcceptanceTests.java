package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.Surface;
import com.nickmlanglois.wfp3.api.statement.VertexReferenceGroup;
import com.nickmlanglois.wfp3.api.statement.VertexReferenceGroupBuilder;

public class SurfaceAcceptanceTests {
  private static final String SURFACE_KEYWORD = "surf";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;
  private VertexReferenceGroupBuilder vertexReferenceGroupBuilder;

  private VertexReferenceGroup createVertexReferenceGroup(int geoVertexIndex, int texVertexIndex,
      int normalVertexIndex) {
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

  @Test
  public void Surface_createSurfaceWithNullStartingParameterValueUAxis_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("startingParameterValueUAxis cannot be null");

    statementFactory.createSurface(null, BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d),
        BigDecimal.valueOf(4.4d), Arrays.asList(createVertexReferenceGroup(1, 1, 1)));
  }

  @Test
  public void Surface_createSurfaceWithNullEndingParameterValueUAxis_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("endingParameterValueUAxis cannot be null");

    statementFactory.createSurface(BigDecimal.valueOf(1.1d), null, BigDecimal.valueOf(3.3d),
        BigDecimal.valueOf(4.4d), Arrays.asList(createVertexReferenceGroup(1, 1, 1)));
  }

  @Test
  public void Surface_createSurfaceWithNullStartingParamterValueVAxis_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("startingParameterValueVAxis cannot be null");

    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d), null,
        BigDecimal.valueOf(4.4d), Arrays.asList(createVertexReferenceGroup(1, 1, 1)));
  }

  @Test
  public void Surface_createSurfaceWithNullEndingParameterValueVAxis_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("endingParameterValueVAxis cannot be null");

    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), null, Arrays.asList(createVertexReferenceGroup(1, 1, 1)));
  }

  @Test
  public void Surface_createSurfaceWithNullVertexReferenceGroupList_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("vertexReferenceGroupList cannot be null");

    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d), null);
  }

  @Test
  public void Surface_createSurfaceWithVertexReferenceGroupListLessThanMinimumMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexReferenceGroupList must have more than ");

    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d), Collections.emptyList());
  }

  @Test
  public void Surface_createSurfaceWithVertexReferenceGroupListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexReferenceGroupList cannot contain null members");

    statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d), Arrays.asList(
            createVertexReferenceGroup(1, 1, 1), null, createVertexReferenceGroup(2, 2, 2)));
  }

  @Test
  public void Surface_createSurfaceWithVertexReferenceGroupListContainingTheMininumNumberOfMembers_surfaceIsCreated() {
    BigDecimal startingParameterValueUAxis = BigDecimal.valueOf(1.1d);
    BigDecimal endingParameterValueUAxis = BigDecimal.valueOf(2.2d);
    BigDecimal startingParameterValueVAxis = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValueVAxis = BigDecimal.valueOf(4.4d);
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createVertexReferenceGroup(1, 1, 1));

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
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3));

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
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3));
    Surface originalSurface =
        statementFactory.createSurface(startingParameterValueUAxis, endingParameterValueUAxis,
            startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList);

    Surface copiedSurface = statementFactory.copySurface(originalSurface);

    assertValidSurface(startingParameterValueUAxis, endingParameterValueUAxis,
        startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList,
        copiedSurface);
  }

  @Test
  public void Surface_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Surface first;
    Surface second;

    first = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    second = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different startingParameterValueUAxis
    first = statementFactory.createSurface(BigDecimal.valueOf(11111.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    second = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different endingParameterValueUAxis
    first = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(-2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    second = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different startingParameterValueVAxis
    first = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    second = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(-3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different endingParameterValueVaxis
    first = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(0.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    second = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different number of VertexReferenceGroup instances
    first = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3), createVertexReferenceGroup(4, 4, 4)));
    second = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different value of VertexReferenceGroup instance
    first = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(2, 2, 2)));
    second = statementFactory.createSurface(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d),
        Arrays.asList(createVertexReferenceGroup(1, 1, 1), createVertexReferenceGroup(2, 2, 2),
            createVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
