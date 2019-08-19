package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.Csh;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;

public class CshAcceptanceTests {
  private static final String CSH_KEYWORD = "csh";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void Csh_createCshWithNullCommandParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("command cannot be null");

    statementFactory.createCsh(false, null);
  }

  @Test
  public void Csh_createCshWithEmptyCommandParameterString_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("command cannot be empty or whitespace only");

    statementFactory.createCsh(false, "");
  }

  @Test
  public void Csh_createCshWithCommandParameterStringContainingOnlyWhitespace_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("command cannot be empty or whitespace only");

    statementFactory.createCsh(false, "    ");
  }

  // TODO what about line breaks?

  @Test
  public void Csh_createCshWithValidParameters_validCshIsCreated() {
    boolean shouldIgnoreError = true;
    String command = "echo '1'";

    Csh csh = statementFactory.createCsh(shouldIgnoreError, command);

    assertNotNull(csh);
    assertEquals(CSH_KEYWORD, csh.getKeyword());
    assertEquals(shouldIgnoreError, csh.shouldIgnoreErrors());
    assertEquals(command, csh.getCommand());
  }

  @Test
  public void Csh_copyCshWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("csh cannot be null");

    statementFactory.copyCsh(null);
  }

  @Test
  public void Csh_copyValidCsh_validCshIsCopied() {
    boolean shouldIgnoreError = false;
    String command = "cat test_file.data | grep '^foo:' | cut -d ':' -f 3";
    Csh originalCsh = statementFactory.createCsh(shouldIgnoreError, command);

    Csh copiedCsh = statementFactory.copyCsh(originalCsh);

    assertNotNull(copiedCsh);
    assertEquals(CSH_KEYWORD, copiedCsh.getKeyword());
    assertEquals(shouldIgnoreError, copiedCsh.shouldIgnoreErrors());
    assertEquals(command, copiedCsh.getCommand());
  }

  @Test
  public void Csh_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Csh first;
    Csh second;

    first = statementFactory.createCsh(true, "echo 'this is a test'");
    second = statementFactory.createCsh(true, "echo 'this is a test'");

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    // Not equals: different shouldIgnoreError
    first = statementFactory.createCsh(false, "echo 'foo' | grep 'foo'");
    second = statementFactory.createCsh(true, "echo 'foo' | grep 'foo'");

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // Not equals: different command
    first = statementFactory.createCsh(false, "echo 'command zzzz'");
    second = statementFactory.createCsh(false, "echo 'command aaaa'");

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);
  }

  // TODO tests for mutability
}
