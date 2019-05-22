package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.SurfaceApproxCspaceTechnique;

public class SurfaceApproxCspaceTechniqueAcceptanceTests {

  private static final String STECH_KEYWORD = "stech";
  private static final String CSPACE_KEYWORD = "cspace";

  private StatementFactory statementFactory;

  private void assertValidSurfaceApproxCspaceTechnique(BigDecimal maxLength,
      SurfaceApproxCspaceTechnique surfaceApproxCspaceTechnique) {
    assertNotNull(surfaceApproxCspaceTechnique);
    assertEquals(STECH_KEYWORD, surfaceApproxCspaceTechnique.getKeyword());
    assertEquals(CSPACE_KEYWORD, surfaceApproxCspaceTechnique.getTechniqueKeyword());
    assertEquals(maxLength, surfaceApproxCspaceTechnique.getMaxLength());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCspaceTechnique_createSurfaceApproxCspaceTechniqueWithNullMaxLength_nullPointerExceptionIsThrown() {
    statementFactory.createSurfaceApproxCspaceTechnique(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCspaceTechnique_createSurfaceApproxCspaceTechniqueWithMaxLengthBelowMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurfaceApproxCspaceTechnique(BigDecimal.valueOf(-55.4d));
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCspaceTechnique_createSurfaceApproxCspaceTechniqueWithMaxLengthAtMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurfaceApproxCspaceTechnique(BigDecimal.valueOf(0.0d));
  }

  @Test
  public void SurfaceApproxCspaceTechnique_createSurfaceApproxCspaceTechniqueWithMaxLengthAboveMinimum_surfaceApproxCspaceTechniqueIsCreated() {
    BigDecimal maxLength = BigDecimal.valueOf(45.3d);

    SurfaceApproxCspaceTechnique surfaceApproxCspaceTechnique =
        statementFactory.createSurfaceApproxCspaceTechnique(maxLength);

    assertValidSurfaceApproxCspaceTechnique(maxLength, surfaceApproxCspaceTechnique);
  }

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCspaceTechnique_copySurfaceApproxCspaceTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copySurfaceApproxCspaceTechnique(null);
  }

  @Test
  public void SurfaceApproxCspaceTechnique_copySurfaceApproxCspaceTechnique_surfaceApproxCspaceTechniqueIsCopied() {
    BigDecimal maxLength = BigDecimal.valueOf(45.3d);
    SurfaceApproxCspaceTechnique originalSurfaceApproxCspaceTechnique =
        statementFactory.createSurfaceApproxCspaceTechnique(maxLength);

    SurfaceApproxCspaceTechnique copiedSurfaceApproxCspaceTechnique =
        statementFactory.copySurfaceApproxCspaceTechnique(originalSurfaceApproxCspaceTechnique);

    assertValidSurfaceApproxCspaceTechnique(maxLength, copiedSurfaceApproxCspaceTechnique);
  }

  @Test
  public void SurfaceApproxCspaceTechnique_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    SurfaceApproxCspaceTechnique first;
    SurfaceApproxCspaceTechnique second;

    first = statementFactory.createSurfaceApproxCspaceTechnique(BigDecimal.valueOf(1234.567d));
    second = statementFactory.createSurfaceApproxCspaceTechnique(BigDecimal.valueOf(1234.567d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: maxLength different value
    first = statementFactory.createSurfaceApproxCspaceTechnique(BigDecimal.valueOf(1.567d));
    second = statementFactory.createSurfaceApproxCspaceTechnique(BigDecimal.valueOf(1234.567d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
