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
import com.ht.wfp3.api.statement.SurfaceApproxCparmaTechnique;

public class SurfaceApproxCparmaTechniqueAcceptanceTests {
  private static final String STECH_KEYWORD = "stech";
  private static final String CPARMA_KEYWORD = "cparma";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithNullResolutionForUAxis_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("resolutionForUAxis cannot be null");

    statementFactory.createSurfaceApproxCparmaTechnique(null, BigDecimal.valueOf(2.2d));
  }

  @Test
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithNullResolutionForVAxis_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("resolutionForVAxis cannot be null");

    statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(1.1d), null);
  }

  @Test
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithResolutionForUAxisBelowMinimum_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("resolutionForUAxis must be greater or equal to ");

    statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(-0.000001d),
        BigDecimal.valueOf(2.2d));
  }

  @Test
  public void SurfaceApproxCparmaTechnique_createSurfaceApproxCparmaTechniqueWithResolutionForVAxisBelowMinimum_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("resolutionForVAxis must be greater or equal to ");

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

  @Test
  public void SurfaceApproxCparmaTechnique_copySurfaceApproxCparmaTechniqueWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("surfaceApproxCparmaTechnique cannot be null");

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

  @Test
  public void SurfaceApproxCparmaTechnique_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    SurfaceApproxCparmaTechnique first;
    SurfaceApproxCparmaTechnique second;

    first = statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d));
    second = statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: resolutionForUAxis different
    first = statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(11111111.1d),
        BigDecimal.valueOf(2.2d));
    second = statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: resolutionForVAxis different
    first = statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d));
    second = statementFactory.createSurfaceApproxCparmaTechnique(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.21d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
