package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Bevel;
import com.ht.wfp3.api.statement.StatementFactory;

public class BevelAcceptanceTests {
  private static final String BEVEL_KEYWORD = "bevel";

  private StatementFactory statementFactory;

  // TODO Implementation note: There are really only ever going to be two Bevel instances. You could
  // use singleton / flyweight pattern.

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void Bevel_createEnabledBevel_validEnabledBevelIsCreated() {
    Bevel bevel = statementFactory.createBevel(true);

    assertNotNull(bevel);
    assertEquals(BEVEL_KEYWORD, bevel.getKeyword());
    assertTrue(bevel.isEnabled());
  }

  @Test
  public void Bevel_createDisableBevel_validDisabledBevelIsCreated() {
    Bevel bevel = statementFactory.createBevel(false);

    assertNotNull(bevel);
    assertEquals(BEVEL_KEYWORD, bevel.getKeyword());
    assertFalse(bevel.isEnabled());
  }

  @Test(expected = NullPointerException.class)
  public void Bevel_copyBevelWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyBevel(null);
  }

  @Test
  public void Bevel_exerciseAllEqualsHashCodeAndCompareToVariants_equalsHashCodeAndCompareToContractsRespected() {
    Bevel first;
    Bevel second;

    // Equal
    first = statementFactory.createBevel(true);
    second = statementFactory.createBevel(true);

    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertFalse(first == second);
    assertTrue(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) == 0);
    assertTrue(second.compareTo(first) == 0);

    assertTrue(first.equals(first));
    assertTrue(first.compareTo(first) == 0);

    // Not equal
    first = statementFactory.createBevel(false);
    second = statementFactory.createBevel(true);

    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);

    // Null
    first = statementFactory.createBevel(true);
    assertFalse(first.equals(null));
  }

  @Test
  public void Bevel_copyBevelWithMaliciousMutableBevelParameter_validImmutableBevelParameterCreated() {
    fail("Not yet implemented");
  }
}
