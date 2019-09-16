package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.Bevel;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.MutabilityTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.MutabilityTester.Creator;

public class BevelAcceptanceTests {
  private static final String BEVEL_KEYWORD = "bevel";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void Bevel_copyBevelWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("bevel cannot be null");

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
  public void Bevel_checkMutableDefensiveCopy_validImmutableInstanceIsCreated() throws Exception {
    final boolean isEnabled = true;
    final MutabilityTester<Bevel> mutabilityTester =
        new MutabilityTester<Bevel>(new Creator<Bevel>() {

          @Override
          public Bevel create() {
            return statementFactory.createBevel(isEnabled);
          }

          @Override
          public Bevel copy(Bevel o) {
            return statementFactory.copyBevel(mutable(o));
          }

          @Override
          public Map<String, Object> getExpectedMemberData() {
            Map<String, Object> methodDataMap = new HashMap<>();
            return methodDataMap;
          }
        });
    mutabilityTester.assertImmutability();
  }
}
