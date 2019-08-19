package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.CurveOrSurfaceType;
import com.ht.wfp3.api.statement.CurveOrSurfaceType.Key;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;

public class CurveOrSurfaceTypeAcceptanceTests {
  private static final String CSTYPE_KEYWORD = "cstype";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void CurveOrSurfaceType_createCurveOrSurfaceTypeWithNullTypeKeyParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("key cannot be null");

    statementFactory.createCurveOrSurface(false, null);
  }

  @Test
  public void CurveOrSurfaceType_createCurveOrSurfaceTypeWithValidParameters_validCurveOrSurfaceTypeIsCreated() {
    boolean isRational = true;
    CurveOrSurfaceType.Key typeKey = CurveOrSurfaceType.Key.CARDINAL;

    CurveOrSurfaceType curveOrSurfaceType =
        statementFactory.createCurveOrSurface(isRational, typeKey);

    assertNotNull(curveOrSurfaceType);
    assertEquals(CSTYPE_KEYWORD, curveOrSurfaceType.getKeyword());
    assertEquals(isRational, curveOrSurfaceType.isRational());
    assertEquals(typeKey, curveOrSurfaceType.getTypeKey());
  }

  @Test
  public void CurveOrSurfaceType_copyCurveOrSurfaceTypeWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("cstype cannot be null");

    statementFactory.copyCurveOrSurfaceType(null);
  }

  @Test
  public void CurveOrSurfaceType_copyValidCurveOrSurfaceType_validCurveOrSurfaceTypeCopied() {
    boolean isRational = true;
    CurveOrSurfaceType.Key typeKey = CurveOrSurfaceType.Key.CARDINAL;
    CurveOrSurfaceType originalCurveOrSurfaceType =
        statementFactory.createCurveOrSurface(isRational, typeKey);

    CurveOrSurfaceType copiedCurveOrSurfaceType =
        statementFactory.copyCurveOrSurfaceType(originalCurveOrSurfaceType);

    assertNotNull(copiedCurveOrSurfaceType);
    assertEquals(CSTYPE_KEYWORD, copiedCurveOrSurfaceType.getKeyword());
    assertEquals(isRational, copiedCurveOrSurfaceType.isRational());
    assertEquals(typeKey, copiedCurveOrSurfaceType.getTypeKey());
  }

  @Test
  public void CurveOrSurfaceType_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    CurveOrSurfaceType first;
    CurveOrSurfaceType second;

    first = statementFactory.createCurveOrSurface(false, Key.BEZIER);
    second = statementFactory.createCurveOrSurface(false, Key.BEZIER);
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: isRational different
    first = statementFactory.createCurveOrSurface(true, Key.BEZIER);
    second = statementFactory.createCurveOrSurface(false, Key.BEZIER);
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: typeKey different
    first = statementFactory.createCurveOrSurface(true, Key.BEZIER);
    second = statementFactory.createCurveOrSurface(true, Key.TAYLOR);
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
