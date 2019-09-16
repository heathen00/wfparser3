package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.Curve2DReference;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class Curve2DReferenceAcceptanceTests {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void Curve2DReference_createCurve2DReferenceWithNullStartingParameterValue_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("startingParameterValue cannot be null");

    statementFactory.createCurve2DReference(null, BigDecimal.valueOf(1.1d), Integer.valueOf(45));
  }

  @Test
  public void Curve2DReference_createCurve2DReferenceWithNullEndingParameterValue_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("endingParameterValue cannot be null");

    statementFactory.createCurve2DReference(BigDecimal.valueOf(3.567d), null, Integer.valueOf(33));
  }

  @Test
  public void Curve2DReference_createCurve2DReferenceWithNullCurve2DIndex_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("curve2DIndex cannot be null");

    statementFactory.createCurve2DReference(BigDecimal.valueOf(2.345d), BigDecimal.valueOf(6.712d),
        null);
  }

  @Test
  public void Curve2DReference_createCurve2DReferenceWithZeroCurve2DIndex_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("curve2DIndex cannot be zero");

    statementFactory.createCurve2DReference(BigDecimal.valueOf(3.456d), BigDecimal.valueOf(999.87d),
        Integer.valueOf(0));
  }

  @Test
  public void Curve2DReference_createCurve2DReferenceWithValidParameters_validCurve2DReferenceIsCreated() {
    BigDecimal startingParameterValue = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValue = BigDecimal.valueOf(5.5d);
    Integer curve2DIndex = Integer.valueOf(-3);

    Curve2DReference curve2DReference = statementFactory
        .createCurve2DReference(startingParameterValue, endingParameterValue, curve2DIndex);

    assertNotNull(curve2DReference);
    assertEquals(startingParameterValue, curve2DReference.getStartingParameterValue());
    assertEquals(endingParameterValue, curve2DReference.getEndingParameterValue());
    assertEquals(curve2DIndex, curve2DReference.getCurve2DIndex());
  }

  @Test
  public void Curve2DReference_copyCurve2DReferenceWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("curve2DReference cannot be null");

    statementFactory.copyCurve2DReference(null);
  }

  @Test
  public void Curve2DReference_copyValidCurve2DReference_validCurve2DReferenceCopied() {
    BigDecimal startingParameterValue = BigDecimal.valueOf(3.3d);
    BigDecimal endingParameterValue = BigDecimal.valueOf(5.5d);
    Integer curve2DIndex = Integer.valueOf(-3);
    Curve2DReference originalCurve2DReference = statementFactory
        .createCurve2DReference(startingParameterValue, endingParameterValue, curve2DIndex);

    Curve2DReference copiedCurve2dReference =
        statementFactory.copyCurve2DReference(originalCurve2DReference);

    assertNotNull(copiedCurve2dReference);
    assertEquals(startingParameterValue, copiedCurve2dReference.getStartingParameterValue());
    assertEquals(endingParameterValue, copiedCurve2dReference.getEndingParameterValue());
    assertEquals(curve2DIndex, copiedCurve2dReference.getCurve2DIndex());
  }

  @Test
  public void Curve2DReference_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsAreRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Curve2DReference first;
    Curve2DReference second;

    first = statementFactory.createCurve2DReference(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), Integer.valueOf(23));
    second = statementFactory.createCurve2DReference(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), Integer.valueOf(23));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // Not equal: startingParameterValue different
    first = statementFactory.createCurve2DReference(BigDecimal.valueOf(1.2d),
        BigDecimal.valueOf(2.2d), Integer.valueOf(23));
    second = statementFactory.createCurve2DReference(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), Integer.valueOf(23));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // Not equal: endingParameterValue different
    first = statementFactory.createCurve2DReference(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.1d), Integer.valueOf(23));
    second = statementFactory.createCurve2DReference(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), Integer.valueOf(23));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // Not equal: curve2DIndex different
    first = statementFactory.createCurve2DReference(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), Integer.valueOf(23));
    second = statementFactory.createCurve2DReference(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), Integer.valueOf(1));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);
  }

  // TODO tests for mutability
}
