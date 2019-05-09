package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.LevelOfDetail;
import com.ht.wfp3.api.statement.StatementFactory;

public class LevelOfDetailAcceptanceTests {

  private static final String LEVEL_OF_DETAIL_KEYWORD = "lod";

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

  @Test(expected = NullPointerException.class)
  public void LevelOfDetail_createLevelOfDetailWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.createLevelOfDetail(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void LevelOfDetail_createLevelOfDetailWithLevelOfDetailParameterLessThanZero_illegalArgumentExceptionIsThrown() {
    statementFactory.createLevelOfDetail(Integer.valueOf(-1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void LevelOfDetail_createLevelOFDetailWithLevelOfDetailParameterGreatherThenOneHundred_illegalArgumentExceptionIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void LevelOfDeail_copyLevelOfDetailWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyLevelOfDetail(null);
  }

  @Test
  public void LevelOfDetail_copyLevelOfDetail_levelOfDetailIsCopied() {
    Integer levelOfDetailParameter = Integer.valueOf(33);
    LevelOfDetail originalLevelOfDetail = statementFactory.createLevelOfDetail(levelOfDetailParameter);

    LevelOfDetail copiedLevelOfDetail = statementFactory.copyLevelOfDetail(originalLevelOfDetail);

    assertValidLevelOfDetail(levelOfDetailParameter, copiedLevelOfDetail);
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
