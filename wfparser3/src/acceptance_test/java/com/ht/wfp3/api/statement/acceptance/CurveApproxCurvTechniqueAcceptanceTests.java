package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.CurveApproxCurvTechnique;
import com.ht.wfp3.api.statement.StatementFactory;

public class CurveApproxCurvTechniqueAcceptanceTests {

  private static final String CTECH_KEYWORD = "ctech";
  private static final Object CURV_KEYWORD = "curv";
  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithNullMaximumDistance_nullPointerExceptionIsThrown() {
    statementFactory.createCurveApproxCurvTechnique(null, BigDecimal.valueOf(90.0d));
  }

  @Test(expected = NullPointerException.class)
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithNullMaximumAngleInDegrees_nullPointerExceptionIsThrown() {
    statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(1.1d), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithZeroLengthMaximumDistance_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurveApproxCurvTechnique(BigDecimal.ZERO, BigDecimal.valueOf(45.0d));
  }

  @Test(expected = IllegalArgumentException.class)
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithNegativeLengthMaximumDistance_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(-123.45d),
        BigDecimal.valueOf(30.00d));
  }

  @Test(expected = IllegalArgumentException.class)
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithZeroAngleInDegrees_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(3d), BigDecimal.ZERO);
  }

  @Test(expected = IllegalArgumentException.class)
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithNegativeMaximumAngleInDegrees_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(3d),
        BigDecimal.valueOf(-0.000000000099d));
  }

  @Test
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithValidParameters_validCurveApproxCurvTechniqueIsCreated() {
    BigDecimal maximumDistance = BigDecimal.valueOf(1.2345d);
    BigDecimal maximumAngleInDegrees = BigDecimal.valueOf(55.6d);

    CurveApproxCurvTechnique curveApproxCurvTechnique =
        statementFactory.createCurveApproxCurvTechnique(maximumDistance, maximumAngleInDegrees);

    assertNotNull(curveApproxCurvTechnique);
    assertEquals(CTECH_KEYWORD, curveApproxCurvTechnique.getKeyword());
    assertEquals(CURV_KEYWORD, curveApproxCurvTechnique.getTechniqueKeyword());
    assertEquals(maximumDistance, curveApproxCurvTechnique.getMaximumDistance());
    assertEquals(maximumAngleInDegrees, curveApproxCurvTechnique.getMaximumAngleInDegrees());
  }

  @Test(expected = NullPointerException.class)
  public void CurveApproxCurvTechnique_copyCurveApproxCurvTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyCurveApproxCurvTechnique(null);
  }

  @Test
  public void CurveApproxCurvTechnique_copyValidCurveApproxCurvTechnique_validCurveApproxCurvTechniqueIsCopied() {
    BigDecimal maximumDistance = BigDecimal.valueOf(1.2345d);
    BigDecimal maximumAngleInDegrees = BigDecimal.valueOf(55.6d);
    CurveApproxCurvTechnique originalCurveApproxCurvTechnique =
        statementFactory.createCurveApproxCurvTechnique(maximumDistance, maximumAngleInDegrees);

    CurveApproxCurvTechnique copiedCurveApproxCurvTechnique =
        statementFactory.copyCurveApproxCurvTechnique(originalCurveApproxCurvTechnique);

    assertNotNull(copiedCurveApproxCurvTechnique);
    assertEquals(CTECH_KEYWORD, copiedCurveApproxCurvTechnique.getKeyword());
    assertEquals(CURV_KEYWORD, copiedCurveApproxCurvTechnique.getTechniqueKeyword());
    assertEquals(maximumDistance, copiedCurveApproxCurvTechnique.getMaximumDistance());
    assertEquals(maximumAngleInDegrees, copiedCurveApproxCurvTechnique.getMaximumAngleInDegrees());
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
