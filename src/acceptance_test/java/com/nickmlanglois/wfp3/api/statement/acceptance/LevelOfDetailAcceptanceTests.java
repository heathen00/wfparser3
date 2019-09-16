package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.LevelOfDetail;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class LevelOfDetailAcceptanceTests {
  private static final String LEVEL_OF_DETAIL_KEYWORD = "lod";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidLevelOfDetail(Integer levelOfDetailParameter,
      LevelOfDetail levelOfDetail) {
    assertNotNull(levelOfDetail);
    assertEquals(LEVEL_OF_DETAIL_KEYWORD, levelOfDetail.getKeyword());
    assertEquals(levelOfDetailParameter, levelOfDetail.getLevelOfDetail());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void LevelOfDetail_createLevelOfDetailWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("levelOfDetail cannot be null");

    statementFactory.createLevelOfDetail(null);
  }

  @Test
  public void LevelOfDetail_createLevelOfDetailWithLevelOfDetailParameterLessThanZero_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("levelOfDetail cannot be less than ");

    statementFactory.createLevelOfDetail(Integer.valueOf(-1));
  }

  @Test
  public void LevelOfDetail_createLevelOFDetailWithLevelOfDetailParameterGreatherThenOneHundred_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("levelOfDetail cannot be greater than ");

    statementFactory.createLevelOfDetail(Integer.valueOf(101));
  }

  @Test
  public void LevelOfDetail_createLevelOfDetailwithLevelOfDetailParameterEqualToZero_levelOfDetailIsCreated() {
    Integer levelOfDetailParameter = Integer.valueOf(0);

    LevelOfDetail levelOfDetail = statementFactory.createLevelOfDetail(levelOfDetailParameter);

    assertValidLevelOfDetail(levelOfDetailParameter, levelOfDetail);
  }

  @Test
  public void LevelOfDetail_createLevelOfDetailWithLevelOfDetailParameterEqualToOneHundred_levelOfDetailIsCreated() {
    Integer levelOfDetailParameter = Integer.valueOf(100);

    LevelOfDetail levelOfDetail = statementFactory.createLevelOfDetail(levelOfDetailParameter);

    assertValidLevelOfDetail(levelOfDetailParameter, levelOfDetail);
  }

  @Test
  public void LevelOfDeail_copyLevelOfDetailWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("lod cannot be null");

    statementFactory.copyLevelOfDetail(null);
  }

  @Test
  public void LevelOfDetail_copyLevelOfDetail_levelOfDetailIsCopied() {
    Integer levelOfDetailParameter = Integer.valueOf(33);
    LevelOfDetail originalLevelOfDetail =
        statementFactory.createLevelOfDetail(levelOfDetailParameter);

    LevelOfDetail copiedLevelOfDetail = statementFactory.copyLevelOfDetail(originalLevelOfDetail);

    assertValidLevelOfDetail(levelOfDetailParameter, copiedLevelOfDetail);
  }

  @Test
  public void LevelOfDetail_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    LevelOfDetail first;
    LevelOfDetail second;

    first = statementFactory.createLevelOfDetail(Integer.valueOf(3));
    second = statementFactory.createLevelOfDetail(Integer.valueOf(3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // Not equal: levelOfDetail different
    first = statementFactory.createLevelOfDetail(Integer.valueOf(37));
    second = statementFactory.createLevelOfDetail(Integer.valueOf(3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // Not equal: levelOfDetail disabled in one
    first = statementFactory.createLevelOfDetail(Integer.valueOf(0));
    second = statementFactory.createLevelOfDetail(Integer.valueOf(3));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
