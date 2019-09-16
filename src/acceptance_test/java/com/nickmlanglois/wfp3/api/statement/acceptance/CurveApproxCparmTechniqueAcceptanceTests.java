package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.CurveApproxCparmTechnique;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class CurveApproxCparmTechniqueAcceptanceTests {
  private static final String CTECH_KEYWORD = "ctech";
  private static final Object CTECH_CPARM_KEYWORD = "cparm";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void CurveApproxCparmTechnique_createCurveApproxCparmTechniqueWithNullResolution_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("resolution cannot be null");

    statementFactory.createCurveApproxCparmTechnique(null);
  }

  @Test
  public void CurveApproxCparmTechnique_createCurveApproxCparmTechniqueWithResolutionLessThanZero_illegalArguementExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("resolution must be greater or equal to ");

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

  @Test
  public void CurveApproxCparmTechnique_copyCurveApproxCparmTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("curveApproxCparmTechnique cannot be null");

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
