package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.CurveApproxCspaceTechnique;
import com.ht.wfp3.api.statement.StatementFactory;

public class CurveApproxCspaceTechniqueAcceptanceTests {
  private static final String CTECH_KEYWORD = "ctech";
  private static final String CSPACE_KEYWORD = "cspace";

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void CurveApproxCspaceTechnique_createCurveApproxCspaceTechniqueWithNullMaxLengthParameter_nullPointerExceptionIsThrown() {
    statementFactory.createCurveApproxCspaceTechnique(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void CurveApproxCspaceTechnique_createCurveApproxCspaceTechniqueWithZeroValueMaxLengthParameter_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurveApproxCspaceTechnique(BigDecimal.ZERO);
  }

  @Test(expected = IllegalArgumentException.class)
  public void CurveApproxCspaceTechnique_createCurveApproxCspaceTechniqueWithNegativeValueMaxLengthParameter_illegalArgumentExceptionIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void CurveApproxCspaceTechnique_copyCurveApproxCspaceTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
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

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
