package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.SurfaceApproxCparmbTechnique;

public class SurfaceApproxCparmbTechniqueAcceptanceTests {

  private static final String STECH_KEYWORD = "stech";
  private static final String CPARMB_KEYWORD = "cparmb";

  private StatementFactory statementFactory;

  private void assertValidSurfaceApproxCparmbTechnique(BigDecimal resolutionForUAndVAxes,
      SurfaceApproxCparmbTechnique surfaceApproxCparmbTechnique) {
    assertNotNull(surfaceApproxCparmbTechnique);
    assertEquals(STECH_KEYWORD, surfaceApproxCparmbTechnique.getKeyword());
    assertEquals(CPARMB_KEYWORD, surfaceApproxCparmbTechnique.getTechniqueKeyword());
    assertEquals(resolutionForUAndVAxes, surfaceApproxCparmbTechnique.getResolutionForUAndVAxes());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCparmbTechnique_createSurfaceApproxCparmbTechniqueWithNullResolution_nullPointerExceptionIsThrown() {
    statementFactory.createSurfaceApproxCparmbTechnique(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCparmbTechnique_createSurfaceApproxCparmbTechniqueWithResolutionBelowMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurfaceApproxCparmbTechnique(BigDecimal.valueOf(-1.1d));
  }

  @Test
  public void SurfaceApproxCparmbTechnique_createSurfaceApproxCparmbTechniqueWithResolutionAtMinimum_surfaceApproxCparmbTechniqueIsCreated() {
    BigDecimal resolutionForUAndVAxes = BigDecimal.valueOf(0.0d);

    SurfaceApproxCparmbTechnique surfaceApproxCparmbTechnique =
        statementFactory.createSurfaceApproxCparmbTechnique(resolutionForUAndVAxes);

    assertValidSurfaceApproxCparmbTechnique(resolutionForUAndVAxes, surfaceApproxCparmbTechnique);
  }

  @Test
  public void SurfaceApproxCparmbTechnique_createSurfaceApproxCparmbTechniqueWithResolutionAboveMinimum_surfaceApproxCparmbTechniqueIsCreated() {
    BigDecimal resolutionForUAndVAxes = BigDecimal.valueOf(3.50d);

    SurfaceApproxCparmbTechnique surfaceApproxCparmbTechnique =
        statementFactory.createSurfaceApproxCparmbTechnique(resolutionForUAndVAxes);

    assertValidSurfaceApproxCparmbTechnique(resolutionForUAndVAxes, surfaceApproxCparmbTechnique);
  }

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCparmbTechnique_copySurfaceApproxCparmbTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copySurfaceApproxCparmbTechnique(null);
  }

  @Test
  public void SurfaceApproxCparmbTechnique_copySurfaceApproxCparmbTechnique_surfaceApproxCparmbTechniqueIsCopied() {
    BigDecimal resolutionForUAndVAxes = BigDecimal.valueOf(3.50d);
    SurfaceApproxCparmbTechnique originalSurfaceApproxCparmbTechnique =
        statementFactory.createSurfaceApproxCparmbTechnique(resolutionForUAndVAxes);

    SurfaceApproxCparmbTechnique copiedSurfaceApproxCparmbTechnique =
        statementFactory.copySurfaceApproxCparmbTechnique(originalSurfaceApproxCparmbTechnique);

    assertValidSurfaceApproxCparmbTechnique(resolutionForUAndVAxes,
        copiedSurfaceApproxCparmbTechnique);
  }

  @Test
  public void SurfaceApproxCparmbTechnique_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    SurfaceApproxCparmbTechnique first;
    SurfaceApproxCparmbTechnique second;

    first = statementFactory.createSurfaceApproxCparmbTechnique(BigDecimal.valueOf(123.45d));
    second = statementFactory.createSurfaceApproxCparmbTechnique(BigDecimal.valueOf(123.45d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // different: different resolutionForUAndVAxes
    first = statementFactory.createSurfaceApproxCparmbTechnique(BigDecimal.valueOf(12389.45d));
    second = statementFactory.createSurfaceApproxCparmbTechnique(BigDecimal.valueOf(123.45d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
