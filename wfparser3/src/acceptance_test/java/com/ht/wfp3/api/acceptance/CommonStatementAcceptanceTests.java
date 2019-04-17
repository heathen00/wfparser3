package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.VisibleStatementImp;

public class CommonStatementAcceptanceTests {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void statement_getKeyword_theKeywordStringIsReturned() {
    String testKeyword = "test_keyword";
    VisibleStatementImp subStatement = new VisibleStatementImp(testKeyword);

    assertNotNull(subStatement.getKeyword());
    assertEquals(testKeyword, subStatement.getKeyword());
  }

  @Test
  public void statement_twoInstancesWithSameKeyword_areEqualAndHaveSameHashCode() {
    VisibleStatementImp statementImpOne = new VisibleStatementImp("test_keyword");
    VisibleStatementImp statementImpTwo = new VisibleStatementImp("test_keyword");

    assertEquals(statementImpOne, statementImpTwo);
    assertEquals(statementImpOne.hashCode(), statementImpTwo.hashCode());
  }

  @Test
  public void statement_twoInstancesWithDifferentKeywords_areNotEqualAndHaveDifferentHashCodes() {
    VisibleStatementImp statementImpOne = new VisibleStatementImp("test_keyword");
    VisibleStatementImp statementImpTwo = new VisibleStatementImp("something_else");

    assertNotEquals(statementImpOne, statementImpTwo);
    assertNotEquals(statementImpOne.hashCode(), statementImpTwo.hashCode());
  }
}
