package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.CurveApproxCspaceTechnique;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class CurveApproxCspaceTechniqueAcceptanceTests {
  private static final String CTECH_KEYWORD = "ctech";
  private static final String CSPACE_KEYWORD = "cspace";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void CurveApproxCspaceTechnique_createCurveApproxCspaceTechniqueWithNullMaxLengthParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("maxLength cannot be null");

    statementFactory.createCurveApproxCspaceTechnique(null);
  }

  @Test
  public void CurveApproxCspaceTechnique_createCurveApproxCspaceTechniqueWithZeroValueMaxLengthParameter_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maxLength must be greater than ");

    statementFactory.createCurveApproxCspaceTechnique(BigDecimal.ZERO);
  }

  @Test
  public void CurveApproxCspaceTechnique_createCurveApproxCspaceTechniqueWithNegativeValueMaxLengthParameter_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maxLength must be greater than ");

    statementFactory.createCurveApproxCspaceTechnique(BigDecimal.valueOf(-0.00000001f));
  }

  @Test
  public void CurveApproxCspaceTechnique_createCurveApproxCspaceTechniqueWithMaxLengthParameterValueGreaterThanZero_validCurveApproxCspaceTechniqueIsCreated() {
    BigDecimal maxLength = BigDecimal.valueOf(12.345d);

    CurveApproxCspaceTechnique curveApproxCspaceTechnique =
        statementFactory.createCurveApproxCspaceTechnique(maxLength);

    assertNotNull(curveApproxCspaceTechnique);
    assertEquals(CTECH_KEYWORD, curveApproxCspaceTechnique.getKeyword());
    assertEquals(CSPACE_KEYWORD, curveApproxCspaceTechnique.getTechniqueKeyword());
    assertEquals(maxLength, curveApproxCspaceTechnique.getMaxLength());
  }

  @Test
  public void CurveApproxCspaceTechnique_copyCurveApproxCspaceTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("maxLength cannot be null");

    statementFactory.createCurveApproxCspaceTechnique(null);
  }

  @Test
  public void CurveApproxCspaceTechnique_copyValidCurveApproxCspaceTechnique_validCurveApproxCspaceTechniqueIsCopied() {
    BigDecimal maxLength = BigDecimal.valueOf(12.345d);
    CurveApproxCspaceTechnique originalCurveApproxCspaceTechnique =
        statementFactory.createCurveApproxCspaceTechnique(maxLength);

    CurveApproxCspaceTechnique copiedCurveApproxCspaceTechnique =
        statementFactory.copyCurveApproxCspaceTechnique(originalCurveApproxCspaceTechnique);

    assertNotNull(copiedCurveApproxCspaceTechnique);
    assertEquals(CTECH_KEYWORD, copiedCurveApproxCspaceTechnique.getKeyword());
    assertEquals(CSPACE_KEYWORD, copiedCurveApproxCspaceTechnique.getTechniqueKeyword());
    assertEquals(maxLength, copiedCurveApproxCspaceTechnique.getMaxLength());
  }

  @Test
  public void CurveApproxCspaceTechnique_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    CurveApproxCspaceTechnique first;
    CurveApproxCspaceTechnique second;

    first = statementFactory.createCurveApproxCspaceTechnique(BigDecimal.valueOf(1001.1d));
    second = statementFactory.createCurveApproxCspaceTechnique(BigDecimal.valueOf(1001.1d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: maxLength different
    first = statementFactory.createCurveApproxCspaceTechnique(BigDecimal.valueOf(1001.1d));
    second = statementFactory.createCurveApproxCspaceTechnique(BigDecimal.valueOf(101.1d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
