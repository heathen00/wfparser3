package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Bevel;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
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
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Bevel first;
    Bevel second;

    first = statementFactory.createBevel(true);
    second = statementFactory.createBevel(true);

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    first = statementFactory.createBevel(false);
    second = statementFactory.createBevel(true);

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    first = statementFactory.createBevel(true);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);
  }

  @Test
  public void Bevel_copyBevelWithMaliciousMutableBevelParameter_validImmutableBevelParameterCreated() {
    fail("Not yet implemented");
  }
}
