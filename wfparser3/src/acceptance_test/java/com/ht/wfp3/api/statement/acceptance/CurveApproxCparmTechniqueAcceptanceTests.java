package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.CurveApproxCparmTechnique;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;

public class CurveApproxCparmTechniqueAcceptanceTests {

  private static final String CTECH_KEYWORD = "ctech";
  private static final Object CTECH_CPARM_KEYWORD = "cparm";
  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void CurveApproxCparmTechnique_createCurveApproxCparmTechniqueWithNullResolution_nullPointerExceptionIsThrown() {
    statementFactory.createCurveApproxCparmTechnique(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void CurveApproxCparmTechnique_createCurveApproxCparmTechniqueWithResolutionLessThanZero_illegalArguementExceptionIsThrown() {
    statementFactory.createCurveApproxCparmTechnique(BigDecimal.valueOf(-0.000000001d));
  }

  @Test
  public void CurveApprocCparmTechnique_createCurveApproxCparmTechniqueWithResolutionEqualToZero_validCurveApproxCparmTechniqueIsCreated() {
    BigDecimal resolution = BigDecimal.ZERO;

    CurveApproxCparmTechnique curveApproxCparmTechnique =
        statementFactory.createCurveApproxCparmTechnique(resolution);

    assertNotNull(curveApproxCparmTechnique);
    assertEquals(CTECH_KEYWORD, curveApproxCparmTechnique.getKeyword());
    assertEquals(CTECH_CPARM_KEYWORD, curveApproxCparmTechnique.getTechniqueKeyword());
    assertEquals(resolution, curveApproxCparmTechnique.getResolution());
  }

  @Test
  public void CurveApproxCparmTechnique_createCurveApproxCparmTechniqueWithResolutionGreaterorThanZero_validCurveApproxCparmTechniqueIsCreated() {
    BigDecimal resolution = BigDecimal.valueOf(0.0000000000001d);

    CurveApproxCparmTechnique curveApproxCparmTechnique =
        statementFactory.createCurveApproxCparmTechnique(resolution);

    assertNotNull(curveApproxCparmTechnique);
    assertEquals(CTECH_KEYWORD, curveApproxCparmTechnique.getKeyword());
    assertEquals(CTECH_CPARM_KEYWORD, curveApproxCparmTechnique.getTechniqueKeyword());
    assertEquals(resolution, curveApproxCparmTechnique.getResolution());
  }

  @Test(expected = NullPointerException.class)
  public void CurveApproxCparmTechnique_copyCurveApproxCparmTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyCurveApproxCparmTechnique(null);
  }

  @Test
  public void CurveApproxCparmTechnique_copyValidCurveApproxCparmTechnique_validCurveApproxCparmTechniqueIsCopied() {
    BigDecimal resolution = BigDecimal.valueOf(0.0000000000001d);
    CurveApproxCparmTechnique originalCurveApproxCparmTechnique =
        statementFactory.createCurveApproxCparmTechnique(resolution);

    CurveApproxCparmTechnique copiedCurveApproxCparmTechnique =
        statementFactory.copyCurveApproxCparmTechnique(originalCurveApproxCparmTechnique);

    assertNotNull(copiedCurveApproxCparmTechnique);
    assertEquals(CTECH_KEYWORD, copiedCurveApproxCparmTechnique.getKeyword());
    assertEquals(CTECH_CPARM_KEYWORD, copiedCurveApproxCparmTechnique.getTechniqueKeyword());
    assertEquals(resolution, copiedCurveApproxCparmTechnique.getResolution());
  }

  @Test
  public void CurveApproxCparmTechnique_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    CurveApproxCparmTechnique first;
    CurveApproxCparmTechnique second;

    first = statementFactory.createCurveApproxCparmTechnique(BigDecimal.valueOf(10d));
    second = statementFactory.createCurveApproxCparmTechnique(BigDecimal.valueOf(10d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // Not equal: resolution different
    first = statementFactory.createCurveApproxCparmTechnique(BigDecimal.valueOf(5d));
    second = statementFactory.createCurveApproxCparmTechnique(BigDecimal.valueOf(10d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);
  }

  // TODO tests for mutability
}
