package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.Curve;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.GeoVertexReference;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class CurveAcceptanceTests {
  private static final String CURVE_KEYWORD = "curv";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void Curve_createCurveWithNullStartingParameterValue_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("startingParameterValue cannot be null");

    statementFactory.createCurve(null, BigDecimal.valueOf(5.5d),
        createGeoVertexReferenceList(1, 2, 3));
  }

  @Test
  public void Curve_createCurveWithNullEndingParameterValue_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("endingParameterValue cannot be null");

    statementFactory.createCurve(BigDecimal.valueOf(4.44444d), null,
        createGeoVertexReferenceList(1, 2, 3));
  }

  @Test
  public void Curve_createCurveWithNullVertexReferenceGroupList_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("controlPointVertexReferenceList cannot be null");

    statementFactory.createCurve(BigDecimal.valueOf(5.678d), BigDecimal.valueOf(6789.1234d), null);
  }

  @Test
  public void Curve_createCurveWithEmptyVertexReferenceGroupList_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("controlPointVertexReferenceList requires at least ");

    statementFactory.createCurve(BigDecimal.valueOf(5.678d), BigDecimal.valueOf(3.3333d),
        Collections.emptyList());
  }

  @Test
  public void Curve_createCurveWithGeoVertexReferenceListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("controlPointVertexReferenceList cannot contain null members");

    statementFactory.createCurve(BigDecimal.valueOf(4.567d), BigDecimal.valueOf(3.1235d),
        Arrays.asList(statementFactory.createGeoVertexReference(1), null,
            statementFactory.createGeoVertexReference(56)));
  }

  @Test
  public void Curve_createCurveWithOneControlPointInVertexReferenceGroupList_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("controlPointVertexReferenceList requires at least ");

    statementFactory.createCurve(BigDecimal.valueOf(3.456d), BigDecimal.valueOf(4.678d),
        createGeoVertexReferenceList(67));
  }

  @Test
  public void Curve_copyCurveWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("curv cannot be null");

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
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Curve first;
    Curve second;

    first = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 2, 3));
    second = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 2, 3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // Not equal: startingParameterValue different
    first = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 2, 3));
    second = statementFactory.createCurve(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 2, 3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // Not equal: endingParameterValue different
    first = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 2, 3));
    second = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(0.0d),
        createGeoVertexReferenceList(1, 2, 3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // Not equal: different number of geoVertexReferences
    first = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 2));
    second = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 2, 3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // Not equal: different geoVertexReference values
    first = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 2, 3));
    second = statementFactory.createCurve(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.3d),
        createGeoVertexReferenceList(1, 7, 3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);
  }

  // TODO tests for mutability
}
