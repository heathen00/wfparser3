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
import com.nickmlanglois.wfp3.api.statement.ColorInterpolation;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.MutabilityTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.MutabilityTester.Creator;

public class ColorInterpolationAcceptanceTests {
  private static final String COLOR_INTERPOLATION_KEYWORD = "c_interp";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void ColorInterpolation_copyColorInterpolationWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("c_interp cannot be null");

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
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    ColorInterpolation first;
    ColorInterpolation second;

    first = statementFactory.createColorInterpolation(true);
    second = statementFactory.createColorInterpolation(true);

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    first = statementFactory.createColorInterpolation(false);
    second = statementFactory.createColorInterpolation(true);

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    first = statementFactory.createColorInterpolation(false);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);
  }

  @Test
  public void ColorInterpolation_checkMutableDefensiveCopy_validImmutableInstanceIsCreated()
      throws Exception {
    final boolean isEnabled = false;
    final MutabilityTester<ColorInterpolation> mutabilityTester =
        new MutabilityTester<ColorInterpolation>(new Creator<ColorInterpolation>() {

          @Override
          public ColorInterpolation create() {
            return statementFactory.createColorInterpolation(isEnabled);
          }

          @Override
          public ColorInterpolation copy(ColorInterpolation o) {
            return statementFactory.copyColorInterpolation(mutable(o));
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
