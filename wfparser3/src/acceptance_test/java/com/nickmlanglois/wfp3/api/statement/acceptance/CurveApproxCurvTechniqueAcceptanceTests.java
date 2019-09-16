package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.CurveApproxCurvTechnique;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class CurveApproxCurvTechniqueAcceptanceTests {
  private static final String CTECH_KEYWORD = "ctech";
  private static final Object CURV_KEYWORD = "curv";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithNullMaximumDistance_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("maximumDistance cannot be null");

    statementFactory.createCurveApproxCurvTechnique(null, BigDecimal.valueOf(90.0d));
  }

  @Test
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithNullMaximumAngleInDegrees_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("maximumAngleInDegrees cannot be null");

    statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(1.1d), null);
  }

  @Test
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithZeroLengthMaximumDistance_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maximumDistance must be greater than ");

    statementFactory.createCurveApproxCurvTechnique(BigDecimal.ZERO, BigDecimal.valueOf(45.0d));
  }

  @Test
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithNegativeLengthMaximumDistance_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maximumDistance must be greater than ");

    statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(-123.45d),
        BigDecimal.valueOf(30.00d));
  }

  @Test
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithZeroAngleInDegrees_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maximumAngleInDegrees must be greater than ");

    statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(3d), BigDecimal.ZERO);
  }

  @Test
  public void CurveApproxCurvTechnique_createCurveApproxCurvTechniqueWithNegativeMaximumAngleInDegrees_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maximumAngleInDegrees must be greater than ");

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

  @Test
  public void CurveApproxCurvTechnique_copyCurveApproxCurvTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("curveApproxCurvTechnique cannot be null");

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

  @Test
  public void CurveApproxCurvTechnique_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    CurveApproxCurvTechnique first;
    CurveApproxCurvTechnique second;

    first = statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(23.4d),
        BigDecimal.valueOf(90.0d));
    second = statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(23.4d),
        BigDecimal.valueOf(90.0d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: maximumDistance different
    first = statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(23.3d),
        BigDecimal.valueOf(90.0d));
    second = statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(23.4d),
        BigDecimal.valueOf(90.0d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // Not equal: maximumAngleInDegrees different
    first = statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(23.4d),
        BigDecimal.valueOf(90.0d));
    second = statementFactory.createCurveApproxCurvTechnique(BigDecimal.valueOf(23.4d),
        BigDecimal.valueOf(45.1d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
