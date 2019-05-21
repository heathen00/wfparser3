package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Curve2DReference;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.SpecialCurve;
import com.ht.wfp3.api.statement.StatementFactory;

public class SpecialCurveAcceptanceTests {

  private static final String SCRV_KEYWORD = "scrv";

  private StatementFactory statementFactory;

  private Curve2DReference buildCurve2DReference(double startingParameter, double endingParameter,
      int curveIndex) {
    return statementFactory.createCurve2DReference(BigDecimal.valueOf(startingParameter),
        BigDecimal.valueOf(endingParameter), Integer.valueOf(curveIndex));
  }

  private void assertValidSpecialCurve(List<Curve2DReference> curve2dReferenceList,
      SpecialCurve specialCurve) {
    assertNotNull(specialCurve);
    assertEquals(SCRV_KEYWORD, specialCurve.getKeyword());
    assertEquals(curve2dReferenceList, specialCurve.getCurve2DReferenceList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void SpecialCurve_createSpecialCurveWithNullCurve2DReferenceList_nullPointerExceptionIsThrown() {
    statementFactory.createSpecialCurve(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void SpecialCurve_createSpecialCurveWithEmptyCurve2DReferenceList_illegalArgumentExceptionIsThrown() {
    statementFactory.createSpecialCurve(Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void SpecialCurve_createSpecialCurveWithCurve2DReferenceListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createSpecialCurve(Arrays.asList(buildCurve2DReference(1.1d, 2.2d, 1), null,
        buildCurve2DReference(3.3d, 4.4d, 2)));
  }

  @Test
  public void SpecialCurve_createSpecialCurveWithCurve2DReferenceListContainingOneCurve2DReference_specialCurveIsCreated() {
    List<Curve2DReference> curve2DReferenceList =
        Arrays.asList(buildCurve2DReference(1.1d, 2.2d, 1));

    SpecialCurve specialCurve = statementFactory.createSpecialCurve(curve2DReferenceList);

    assertValidSpecialCurve(curve2DReferenceList, specialCurve);
  }

  @Test
  public void SpecialCurve_createSpecialCurveWithCirve2DReferenceListContainingMoreThanOneCurve2DReference_specialCurveIsCreated() {
    List<Curve2DReference> curve2DReferenceList =
        Arrays.asList(buildCurve2DReference(1.1d, 2.2d, 1), buildCurve2DReference(3.3d, 4.4d, 2));

    SpecialCurve specialCurve = statementFactory.createSpecialCurve(curve2DReferenceList);

    assertValidSpecialCurve(curve2DReferenceList, specialCurve);
  }

  @Test(expected = NullPointerException.class)
  public void SpecialCurve_copySpecialCurveWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copySpecialCurve(null);
  }

  @Test
  public void SpecialCurve_copySpecialCurve_specialCurveIsCopied() {
    List<Curve2DReference> curve2DReferenceList =
        Arrays.asList(buildCurve2DReference(1.1d, 2.2d, 1), buildCurve2DReference(3.3d, 4.4d, 2));
    SpecialCurve originalSpecialCurve = statementFactory.createSpecialCurve(curve2DReferenceList);

    SpecialCurve copiedSpecialCurve = statementFactory.copySpecialCurve(originalSpecialCurve);

    assertValidSpecialCurve(curve2DReferenceList, copiedSpecialCurve);
  }

  @Test
  public void SpecialCurve_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    SpecialCurve first;
    SpecialCurve second;

    first = statementFactory.createSpecialCurve(Arrays
        .asList(buildCurve2DReference(1.1d, 2.2d, 100), buildCurve2DReference(3.3d, 4.4d, 200)));
    second = statementFactory.createSpecialCurve(Arrays
        .asList(buildCurve2DReference(1.1d, 2.2d, 100), buildCurve2DReference(3.3d, 4.4d, 200)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different number of curve2D references
    first = statementFactory.createSpecialCurve(Arrays
        .asList(buildCurve2DReference(1.1d, 2.2d, 100), buildCurve2DReference(3.3d, 4.4d, 200)));
    second =
        statementFactory.createSpecialCurve(Arrays.asList(buildCurve2DReference(1.1d, 2.2d, 100),
            buildCurve2DReference(3.3d, 4.4d, 200), buildCurve2DReference(5.5d, 6.6d, 300)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: differnt value of curve2D references
    first = statementFactory.createSpecialCurve(Arrays
        .asList(buildCurve2DReference(1.1d, 2.2d, 100), buildCurve2DReference(3.3d, 4.4d, 201)));
    second = statementFactory.createSpecialCurve(Arrays
        .asList(buildCurve2DReference(1.1d, 2.2d, 100), buildCurve2DReference(3.3d, 4.4d, 200)));
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
