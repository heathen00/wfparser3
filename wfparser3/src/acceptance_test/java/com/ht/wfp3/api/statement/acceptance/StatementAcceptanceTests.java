package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.VisibleStatementImp;

public class StatementAcceptanceTests {

  @Test
  public void Statement_getKeyword_theKeywordStringIsReturned() {
    String testKeyword = "test_keyword";
    VisibleStatementImp subStatement = new VisibleStatementImp(testKeyword);

    assertNotNull(subStatement.getKeyword());
    assertEquals(testKeyword, subStatement.getKeyword());
  }

  @Test
  public void Statement_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    VisibleStatementImp first;
    VisibleStatementImp second;

    first = new VisibleStatementImp("test_keyword");
    second = new VisibleStatementImp("test_keyword");
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different keywords
    first = new VisibleStatementImp("test_keyword");
    second = new VisibleStatementImp("test_keyword_two");
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }
}
