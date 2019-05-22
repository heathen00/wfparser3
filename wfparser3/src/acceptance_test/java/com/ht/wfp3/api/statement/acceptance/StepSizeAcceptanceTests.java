package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.StepSize;

public class StepSizeAcceptanceTests {

  private static final String STEP_KEYWORD = "step";

  private StatementFactory statementFactory;

  private void assertValidStepSize(Integer stepSizeInUAxis, Integer stepSizeInVAxis,
      StepSize stepSize) {
    assertNotNull(stepSize);
    assertEquals(STEP_KEYWORD, stepSize.getKeyword());
    assertEquals(stepSizeInUAxis, stepSize.getStepSizeInUAxis());
    assertEquals(stepSizeInVAxis, stepSize.getStepSizeInVAxis());
    assertTrue(stepSize.isStepSizeInVAxisSet());
  }

  private void assertValidStepSize(Integer stepSizeInUAxis, StepSize stepSize) {
    assertNotNull(stepSize);
    assertEquals(STEP_KEYWORD, stepSize.getKeyword());
    assertEquals(stepSizeInUAxis, stepSize.getStepSizeInUAxis());
    assertFalse(stepSize.isStepSizeInVAxisSet());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void StepSize_createStepSizeWithNullStepSizeInUAxis_nullPointerExceptionIsThrown() {
    statementFactory.createStepSize(null, Integer.valueOf(5));
  }

  @Test(expected = NullPointerException.class)
  public void StepSize_createStepSizeWithNullStepSizeInVAxis_nullPointerExceptionIsThrown() {
    statementFactory.createStepSize(Integer.valueOf(33), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void StepSize_createStepSizeWithStepSizeInUAxisLessThanTheMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createStepSize(Integer.valueOf(0), Integer.valueOf(5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void StepSize_createStepSizeWithStepSizeInVAxisLessThanTheMinimum_illegalArgumentExceptionIsThrown() {
    statementFactory.createStepSize(Integer.valueOf(3), Integer.valueOf(0));
  }

  @Test
  public void StepSize_createStepSizeWithStepSizeInUAndVSetToTheMinimum_stepSizeIsCreated() {
    Integer stepSizeInUAxis = Integer.valueOf(1);
    Integer stepSizeInVAxis = Integer.valueOf(1);

    StepSize stepSize = statementFactory.createStepSize(stepSizeInUAxis, stepSizeInVAxis);

    assertValidStepSize(stepSizeInUAxis, stepSizeInVAxis, stepSize);
  }

  @Test
  public void StepSize_createStepSizeWithStepSizeInUAndVBothGreaterThanTheMinimum_stepSizeIsCreated() {
    Integer stepSizeInUAxis = Integer.valueOf(2);
    Integer stepSizeInVAxis = Integer.valueOf(5005);

    StepSize stepSize = statementFactory.createStepSize(stepSizeInUAxis, stepSizeInVAxis);

    assertValidStepSize(stepSizeInUAxis, stepSizeInVAxis, stepSize);
  }

  @Test
  public void StepSize_createStepSizeWithJustTheStepSizeInUAxisSpecified_stepSizeIsCreated() {
    Integer stepSizeInUAxis = Integer.valueOf(2);

    StepSize stepSize = statementFactory.createStepSize(stepSizeInUAxis);

    assertValidStepSize(stepSizeInUAxis, stepSize);
  }

  @Test(expected = NullPointerException.class)
  public void StepSize_copyStepSizeWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyStepSize(null);
  }

  @Test
  public void StepSize_copyStepSizeWithBothUAndVStepSizeSet_stepSizeIsCopied() {
    Integer stepSizeInUAxis = Integer.valueOf(2);
    Integer stepSizeInVAxis = Integer.valueOf(5005);
    StepSize originalStepSize = statementFactory.createStepSize(stepSizeInUAxis, stepSizeInVAxis);

    StepSize copiedStepSize = statementFactory.copyStepSize(originalStepSize);

    assertValidStepSize(stepSizeInUAxis, stepSizeInVAxis, copiedStepSize);
  }

  @Test
  public void StepSize_copyStepSizeWithJustUAxisStepSizeSet_stepSizeIsCopied() {
    Integer stepSizeInUAxis = Integer.valueOf(2);
    StepSize originalStepSize = statementFactory.createStepSize(stepSizeInUAxis);

    StepSize copiedStepSize = statementFactory.copyStepSize(originalStepSize);

    assertValidStepSize(stepSizeInUAxis, copiedStepSize);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void StepSize_accessStepSizeInVAxisWhenNotSet_unsupportedOperationExceptionIsThrown() {
    StepSize stepSize = statementFactory.createStepSize(Integer.valueOf(33));
    stepSize.getStepSizeInVAxis();
  }

  @Test
  public void StepSize_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    StepSize first;
    StepSize second;

    // step size in u axis only

    first = statementFactory.createStepSize(Integer.valueOf(23));
    second = statementFactory.createStepSize(Integer.valueOf(23));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different step size in u-axis.
    first = statementFactory.createStepSize(Integer.valueOf(2));
    second = statementFactory.createStepSize(Integer.valueOf(23));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // step size in both u and v axes

    first = statementFactory.createStepSize(Integer.valueOf(23), Integer.valueOf(54));
    second = statementFactory.createStepSize(Integer.valueOf(23), Integer.valueOf(54));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different step size in u-axis
    first = statementFactory.createStepSize(Integer.valueOf(230000), Integer.valueOf(54));
    second = statementFactory.createStepSize(Integer.valueOf(23), Integer.valueOf(54));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different step size in v-axis
    first = statementFactory.createStepSize(Integer.valueOf(23), Integer.valueOf(54));
    second = statementFactory.createStepSize(Integer.valueOf(23), Integer.valueOf(55));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // compare step sizes specifying different number of step size values
    first = statementFactory.createStepSize(Integer.valueOf(23), Integer.valueOf(54));
    second = statementFactory.createStepSize(Integer.valueOf(23));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
