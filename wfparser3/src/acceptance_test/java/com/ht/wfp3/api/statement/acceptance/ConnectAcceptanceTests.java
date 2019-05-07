package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

  private Curve2DReference createFirstDefaultCurve2DReference() {
    return statementFactory.createCurve2DReference(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(5.5d), Integer.valueOf(78));
  }

  private Curve2DReference createSecondDefaultCurve2DReference() {
    return statementFactory.createCurve2DReference(BigDecimal.valueOf(3.3d),
        BigDecimal.valueOf(7.7d), Integer.valueOf(99));
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
    fail("Not yet implemented");
  }
  
  @Test
  public void Connect_copyValidConnect_validConnectCopyIsCreated() {
    fail("Not yet implemented");
  }

  @Test
  public void Connect_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsAreRespected() {
    fail("Not yet implemented");
  }

  @Test
  public void Connect_copyMaliciousMutableConnect_validImmutableConnectIsReturned() {
    fail("Not yet implemented");
  }
}
