package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.End;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;

public class EndAcceptanceTests {
  private static final String END_KEYWORD = "end";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void End_copyEndWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("end cannot be null");

    statementFactory.copyEnd(null);
  }

  @Test
  public void End_copyValidEnd_validEndCopied() {
    End originalEnd = statementFactory.createEnd();

    End copiedEnd = statementFactory.copyEnd(originalEnd);

    assertNotNull(copiedEnd);
    assertEquals(END_KEYWORD, copiedEnd.getKeyword());
  }

  @Test
  public void End_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    End first;
    End second;

    first = statementFactory.createEnd();
    second = statementFactory.createEnd();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);;

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
