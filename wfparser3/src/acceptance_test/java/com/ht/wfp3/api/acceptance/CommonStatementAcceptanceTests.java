package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ht.wfp3.api.statement.VisibleStatementImp;

import org.junit.Before;
import org.junit.Test;

public class CommonStatementAcceptanceTests {

  @Before
  public void setUp() throws Exception {}
  
  // TODO: The following:
  
//  
//  public void setComment(Comment comment)
//
//  public String getComment() {

  
  @Test
  public void statement_getKeyword_theKeywordStringIsReturned() {
    String testKeyword = "test_keyword";
    boolean commentable = true;
    VisibleStatementImp subStatement = new VisibleStatementImp(testKeyword, commentable);
    
    assertNotNull(subStatement.getKeyword());
    assertEquals(testKeyword, subStatement.getKeyword());
  }
  
  @Test
  public void statement_canCommentWhenCommentable_trueIsReturned() {
    String testKeyword = "test_keyword";    
    boolean commentable = true;
    VisibleStatementImp statementImp = new VisibleStatementImp(testKeyword, commentable);
    
    assertTrue(statementImp.canComment());
  }
  
  @Test
  public void statement_canCommentWhenNotCommentable_falseIsReturned() {
    String testKeyword = "test_keyword";    
    boolean commentable = false;
    VisibleStatementImp statementImp = new VisibleStatementImp(testKeyword, commentable);

    assertFalse(statementImp.canComment());
  }
  
  @Test
  public void statement_twoInstancesWithSameKeywordAndCanCommentValues_areEqualAndHaveSameHashCode() {
    VisibleStatementImp statementImpOne = new VisibleStatementImp("test_keyword", true);
    VisibleStatementImp statementImpTwo = new VisibleStatementImp("test_keyword", true);

    assertEquals(statementImpOne, statementImpTwo);
    assertEquals(statementImpOne.hashCode(), statementImpTwo.hashCode());
  }
  
  @Test
  public void statement_twoInstancesWithSameKeywordButDifferentCanCommentValues_areNotEqualAndHaveDifferentHashCodes() {
    VisibleStatementImp statementImpOne = new VisibleStatementImp("test_keyword", true);
    VisibleStatementImp statementImpTwo = new VisibleStatementImp("test_keyword", false);

    assertNotEquals(statementImpOne, statementImpTwo);
    assertNotEquals(statementImpOne.hashCode(), statementImpTwo.hashCode());
  }
  
  @Test
  public void statement_twoInstancesWithDifferentKeywordsButSameCanCommentValues_areNotEqualAndHaveDifferentHashCodes() {
    VisibleStatementImp statementImpOne = new VisibleStatementImp("test_keyword", false);
    VisibleStatementImp statementImpTwo = new VisibleStatementImp("something_else", false);

    assertNotEquals(statementImpOne, statementImpTwo);
    assertNotEquals(statementImpOne.hashCode(), statementImpTwo.hashCode());
  }
}
