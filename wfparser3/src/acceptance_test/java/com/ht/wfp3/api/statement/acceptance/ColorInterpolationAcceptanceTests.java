package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.ht.wfp3.api.statement.ColorInterpolation;
import com.ht.wfp3.api.statement.StatementFactory;

import org.junit.Before;
import org.junit.Test;

public class ColorInterpolationAcceptanceTests {
  private static final String COLOR_INTERPOLATION_KEYWORD = "c_interp";

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }


  // TODO implementation note: There will only ever be two variations of ColorInterpolation
  // statements, enabled or disabled, so you could use singleton / flyweight.
  @Test
  public void ColorInterpolation_createEnabledColorInterpolation_validEnabledColorInterpolationIsCreated() {
    boolean isEnabled = true;

    ColorInterpolation colorInterpolation = statementFactory.createColorInterpolation(isEnabled);

    assertNotNull(colorInterpolation);
    assertEquals(COLOR_INTERPOLATION_KEYWORD, colorInterpolation.getKeyword());
    assertTrue(colorInterpolation.isEnabled());
  }

  @Test
  public void ColorInterpolation_createDisabledColorInterpolation_validDisabledColorInterpolationIsCreated() {
    boolean isEnabled = false;

    ColorInterpolation colorInterpolation = statementFactory.createColorInterpolation(isEnabled);

    assertNotNull(colorInterpolation);
    assertEquals(COLOR_INTERPOLATION_KEYWORD, colorInterpolation.getKeyword());
    assertFalse(colorInterpolation.isEnabled());
  }

  @Test(expected = NullPointerException.class)
  public void ColorInterpolation_copyColorInterpolationWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyColorInterpolation(null);
  }

  @Test
  public void ColorInterpolation_copyColorInterpolation_validColorInterpolationIsCopied() {
    boolean isEnabled = true;
    ColorInterpolation originalColorInterpolation =
        statementFactory.createColorInterpolation(isEnabled);

    ColorInterpolation copiedColorInterpolation =
        statementFactory.copyColorInterpolation(originalColorInterpolation);

    assertNotNull(copiedColorInterpolation);
    assertFalse(originalColorInterpolation == copiedColorInterpolation);
    assertEquals(COLOR_INTERPOLATION_KEYWORD, copiedColorInterpolation.getKeyword());
    assertTrue(copiedColorInterpolation.isEnabled());
  }

  @Test
  public void ColorInterpolation_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsAreRespected() {
    ColorInterpolation first;
    ColorInterpolation second;

    // Equals
    first = statementFactory.createColorInterpolation(true);
    second = statementFactory.createColorInterpolation(true);

    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertTrue(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) == 0);
    assertTrue(second.compareTo(first) == 0);

    assertTrue(first.equals(first));
    assertTrue(first.compareTo(first) == 0);

    // Not equals
    first = statementFactory.createColorInterpolation(false);
    second = statementFactory.createColorInterpolation(true);

    assertFalse(first.equals(second));
    assertFalse(second.equals(first));
    assertFalse(first.hashCode() == second.hashCode());
    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);

    // Null
    first = statementFactory.createColorInterpolation(false);
    assertFalse(first.equals(null));
  }

  @Test
  public void ColorInterpolation_copyMaliciousMutableColorInterpolation_validImmutableColorInterpolationIsCreated() {
    fail("Not yet implemented");
  }
}
