package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import com.ht.wfp3.api.statement.Connect;
import com.ht.wfp3.api.statement.Curve2DReference;
import com.ht.wfp3.api.statement.StatementFactory;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

public class ConnectAcceptanceTests {
  private static final String CONNECT_KEYWORD = "con";

  private StatementFactory statementFactory;


  private Curve2DReference create2DReference(double startingParameterValue,
      double endingParameterValue, int curve2DIndex) {
    return statementFactory.createCurve2DReference(BigDecimal.valueOf(startingParameterValue),
        BigDecimal.valueOf(endingParameterValue), Integer.valueOf(curve2DIndex));
  }

  private Curve2DReference createFirstDefaultCurve2DReference() {
    return create2DReference(1.1d, 5.5d, 78);
  }

  private Curve2DReference createSecondDefaultCurve2DReference() {
    return create2DReference(3.3d, 7.7d, 99);
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void Connect_createConnectWithNullFirstSurfaceIndexAllOtherParametersValid_nullPointerExceptionIsThrown() {
    statementFactory.createConnect(null, createFirstDefaultCurve2DReference(), Integer.valueOf(2),
        createSecondDefaultCurve2DReference());
  }

  @Test(expected = NullPointerException.class)
  public void Connect_createConnectWithNullFirstSurfaceCurve2dReferenceAllOtherParametersValid_nullPointerExceptionIsThrown() {
    statementFactory.createConnect(Integer.valueOf(1), null, Integer.valueOf(2),
        createSecondDefaultCurve2DReference());
  }

  @Test(expected = NullPointerException.class)
  public void Connect_createConnectWithNullSecondSurfaceIndexAllOtherParametersValid_nullPointerExceptionIsThrown() {
    statementFactory.createConnect(Integer.valueOf(1), createFirstDefaultCurve2DReference(), null,
        createSecondDefaultCurve2DReference());
  }

  @Test(expected = NullPointerException.class)
  public void Connect_createConnectWithNullSecondSurfaceCurve2dReferenceAllOtherParametersValid_nullPointerExceptionIsThrown() {
    statementFactory.createConnect(Integer.valueOf(1), createFirstDefaultCurve2DReference(),
        Integer.valueOf(2), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Connect_createConnectWithFirstSurfaceIndexEqualsZero_illegalArgumentExceptionIsThrown() {
    statementFactory.createConnect(Integer.valueOf(0), createFirstDefaultCurve2DReference(),
        Integer.valueOf(2), createSecondDefaultCurve2DReference());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Connect_createConnectWithSecondSurfaceIndexEqualsZero_illegalArgumentExceptionIsThrown() {
    statementFactory.createConnect(Integer.valueOf(2), createFirstDefaultCurve2DReference(),
        Integer.valueOf(0), createSecondDefaultCurve2DReference());
  }

  @Test
  public void Connect_createConnectWithAllValidParameters_validConnectIsCreated() {
    Integer firstSurfaceIndex = Integer.valueOf(1);
    Curve2DReference firstSurfaceCurve2dReference = createFirstDefaultCurve2DReference();
    Integer secondSurfaceIndex = Integer.valueOf(2);
    Curve2DReference secondSurfaceCurve2dReference = createSecondDefaultCurve2DReference();

    Connect connect = statementFactory.createConnect(firstSurfaceIndex,
        firstSurfaceCurve2dReference, secondSurfaceIndex, secondSurfaceCurve2dReference);

    assertNotNull(connect);
    assertEquals(CONNECT_KEYWORD, connect.getKeyword());
    assertEquals(firstSurfaceIndex, connect.getFirstSurfaceIndex());
    assertEquals(firstSurfaceCurve2dReference, connect.getFirstSurfaceCurve2DReference());
    assertEquals(secondSurfaceIndex, connect.getSecondSurfaceIndex());
    assertEquals(secondSurfaceCurve2dReference, connect.getSecondSurfaceCurve2DReference());
  }

  @Test(expected = NullPointerException.class)
  public void Connect_copyConnectWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyConnect(null);
  }

  @Test
  public void Connect_copyValidConnect_validConnectCopyIsCreated() {
    Integer firstSurfaceIndex = Integer.valueOf(1);
    Curve2DReference firstSurfaceCurve2dReference = createFirstDefaultCurve2DReference();
    Integer secondSurfaceIndex = Integer.valueOf(2);
    Curve2DReference secondSurfaceCurve2dReference = createSecondDefaultCurve2DReference();

    Connect originalConnect = statementFactory.createConnect(firstSurfaceIndex,
        firstSurfaceCurve2dReference, secondSurfaceIndex, secondSurfaceCurve2dReference);

    Connect copiedConnect = statementFactory.copyConnect(originalConnect);

    assertNotNull(copiedConnect);
    assertEquals(CONNECT_KEYWORD, copiedConnect.getKeyword());
    assertEquals(firstSurfaceIndex, copiedConnect.getFirstSurfaceIndex());
    assertEquals(firstSurfaceCurve2dReference, copiedConnect.getFirstSurfaceCurve2DReference());
    assertEquals(secondSurfaceIndex, copiedConnect.getSecondSurfaceIndex());
    assertEquals(secondSurfaceCurve2dReference, copiedConnect.getSecondSurfaceCurve2DReference());
  }

  @Test
  public void Connect_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsAreRespected() {
    Connect first;
    Connect second;

    // Equals
    first = statementFactory.createConnect(1, create2DReference(1.1d, 2.2d, 33), 2,
        create2DReference(5.5d, 6.6d, 1000));
    second = statementFactory.createConnect(1, create2DReference(1.1d, 2.2d, 33), 2,
        create2DReference(5.5d, 6.6d, 1000));
    
    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertTrue(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) == 0);
    assertTrue(second.compareTo(first) == 0);
    
    assertTrue(first.equals(first));
    assertTrue(first.compareTo(first) == 0);
    
    // Not equals
    first = statementFactory.createConnect(1, create2DReference(1.1d, 2.2d, 33), 2,
        create2DReference(5.5d, 6.6d, 1000));
    second = statementFactory.createConnect(1, create2DReference(10.1d, 20.2d, 330), 2,
        create2DReference(50.5d, 60.6d, 10000));
    
    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);
    
    // Null
    first = statementFactory.createConnect(34543, create2DReference(1.1d, 2.2d, 33), 86786,
        create2DReference(5.5d, 6.6d, 1000));
    assertFalse(first.equals(null));
  }

  @Test
  public void Connect_copyMaliciousMutableConnect_validImmutableConnectIsReturned() {
    fail("Not yet implemented");
  }
}
