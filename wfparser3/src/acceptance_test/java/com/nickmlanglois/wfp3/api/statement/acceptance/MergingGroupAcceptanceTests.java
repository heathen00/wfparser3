package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.MergingGroup;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class MergingGroupAcceptanceTests {
  private static final Object MERGING_GROUP_KEYWORD = "mg";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidMergingGroup(Integer expectedMergingGroupNumber,
      BigDecimal expectedMergingResolution, boolean expectedIsEnabled, MergingGroup mergingGroup) {
    assertNotNull(mergingGroup);
    assertEquals(MERGING_GROUP_KEYWORD, mergingGroup.getKeyword());
    assertEquals(expectedMergingGroupNumber, mergingGroup.getMergingGroupNumber());
    assertEquals(expectedMergingResolution, mergingGroup.getMergingResolution());
    assertEquals(expectedIsEnabled, mergingGroup.isEnabled());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void MergingGroup_createMergingGroupWithNullGroupNumber_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("mergingGroupNumber cannot be null");

    statementFactory.createMergingGroup(null, BigDecimal.valueOf(1.1d));
  }

  @Test
  public void MergingGroup_createMergingGroupWithNullResolution_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("mergingGroupResolution cannot be null");

    statementFactory.createMergingGroup(Integer.valueOf(23), null);
  }

  @Test
  public void MergingGroup_createMergingGroupWithGroupNumberLessThanZero_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("mergingGroupNumber must be greater or equal to ");

    statementFactory.createMergingGroup(Integer.valueOf(-1), BigDecimal.valueOf(3.3d));
  }

  @Test
  public void MergingGroup_createMergingGroupWithGroupNumberEqualToZero_mergingGroupIsCreated() {
    Integer mergingGroupNumber = Integer.valueOf(0);
    BigDecimal mergingGroupResolution = BigDecimal.valueOf(1.1d);
    boolean expectedIsEnabled = false;

    MergingGroup mergingGroup =
        statementFactory.createMergingGroup(mergingGroupNumber, mergingGroupResolution);

    assertValidMergingGroup(mergingGroupNumber, mergingGroupResolution, expectedIsEnabled,
        mergingGroup);
  }

  @Test
  public void MergingGroup_createMergingGroupWithGroupNumberGreaterThanZero_mergingGroupIsCreated() {
    Integer mergingGroupNumber = Integer.valueOf(1);
    BigDecimal mergingGroupResolution = BigDecimal.valueOf(1.1d);
    boolean expectedIsEnabled = true;

    MergingGroup mergingGroup =
        statementFactory.createMergingGroup(mergingGroupNumber, mergingGroupResolution);

    assertValidMergingGroup(mergingGroupNumber, mergingGroupResolution, expectedIsEnabled,
        mergingGroup);
  }

  @Test
  public void MergingGroup_createMergingGroupWithResolutionLessThanOrEqualToZero_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("mergingGroupResolution must be greater than ");

    statementFactory.createMergingGroup(Integer.valueOf(53), BigDecimal.valueOf(-00000046d));
  }

  @Test
  public void MergingGroup_createMergingGroupWithResolutionGreaterThanZero_mergingGroupIsCreated() {
    Integer mergingGroupNumber = Integer.valueOf(11111);
    BigDecimal mergingGroupResolution = BigDecimal.valueOf(0.000000001d);
    boolean expectedIsEnabled = true;

    MergingGroup mergingGroup =
        statementFactory.createMergingGroup(mergingGroupNumber, mergingGroupResolution);

    assertValidMergingGroup(mergingGroupNumber, mergingGroupResolution, expectedIsEnabled,
        mergingGroup);
  }

  @Test
  public void MergingGroup_copyMergingGroupWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("mg cannot be null");

    statementFactory.copyMergingGroup(null);
  }

  @Test
  public void MergingGroup_copyMergingGroup_mergingGroupIsCopied() {
    Integer mergingGroupNumber = Integer.valueOf(1);
    BigDecimal mergingGroupResolution = BigDecimal.valueOf(1.1d);
    boolean expectedIsEnabled = true;
    MergingGroup originalMergingGroup =
        statementFactory.createMergingGroup(mergingGroupNumber, mergingGroupResolution);

    MergingGroup copiedMergingGroup = statementFactory.copyMergingGroup(originalMergingGroup);

    assertValidMergingGroup(mergingGroupNumber, mergingGroupResolution, expectedIsEnabled,
        copiedMergingGroup);
  }

  @Test
  public void MergingGroup_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    MergingGroup first;
    MergingGroup second;

    first = statementFactory.createMergingGroup(Integer.valueOf(10), BigDecimal.valueOf(10.0d));
    second = statementFactory.createMergingGroup(Integer.valueOf(10), BigDecimal.valueOf(10.0d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different merging group number
    first = statementFactory.createMergingGroup(Integer.valueOf(22), BigDecimal.valueOf(10.0d));
    second = statementFactory.createMergingGroup(Integer.valueOf(10), BigDecimal.valueOf(10.0d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different resolution
    first = statementFactory.createMergingGroup(Integer.valueOf(10), BigDecimal.valueOf(5.0d));
    second = statementFactory.createMergingGroup(Integer.valueOf(10), BigDecimal.valueOf(10.0d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
