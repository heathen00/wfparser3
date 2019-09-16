package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.SmoothingGroup;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class SmoothingGroupAcceptanceTests {
  private static final String SMOOTHING_GROUP_KEYWORD = "s";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidSmoothingGroup(Integer smoothingGroupNumber, boolean expectedIsEnabled,
      SmoothingGroup smoothingGroup) {
    assertNotNull(smoothingGroup);
    assertEquals(SMOOTHING_GROUP_KEYWORD, smoothingGroup.getKeyword());
    assertEquals(smoothingGroupNumber, smoothingGroup.getSmoothingGroupNumber());
    assertEquals(expectedIsEnabled, smoothingGroup.isEnabled());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void SmoothingGroup_createSmoothingGroupWithNullGroupNumber_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("smoothingGroupNumber cannot be null");

    statementFactory.createSmoothingGroup(null);
  }

  @Test
  public void SmoothingGroup_createSmoothingGorupWithGroupNumberLessThanZero_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("smoothingGroupNumber must be greater or equal to ");

    statementFactory.createSmoothingGroup(Integer.valueOf(-1));
  }

  @Test
  public void SmoothingGroup_createSmoothingGroupWithGrouNumberEqualToZero_smoothingGroupIsCreated() {
    Integer smoothingGroupNumber = Integer.valueOf(0);
    boolean expectedIsEnabled = false;

    SmoothingGroup smoothingGroup = statementFactory.createSmoothingGroup(smoothingGroupNumber);

    assertValidSmoothingGroup(smoothingGroupNumber, expectedIsEnabled, smoothingGroup);
  }

  @Test
  public void SmoothingGroup_createSmoothingGroupWithGrouNumberGreaterThanZero_smoothingGroupIsCreated() {
    Integer smoothingGroupNumber = Integer.valueOf(1);
    boolean expectedIsEnabled = true;

    SmoothingGroup smoothingGroup = statementFactory.createSmoothingGroup(smoothingGroupNumber);

    assertValidSmoothingGroup(smoothingGroupNumber, expectedIsEnabled, smoothingGroup);
  }

  @Test
  public void SmoothingGroup_copySmoothingGroupWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("s cannot be null");

    statementFactory.copySmoothingGroup(null);
  }

  @Test
  public void SmoothingGroup_copySmoothingGroup_smoothingGroupIsCopied() {
    Integer smoothingGroupNumber = Integer.valueOf(1);
    boolean expectedIsEnabled = true;
    SmoothingGroup originalSmoothingGroup =
        statementFactory.createSmoothingGroup(smoothingGroupNumber);

    SmoothingGroup copiedSmoothingGroup =
        statementFactory.copySmoothingGroup(originalSmoothingGroup);

    assertValidSmoothingGroup(smoothingGroupNumber, expectedIsEnabled, copiedSmoothingGroup);
  }

  @Test
  public void SmoothingGroup_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    SmoothingGroup first;
    SmoothingGroup second;

    first = statementFactory.createSmoothingGroup(Integer.valueOf(2345));
    second = statementFactory.createSmoothingGroup(Integer.valueOf(2345));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different smoothing group numbers.
    first = statementFactory.createSmoothingGroup(Integer.valueOf(2345));
    second = statementFactory.createSmoothingGroup(Integer.valueOf(245));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
