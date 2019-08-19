package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.SurfaceApproxCurvTechnique;

public class SurfaceApproxCurvTechniqueAcceptanceTests {

  private static final String STECH_KEYWORD = "stech";
  private static final String CURV_KEYWORD = "curv";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidSurfaceApproxCurvTechnique(BigDecimal maxDistance,
      BigDecimal maxAngleInDegrees, SurfaceApproxCurvTechnique surfaceApproxCurvTechnique) {
    assertNotNull(surfaceApproxCurvTechnique);
    assertEquals(STECH_KEYWORD, surfaceApproxCurvTechnique.getKeyword());
    assertEquals(CURV_KEYWORD, surfaceApproxCurvTechnique.getTechniqueKeyword());
    assertEquals(maxDistance, surfaceApproxCurvTechnique.getMaxDistance());
    assertEquals(maxAngleInDegrees, surfaceApproxCurvTechnique.getMaxAngleInDegrees());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithNullMaxDistance_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("maxDistance cannot be null");

    statementFactory.createSurfaceApproxCurvTechnique(null, BigDecimal.valueOf(33.3d));
  }

  @Test
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithNullMaxAngle_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("maxAngleInDegrees cannot be null");

    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(44.4d), null);
  }

  @Test
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithMaxDistanceBelowMinimum_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maxDistance must be greater than ");

    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(-1.1d),
        BigDecimal.valueOf(5.5d));
  }

  @Test
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithMaxAngleBelowMinimum_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maxAngleInDegrees must be greater than ");

    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(5.5d),
        BigDecimal.valueOf(-1.1d));
  }

  @Test
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithMaxDistanceAtMinimum_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maxDistance must be greater than ");

    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(0.0d),
        BigDecimal.valueOf(5.5d));
  }

  @Test
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithMaxAngleAtMinimum_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maxAngleInDegrees must be greater than ");

    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(5.5d),
        BigDecimal.valueOf(0.0d));
  }

  @Test
  public void SurfaceApproxCurvTechnique_createSurfaceApprocCurvTechniqueWithBothMaxDistanceAndMaxAngleAboveMinimum_surfaceApproxCurvTechniqueIsCreated() {
    BigDecimal maxDistance = BigDecimal.valueOf(5.5d);
    BigDecimal maxAngleInDegrees = BigDecimal.valueOf(45.0d);

    SurfaceApproxCurvTechnique surfaceApproxCurvTechnique =
        statementFactory.createSurfaceApproxCurvTechnique(maxDistance, maxAngleInDegrees);

    assertValidSurfaceApproxCurvTechnique(maxDistance, maxAngleInDegrees,
        surfaceApproxCurvTechnique);
  }

  @Test
  public void SurfaceApproxCurvTechnique_copySurfaceApproxCurvTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("surfaceApproxCurvTechnique cannot be null");

    statementFactory.copySurfaceApproxCurvTechnique(null);
  }

  @Test
  public void SurfaceApproxCurvTechnique_copySurfaceApproxCurvTechnique_surfaceApproxCurvTechniqueIsCopied() {
    BigDecimal maxDistance = BigDecimal.valueOf(5.5d);
    BigDecimal maxAngleInDegrees = BigDecimal.valueOf(45.0d);
    SurfaceApproxCurvTechnique originalSurfaceApproxCurvTechnique =
        statementFactory.createSurfaceApproxCurvTechnique(maxDistance, maxAngleInDegrees);

    SurfaceApproxCurvTechnique copiedSurfaceApproxCurvTechnique =
        statementFactory.copySurfaceApproxCurvTechnique(originalSurfaceApproxCurvTechnique);

    assertValidSurfaceApproxCurvTechnique(maxDistance, maxAngleInDegrees,
        copiedSurfaceApproxCurvTechnique);
  }

  @Test
  public void SurfaceApproxCurvTechnique_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    SurfaceApproxCurvTechnique first;
    SurfaceApproxCurvTechnique second;

    first = statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(123.45d),
        BigDecimal.valueOf(30.0d));
    second = statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(123.45d),
        BigDecimal.valueOf(30.0d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different maxLength
    first = statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(1234.45d),
        BigDecimal.valueOf(30.0d));
    second = statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(123.45d),
        BigDecimal.valueOf(30.0d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different maxAngleInDegrees
    first = statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(123.45d),
        BigDecimal.valueOf(31.0d));
    second = statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(123.45d),
        BigDecimal.valueOf(30.0d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
