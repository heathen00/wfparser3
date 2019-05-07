package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Csh;
import com.ht.wfp3.api.statement.StatementFactory;

public class CshAcceptanceTests {
  
  private static final String CSH_KEYWORD = "csh";
  private StatementFactory statementFactory;
  
  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void Csh_createCshWithNullCommandParameter_nullPointerExceptionIsThrown() {
    statementFactory.createCsh(false, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Csh_createCshWithEmptyCommandParameterString_illegalArgumentExceptionIsThrown() {
    statementFactory.createCsh(false, "");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void Csh_createCshWithCommandParameterStringContainingOnlyWhitespace_illegalArgumentExceptionIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void Csh_copyCshWithNullParameter_nullPointerExceptionIsThrown() {
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
    fail("Not yet implemented");
  }

  @Test
  public void Csh_copyMaliciousMutableCsh_validImmutableCshIsCreated() {
    fail("Not yet implemented");
  }
}
