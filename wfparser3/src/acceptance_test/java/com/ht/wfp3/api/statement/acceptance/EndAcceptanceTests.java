package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.End;
import com.ht.wfp3.api.statement.StatementFactory;

public class EndAcceptanceTests {

  private static final String END_KEYWORD = "end";
  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void End_createValidEnd_validEndCreated() {
    End end = statementFactory.createEnd();

    assertNotNull(end);
    assertEquals(END_KEYWORD, end.getKeyword());
  }

  @Test(expected = NullPointerException.class)
  public void End_copyEndWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyEnd(null);
  }

  @Test
  public void End_copyValidEnd_validEndCopied() {
    End originalEnd = statementFactory.createEnd();

    End copiedEnd = statementFactory.copyEnd(originalEnd);

    assertNotNull(copiedEnd);
    assertEquals(END_KEYWORD, copiedEnd.getKeyword());
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }
}
