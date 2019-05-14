package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.SurfaceApproxCurvTechnique;

public class SurfaceApproxCurvTechniqueAcceptanceTests {

  private static final String STECH_KEYWORD = "stech";
  private static final String CURV_KEYWORD = "curv";

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

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithNullMaxDistance_nullPointerExceptionIsThrown() {
    statementFactory.createSurfaceApproxCurvTechnique(null, BigDecimal.valueOf(33.3d));
  }

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithNullMaxAngle_nullPointerExceptionIsThrown() {
    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(44.4d), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithMaxDistanceBelowMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(-1.1d),
        BigDecimal.valueOf(5.5d));
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithMaxAngleBelowMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(5.5d),
        BigDecimal.valueOf(-1.1d));
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithMaxDistanceAtMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurfaceApproxCurvTechnique(BigDecimal.valueOf(0.0d),
        BigDecimal.valueOf(5.5d));
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCurvTechnique_createSurfaceApproxCurvTechniqueWithMaxAngleAtMinimum_illegalArgumentExceptionIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCurvTechnique_copySurfaceApproxCurvTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
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

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }
}
