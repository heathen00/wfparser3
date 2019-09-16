package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.SurfaceApproxCparmbTechnique;

public class SurfaceApproxCparmbTechniqueAcceptanceTests {

  private static final String STECH_KEYWORD = "stech";
  private static final String CPARMB_KEYWORD = "cparmb";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void SurfaceApproxCparmbTechnique_createSurfaceApproxCparmbTechniqueWithNullResolution_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("resolutionForUAndVAxes cannot be null");

    statementFactory.createSurfaceApproxCparmbTechnique(null);
  }

  @Test
  public void SurfaceApproxCparmbTechnique_createSurfaceApproxCparmbTechniqueWithResolutionBelowMinimum_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("resolutionForUAndVAxes must be greater or equal to ");

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

  @Test
  public void SurfaceApproxCparmbTechnique_copySurfaceApproxCparmbTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("surfaceApproxCparmbTechnique cannot be null");

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
}
