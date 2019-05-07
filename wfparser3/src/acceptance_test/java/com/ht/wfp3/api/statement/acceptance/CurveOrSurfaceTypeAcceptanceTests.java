package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.CurveOrSurfaceType;
import com.ht.wfp3.api.statement.StatementFactory;

public class CurveOrSurfaceTypeAcceptanceTests {

  private static final String CSTYPE_KEYWORD = "cstype";
  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void CurveOrSurfaceType_createCurveOrSurfaceTypeWithNullTypeKeyParameter_nullPointerExceptionIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void CurveOrSurfaceType_copyCurveOrSurfaceTypeWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyCurveOrSurfaceType(null);
  }

  @Test
  public void CurveOrSurfaceType_copyValidCurveOrSurfaceType_validCurveOrSurfaceTypeCopied() {
    boolean isRational = true;
    CurveOrSurfaceType.Key typeKey = CurveOrSurfaceType.Key.CARDINAL;
    CurveOrSurfaceType originalCurveOrSurfaceType =
        statementFactory.createCurveOrSurface(isRational, typeKey);
    
    CurveOrSurfaceType copiedCurveOrSurfaceType = statementFactory.copyCurveOrSurfaceType(originalCurveOrSurfaceType);

    assertNotNull(copiedCurveOrSurfaceType);
    assertEquals(CSTYPE_KEYWORD, copiedCurveOrSurfaceType.getKeyword());
    assertEquals(isRational, copiedCurveOrSurfaceType.isRational());
    assertEquals(typeKey, copiedCurveOrSurfaceType.getTypeKey());  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }
}
