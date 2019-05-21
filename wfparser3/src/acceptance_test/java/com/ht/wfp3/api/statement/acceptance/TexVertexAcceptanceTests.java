package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.TexVertex;

public class TexVertexAcceptanceTests {

  private static final String TEX_VERTEX_KEYWORD = "vt";

  private StatementFactory statementFactory;

  private void assertValidTexVertex(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord,
      TexVertex texVertex) {
    assertNotNull(texVertex);
    assertEquals(TEX_VERTEX_KEYWORD, texVertex.getKeyword());
    assertEquals(uCoord, texVertex.getUCoord());
    assertEquals(vCoord, texVertex.getVCoord());
    assertEquals(wCoord, texVertex.getWCoord());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void TexVertex_createTexVertexWithNullUCoord_nullPointerExceptionIsThrown() {
    statementFactory.createTexVertex(null, BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d));
  }

  @Test(expected = NullPointerException.class)
  public void TexVertex_createTexVertexWithNullVCoord_nullPointerExceptionIsThrown() {
    statementFactory.createTexVertex(BigDecimal.valueOf(1.1d), null, BigDecimal.valueOf(3.3d));
  }

  @Test(expected = NullPointerException.class)
  public void TexVertex_createTexVertexWithNullWCoord_nullPointerExceptionIsThrown() {
    statementFactory.createTexVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d), null);
  }

  @Test
  public void TexVertex_createTexVertexWithUVAndWCoords_texVertexIsCreated() {
    BigDecimal uCoord = BigDecimal.valueOf(1.1d);
    BigDecimal vCoord = BigDecimal.valueOf(-2.2d);
    BigDecimal wCoord = BigDecimal.valueOf(0.0000033d);

    TexVertex texVertex = statementFactory.createTexVertex(uCoord, vCoord, wCoord);

    assertValidTexVertex(uCoord, vCoord, wCoord, texVertex);
  }

  @Test
  public void TexVertex_createTexVertexWithUAndVCoords_texVertexIsCreated() {
    BigDecimal uCoord = BigDecimal.valueOf(1.1d);
    BigDecimal vCoord = BigDecimal.valueOf(-2.2d);
    BigDecimal expectedWCoord = TexVertex.DEFAULT_W_COORD;

    TexVertex texVertex = statementFactory.createTexVertexWithDefaultWCoord(uCoord, vCoord);

    assertValidTexVertex(uCoord, vCoord, expectedWCoord, texVertex);
  }

  @Test
  public void TexVertex_createTexVertexWithUCoord_texVertexIsCreated() {
    BigDecimal uCoord = BigDecimal.valueOf(1.1d);
    BigDecimal expectedVCoord = TexVertex.DEFAULT_V_COORD;
    BigDecimal expectedWCoord = TexVertex.DEFAULT_W_COORD;

    TexVertex texVertex = statementFactory.createTexVertexWithDefaultVAndWCoords(uCoord);

    assertValidTexVertex(uCoord, expectedVCoord, expectedWCoord, texVertex);
  }

  @Test(expected = NullPointerException.class)
  public void TexVertex_copyTexVertexWithNullParameter_texVertexIsCreated() {
    statementFactory.copyTexVertex(null);
  }

  @Test
  public void TexVertex_copyTexVertex_texVertexIsCopied() {
    BigDecimal uCoord = BigDecimal.valueOf(1.1d);
    BigDecimal vCoord = BigDecimal.valueOf(-2.2d);
    BigDecimal wCoord = BigDecimal.valueOf(0.0000033d);
    TexVertex originalTexVertex = statementFactory.createTexVertex(uCoord, vCoord, wCoord);

    TexVertex copiedTexVertex = statementFactory.copyTexVertex(originalTexVertex);

    assertValidTexVertex(uCoord, vCoord, wCoord, copiedTexVertex);
  }

  @Test
  public void TexVertex_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    TexVertex first;
    TexVertex second;

    // just uCoord

    first = statementFactory.createTexVertexWithDefaultVAndWCoords(BigDecimal.valueOf(-1.1d));
    second = statementFactory.createTexVertexWithDefaultVAndWCoords(BigDecimal.valueOf(-1.1d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different uCoord
    first = statementFactory.createTexVertexWithDefaultVAndWCoords(BigDecimal.valueOf(-1.5d));
    second = statementFactory.createTexVertexWithDefaultVAndWCoords(BigDecimal.valueOf(-1.1d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // uCoord and vCoord

    first = statementFactory.createTexVertexWithDefaultWCoord(BigDecimal.valueOf(-1.1d),
        BigDecimal.valueOf(-2.2d));
    second = statementFactory.createTexVertexWithDefaultWCoord(BigDecimal.valueOf(-1.1d),
        BigDecimal.valueOf(-2.2d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different uCoord
    first = statementFactory.createTexVertexWithDefaultWCoord(BigDecimal.valueOf(-1.1d),
        BigDecimal.valueOf(-2.2d));
    second = statementFactory.createTexVertexWithDefaultWCoord(BigDecimal.valueOf(-1.0d),
        BigDecimal.valueOf(-2.2d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different vCoord
    first = statementFactory.createTexVertexWithDefaultWCoord(BigDecimal.valueOf(-1.1d),
        BigDecimal.valueOf(2.2d));
    second = statementFactory.createTexVertexWithDefaultWCoord(BigDecimal.valueOf(-1.1d),
        BigDecimal.valueOf(-2.2d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // uCoord, vCoord, and wCoord

    first = statementFactory.createTexVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(-2.2d),
        BigDecimal.valueOf(-3.3d));
    second = statementFactory.createTexVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(-2.2d),
        BigDecimal.valueOf(-3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different uCoord
    first = statementFactory.createTexVertex(BigDecimal.valueOf(-1.0d), BigDecimal.valueOf(-2.2d),
        BigDecimal.valueOf(-3.3d));
    second = statementFactory.createTexVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(-2.2d),
        BigDecimal.valueOf(-3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different vCoord
    first = statementFactory.createTexVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(-2.2d),
        BigDecimal.valueOf(-3.3d));
    second = statementFactory.createTexVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(-3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different wCoord
    first = statementFactory.createTexVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(-2.2d),
        BigDecimal.valueOf(-3.3d));
    second = statementFactory.createTexVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(-2.2d),
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
