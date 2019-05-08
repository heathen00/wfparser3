package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.DissolveInterpolation;
import com.ht.wfp3.api.statement.StatementFactory;

public class DissolveInterpolationAcceptanceTests {

  private static final String D_INTERP_KEYWORD = "d_interp";
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

  @Test(expected = NullPointerException.class)
  public void DissolveInterpolation_copyDissolveInterpolationWithNullParameter_nullPointerExceptionIsThrown() {
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

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.
  // TODO implementation note: there will only ever be two instances, either enabled or disabled.
  // Could use FlyWeight.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
