package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.ParamVertex;
import com.ht.wfp3.api.statement.StatementFactory;

public class ParamVertexAcceptanceTests {

  private static final String PARAM_VERTEX_KEYWORD = "vp";

  private StatementFactory statementFactory;


  private void assertValidParamVertexBase(ParamVertex paramVertex) {
    assertNotNull(paramVertex);
    assertEquals(PARAM_VERTEX_KEYWORD, paramVertex.getKeyword());
  }

  private void assertValidParamVertex(BigDecimal uCoord, ParamVertex paramVertex) {
    assertValidParamVertexBase(paramVertex);
    assertEquals(uCoord, paramVertex.getUCoord());
  }

  private void assertValidParamVertex(BigDecimal uCoord, BigDecimal vCoord,
      ParamVertex paramVertex) {
    assertValidParamVertex(uCoord, paramVertex);
    assertEquals(vCoord, paramVertex.getVCoord());
  }

  private void assertValidParamVertex(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord,
      ParamVertex paramVertex) {
    assertValidParamVertex(uCoord, vCoord, paramVertex);
    assertEquals(wCoord, paramVertex.getWCoord());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void ParamVertex_createParamVertexWithNullUCoord_nullPointerExceptionIsThrown() {
    statementFactory.createParamVertex(null, BigDecimal.valueOf(2.2d), BigDecimal.valueOf(-3.3d));
  }

  @Test(expected = NullPointerException.class)
  public void ParamVertex_createParamVertexWithNullVCoord_nullPointerExceptionIsThrown() {
    statementFactory.createParamVertex(BigDecimal.valueOf(-1.1d), null,
        BigDecimal.valueOf(333.33d));
  }

  @Test(expected = NullPointerException.class)
  public void ParamVertex_createParamVertexWithNullWCoord_nullPointerExceptionIsThrown() {
    statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(-34.87d), null);
  }

  @Test
  public void ParamVertex_createParamVertexWithJustUCoord_paramVertexIsCreated() {
    BigDecimal uCoord = BigDecimal.valueOf(33.3d);

    ParamVertex paramVertex = statementFactory.createParamVertex(uCoord);

    assertValidParamVertex(uCoord, paramVertex);
  }

  @Test
  public void ParamVertex_createParamVertexWithJusUAndVCoords_paramVertexIsCreated() {
    BigDecimal uCoord = BigDecimal.valueOf(99.99d);
    BigDecimal vCoord = BigDecimal.valueOf(-55.123d);

    ParamVertex paramVertex = statementFactory.createParamVertex(uCoord, vCoord);

    assertValidParamVertex(uCoord, vCoord, paramVertex);
  }

  @Test
  public void ParamVertex_createParamVertexWithUVAndWCoords_paramVertexIsCreated() {
    BigDecimal uCoord = BigDecimal.valueOf(99.99d);
    BigDecimal vCoord = BigDecimal.valueOf(-55.123d);
    BigDecimal wCoord = BigDecimal.valueOf(7777777.123498d);

    ParamVertex paramVertex = statementFactory.createParamVertex(uCoord, vCoord, wCoord);

    assertValidParamVertex(uCoord, vCoord, wCoord, paramVertex);
  }

  @Test(expected = NullPointerException.class)
  public void ParamVertex_copyParamVertexWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyParamVertex(null);
  }

  @Test
  public void ParamVertex_copyParamVertexWithOnlyUCoord_paramVertexIsCopied() {
    BigDecimal uCoord = BigDecimal.valueOf(33.3d);
    ParamVertex originalParamVertex = statementFactory.createParamVertex(uCoord);

    ParamVertex copiedParamVertex = statementFactory.copyParamVertex(originalParamVertex);

    assertValidParamVertex(uCoord, copiedParamVertex);
  }

  @Test
  public void ParamVertex_copyParamVertexWithOnlyUAndVCoords_paramVertexIsCopied() {
    BigDecimal uCoord = BigDecimal.valueOf(99.99d);
    BigDecimal vCoord = BigDecimal.valueOf(-55.123d);
    ParamVertex originalParamVertex = statementFactory.createParamVertex(uCoord, vCoord);

    ParamVertex copiedParamVertex = statementFactory.copyParamVertex(originalParamVertex);

    assertValidParamVertex(uCoord, vCoord, copiedParamVertex);
  }

  @Test
  public void ParamVertex_copyParamVertexWithUVAndWCoords_paramVertexIsCopied() {
    BigDecimal uCoord = BigDecimal.valueOf(99.99d);
    BigDecimal vCoord = BigDecimal.valueOf(-55.123d);
    BigDecimal wCoord = BigDecimal.valueOf(7777777.123498d);
    ParamVertex originalParamVertex = statementFactory.createParamVertex(uCoord, vCoord, wCoord);

    ParamVertex copiedParamVertex = statementFactory.copyParamVertex(originalParamVertex);

    assertValidParamVertex(uCoord, vCoord, wCoord, copiedParamVertex);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void ParamVertex_accessVCoordWhenNotSet_unsupportedOperationExceptionIsThrown() {
    BigDecimal uCoord = BigDecimal.valueOf(33.3d);

    ParamVertex paramVertex = statementFactory.createParamVertex(uCoord);

    assertValidParamVertex(uCoord, paramVertex);

    paramVertex.getVCoord();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void ParamVertex_accessWCoordWhenNotSet_unsupportedOperationExceptionIsThrown() {
    BigDecimal uCoord = BigDecimal.valueOf(99.99d);
    BigDecimal vCoord = BigDecimal.valueOf(-55.123d);

    ParamVertex paramVertex = statementFactory.createParamVertex(uCoord, vCoord);

    assertValidParamVertex(uCoord, vCoord, paramVertex);

    paramVertex.getWCoord();
  }

  @Test
  public void ParamVertex_createParamVertexWithDefaultWParameter_paramVertexIsCreated() {
    BigDecimal uCoord = BigDecimal.valueOf(99.99d);
    BigDecimal vCoord = BigDecimal.valueOf(-55.123d);
    BigDecimal expectedWCoord = ParamVertex.DEFAULT_W_COORD;

    ParamVertex paramVertex = statementFactory.createParamVertexWithDefaultWCoord(uCoord, vCoord);

    assertValidParamVertex(uCoord, vCoord, expectedWCoord, paramVertex);
  }

  @Test
  public void ParamVertex_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    ParamVertex first;
    ParamVertex second;

    // Only uCoord

    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d));
    second = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different uCoord
    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d));
    second = statementFactory.createParamVertex(BigDecimal.valueOf(12345.1d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // uCoord and vCoord

    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d));
    second = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different uCoord
    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d));
    second =
        statementFactory.createParamVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(2.2d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different vCoord
    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d));
    second =
        statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(3.50d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // uCoord, vCoord, and wCoord

    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d));
    second = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different uCoord
    first = statementFactory.createParamVertex(BigDecimal.valueOf(111.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d));
    second = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different vCoord
    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(-2.2d),
        BigDecimal.valueOf(3.3d));
    second = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different wCoord
    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(673.3d));
    second = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different number of parameters.
    first = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d));
    second = statementFactory.createParamVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
