package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Curve2D;
import com.ht.wfp3.api.statement.ParamVertexReference;
import com.ht.wfp3.api.statement.StatementFactory;

public class Curve2DAcceptanceTests {

  private static final Object CURVE2D_KEYWORD = "curv2";
  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void Curve2D_createCurve2DWithNullControlPointVertexReferenceListParameter_nullPointerExceptionIsThrown() {
    statementFactory.createCurve2D(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Curve2D_createCurve2DWithEmptyControlPointVertexReferenceListParameter_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurve2D(Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Curve2D_createCurve2DWithOneControlPointInVertexReferenceListParameter_illegalArgumentExceptionIsThrown() {
    statementFactory.createCurve2D(Arrays.asList(statementFactory.createParamVertexReference(1)));
  }

  @Test
  public void Curve2D_createCurve2DWithTwoControlPointsInVertexReferenceListParameter_validCurve2DIsCreated() {
    List<ParamVertexReference> controlPointerVertexReferenceList =
        Arrays.asList(statementFactory.createParamVertexReference(1),
            statementFactory.createParamVertexReference(2));

    Curve2D curve2D = statementFactory.createCurve2D(controlPointerVertexReferenceList);

    assertNotNull(curve2D);
    assertEquals(CURVE2D_KEYWORD, curve2D.getKeyword());
    assertEquals(controlPointerVertexReferenceList, curve2D.getControlPointVertexReferences());
  }

  @Test(expected = NullPointerException.class)
  public void Curve2D_copyCurve2DWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyCurve2D(null);
  }

  @Test
  public void Curve2D_copyValidCurve2D_validCurve2DIsCopied() {
    List<ParamVertexReference> controlPointerVertexReferenceList =
        Arrays.asList(statementFactory.createParamVertexReference(1),
            statementFactory.createParamVertexReference(2));
    Curve2D originalCurve2D = statementFactory.createCurve2D(controlPointerVertexReferenceList);
    
    Curve2D copiedCurve2D = statementFactory.copyCurve2D(originalCurve2D);

    assertNotNull(copiedCurve2D);
    assertEquals(CURVE2D_KEYWORD, copiedCurve2D.getKeyword());
    assertEquals(controlPointerVertexReferenceList, copiedCurve2D.getControlPointVertexReferences());
  }

  @Test
  public void Curve2D_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    fail("Not yet implemented");
  }

  @Test
  public void Curve2D_copyCurve2DWithMaliciousMutableCurve2DParameter_validImmutableCurve2DIsCreated() {
    fail("Not yet implemented");
  }
}
