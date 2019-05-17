package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Curve;
import com.ht.wfp3.api.statement.GeoVertexReference;
import com.ht.wfp3.api.statement.StatementFactory;

public class CurveAcceptanceTests {

  private static final String CURVE_KEYWORD = "curv";
  private StatementFactory statementFactory;

  private List<GeoVertexReference> createGeoVertexReferenceList(Integer... geoVertexReferences) {
    if (geoVertexReferences.length == 0) {
      throw new IllegalArgumentException();
    }
    List<GeoVertexReference> geoVertexReferenceList = new ArrayList<>();
    for (Integer integer : geoVertexReferences) {
      geoVertexReferenceList.add(statementFactory.createGeoVertexReference(integer));
    }
    return geoVertexReferenceList;
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void Curve_createCurveWithNullStartingParameterValue_nullPointerExceptionIsThrown() {
    statementFactory.createCurve(null, BigDecimal.valueOf(5.5d),
        createGeoVertexReferenceList(1, 2, 3));
  }

  @Test(expected = NullPointerException.class)
  public void Curve_createCurveWithNullEndingParameterValue_nullPointerExceptionIsThrown() {
    statementFactory.createCurve(BigDecimal.valueOf(4.44444d), null,
        createGeoVertexReferenceList(1, 2, 3));
  }

  @Test(expected = NullPointerException.class)
  public void Curve_createCurveWithNullVertexReferenceGroupList_nullPointerExceptionIsThrown() {
    statementFactory.createCurve(BigDecimal.valueOf(5.678d), BigDecimal.valueOf(6789.1234d), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Curve_createCurveWithEmptyVertexReferenceGroupList_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurve(BigDecimal.valueOf(5.678d), BigDecimal.valueOf(3.3333d),
        Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Curve_createCurveWithGeoVertexReferenceListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurve(BigDecimal.valueOf(4.567d), BigDecimal.valueOf(3.1235d),
        Arrays.asList(statementFactory.createGeoVertexReference(1), null,
            statementFactory.createGeoVertexReference(56)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void Curve_createCurveWithOneControlPointInVertexReferenceGroupList_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurve(BigDecimal.valueOf(3.456d), BigDecimal.valueOf(4.678d),
        createGeoVertexReferenceList(67));
  }

  @Test(expected = NullPointerException.class)
  public void Curve_copyCurveWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyCurve(null);
  }

  @Test
  public void Curve_createCurveWithTwoControlPointsInVertexReferenceGroupList_validCurveIsCreated() {
    BigDecimal startingParameterValue = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValue = BigDecimal.valueOf(4.4d);
    List<GeoVertexReference> vertexReferenceGroupList = createGeoVertexReferenceList(1, 2);

    Curve curve = statementFactory.createCurve(startingParameterValue, endingParameterValue,
        vertexReferenceGroupList);

    assertNotNull(curve);
    assertEquals(CURVE_KEYWORD, curve.getKeyword());
    assertEquals(startingParameterValue, curve.getStartingParameterValue());
    assertEquals(endingParameterValue, curve.getEndingParameterValue());
    assertEquals(vertexReferenceGroupList, curve.getControlPointVertexReferenceList());
  }

  @Test
  public void Curve_createCurveWithMoreThanTwoControlPointsInVertexReferenceGroupList_validCurveIsCreated() {
    BigDecimal startingParameterValue = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValue = BigDecimal.valueOf(4.4d);
    List<GeoVertexReference> vertexReferenceGroupList =
        createGeoVertexReferenceList(1, 2, 4, 6, 8, 2323, 78, 5);

    Curve curve = statementFactory.createCurve(startingParameterValue, endingParameterValue,
        vertexReferenceGroupList);

    assertNotNull(curve);
    assertEquals(CURVE_KEYWORD, curve.getKeyword());
    assertEquals(startingParameterValue, curve.getStartingParameterValue());
    assertEquals(endingParameterValue, curve.getEndingParameterValue());
    assertEquals(vertexReferenceGroupList, curve.getControlPointVertexReferenceList());
  }

  @Test
  public void Curve_copyValidCurve_validCurveIsCopied() {
    BigDecimal startingParameterValue = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValue = BigDecimal.valueOf(4.4d);
    List<GeoVertexReference> vertexReferenceGroupList =
        createGeoVertexReferenceList(1, 2, 4, 6, 8, 2323, 78, 5);
    Curve originalCurve = statementFactory.createCurve(startingParameterValue, endingParameterValue,
        vertexReferenceGroupList);

    Curve copiedCurve = statementFactory.copyCurve(originalCurve);

    assertNotNull(copiedCurve);
    assertEquals(CURVE_KEYWORD, copiedCurve.getKeyword());
    assertEquals(startingParameterValue, copiedCurve.getStartingParameterValue());
    assertEquals(endingParameterValue, copiedCurve.getEndingParameterValue());
    assertEquals(vertexReferenceGroupList, copiedCurve.getControlPointVertexReferenceList());
  }

  @Test
  public void Curve_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsAreRespected() {
    fail("Not yet implemented");
  }

  @Test
  public void Curve_copyCurveWithMaliciousMutableCurve_validImmutableCurveIsCreated() {
    fail("Not yet implemented");
  }
}
