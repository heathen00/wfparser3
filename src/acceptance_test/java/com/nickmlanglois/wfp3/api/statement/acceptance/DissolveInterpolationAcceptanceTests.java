package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.DissolveInterpolation;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class DissolveInterpolationAcceptanceTests {
  private static final String D_INTERP_KEYWORD = "d_interp";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidDissolveInterpolation(boolean expectedIsEnabled,
      DissolveInterpolation dissolveInterpolation) {
    assertNotNull(dissolveInterpolation);
    assertEquals(D_INTERP_KEYWORD, dissolveInterpolation.getKeyword());
    assertEquals(expectedIsEnabled, dissolveInterpolation.isEnabled());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void DissolveInterpolation_createEnabledDissolveInterpolation_enabledDissolveInterpolationIsCreated() {
    boolean isEnabled = true;

    DissolveInterpolation dissolveInterpolation =
        statementFactory.createDissolveInterpolation(isEnabled);

    assertValidDissolveInterpolation(isEnabled, dissolveInterpolation);
  }

  @Test
  public void DissolveInterpolation_createDisabledDissolveInterpolation_disabledDissolveInterpolationIsCreated() {
    boolean isEnabled = false;

    DissolveInterpolation dissolveInterpolation =
        statementFactory.createDissolveInterpolation(isEnabled);

    assertValidDissolveInterpolation(isEnabled, dissolveInterpolation);
  }

  @Test
  public void DissolveInterpolation_copyDissolveInterpolationWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("d_interp cannot be null");

    statementFactory.copyDissolveInterpolation(null);
  }

  @Test
  public void DissolveInterpolation_copyValidDissolveInterpolaiton_validDissolveInterpolationIsCopied() {
    boolean isEnabled = true;
    DissolveInterpolation originalDissolveInterpolation =
        statementFactory.createDissolveInterpolation(isEnabled);

    DissolveInterpolation copiedDissolveInterpolation =
        statementFactory.copyDissolveInterpolation(originalDissolveInterpolation);

    assertValidDissolveInterpolation(isEnabled, copiedDissolveInterpolation);
  }

  @Test
  public void DissolveInterpolation_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    DissolveInterpolation first;
    DissolveInterpolation second;

    first = statementFactory.createDissolveInterpolation(false);
    second = statementFactory.createDissolveInterpolation(false);
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    first = statementFactory.createDissolveInterpolation(false);
    second = statementFactory.createDissolveInterpolation(true);
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
  // TODO implementation note: there will only ever be two instances, either enabled or disabled.
  // Could use FlyWeight.
}
