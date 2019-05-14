package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.SurfaceApproxCparmaTechnique;

public class SurfaceApproxCparmaTechniqueAcceptanceTests {
  private static final String STECH_KEYWORD = "stech";
  private static final String CPARMA_KEYWORD = "cparma";

  private StatementFactory statementFactory;

  private void assertValidSurfaceApproxCparmaTechnique(BigDecimal resolutionForUAxis,
      BigDecimal resolutionForVAxis, SurfaceApproxCparmaTechnique surfaceApproxCparmaTechnique) {
    assertNotNull(surfaceApproxCparmaTechnique);
    assertEquals(STECH_KEYWORD, surfaceApproxCparmaTechnique.getKeyword());
    assertEquals(CPARMA_KEYWORD, surfaceApproxCparmaTechnique.getTechniqueKeyword());
    assertEquals(resolutionForUAxis, surfaceApproxCparmaTechnique.getResolutionForUAxis());
    assertEquals(resolutionForVAxis, surfaceApproxCparmaTechnique.getResolutionForVAxis());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithNullResolutionForUAxis_nullPointerExceptionIsThrown() {
    statementFactory.createSurfaceApproxCparmaTechnique(null, BigDecimal.valueOf(2.2d));
  }

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithNullResolutionForVAxis_nullPointerExceptionIsThrown() {
    statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(1.1d), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithResolutionForUAxisBelowMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(-0.000001d),
        BigDecimal.valueOf(2.2d));
  }

  @Test(expected = IllegalArgumentException.class)
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithResolutionForVAxisBelowMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(-1000000.55));
  }

  @Test
  public void SurfaceApproxCParmaTechnique_createSurfaceApproxCparmaTechniqueWithResolutionForUAxisSetToMinimum_surfaceApproxCParmaTechniqueIsCreated() {
    BigDecimal resolutionForUAxis = BigDecimal.valueOf(0.0d);
    BigDecimal resolutionForVAxis = BigDecimal.valueOf(55.76d);

    SurfaceApproxCparmaTechnique surfaceApproxCparmaTechnique =
        statementFactory.createSurfaceApproxCparmaTechnique(resolutionForUAxis, resolutionForVAxis);

    assertValidSurfaceApproxCparmaTechnique(resolutionForUAxis, resolutionForVAxis,
        surfaceApproxCparmaTechnique);
  }

  @Test
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithResolutionForVAxisSetToMinimum_surfaceApproxCparmaTechniqueIsCreated() {
    BigDecimal resolutionForUAxis = BigDecimal.valueOf(155.23d);
    BigDecimal resolutionForVAxis = BigDecimal.valueOf(0.0d);

    SurfaceApproxCparmaTechnique surfaceApproxCparmaTechnique =
        statementFactory.createSurfaceApproxCparmaTechnique(resolutionForUAxis, resolutionForVAxis);

    assertValidSurfaceApproxCparmaTechnique(resolutionForUAxis, resolutionForVAxis,
        surfaceApproxCparmaTechnique);
  }

  @Test
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithBothResolutionsInUAndVAxesSetToAboveMinimum_surfaceApproxCparmaTechniqueIsCreated() {
    BigDecimal resolutionForUAxis = BigDecimal.valueOf(155.23d);
    BigDecimal resolutionForVAxis = BigDecimal.valueOf(0.000456d);

    SurfaceApproxCparmaTechnique surfaceApproxCparmaTechnique =
        statementFactory.createSurfaceApproxCparmaTechnique(resolutionForUAxis, resolutionForVAxis);

    assertValidSurfaceApproxCparmaTechnique(resolutionForUAxis, resolutionForVAxis,
        surfaceApproxCparmaTechnique);
  }

  @Test(expected = NullPointerException.class)
  public void SurfaceApproxCparmaTechnique_copySurfaceApproxCparmaTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copySurfaceApproxCparmaTechnique(null);
  }

  @Test
  public void SurfaceApproxCparmaTechnique_copySurfaceApproxCparmaTechnique_surfaceApproxCparmaTechniqueIsCopied() {
    BigDecimal resolutionForUAxis = BigDecimal.valueOf(155.23d);
    BigDecimal resolutionForVAxis = BigDecimal.valueOf(0.000456d);
    SurfaceApproxCparmaTechnique originalSurfaceApproxCparmaTechnique =
        statementFactory.createSurfaceApproxCparmaTechnique(resolutionForUAxis, resolutionForVAxis);

    SurfaceApproxCparmaTechnique copiedSurfaceApproxCparmaTechnique =
        statementFactory.copySurfaceApproxCparmaTechnique(originalSurfaceApproxCparmaTechnique);

    assertValidSurfaceApproxCparmaTechnique(resolutionForUAxis, resolutionForVAxis,
        copiedSurfaceApproxCparmaTechnique);
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
