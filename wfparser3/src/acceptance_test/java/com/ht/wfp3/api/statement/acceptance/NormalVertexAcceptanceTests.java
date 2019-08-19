package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.NormalVertex;
import com.ht.wfp3.api.statement.StatementFactory;

public class NormalVertexAcceptanceTests {
  private static final String NORMAL_VERTEX_KEYWORD = "vn";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidNormalVertex(BigDecimal iCoord, BigDecimal jCoord, BigDecimal kCoord,
      NormalVertex normalVertex) {
    assertNotNull(normalVertex);
    assertEquals(NORMAL_VERTEX_KEYWORD, normalVertex.getKeyword());
    assertEquals(iCoord, normalVertex.getICoord());
    assertEquals(jCoord, normalVertex.getJCoord());
    assertEquals(kCoord, normalVertex.getKCoord());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void NormalVertex_createNormalVertexWithNullICoord_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("iCoord cannot be null");

    statementFactory.createNormalVertex(null, BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d));
  }

  @Test
  public void NormalVertex_createNormalVertexWithNullJCoord_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("jCoord cannot be null");

    statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), null, BigDecimal.valueOf(3.3d));
  }

  @Test
  public void NormalVertex_createNormalVertexWithNullKCoord_nullPointerExceptinIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("kCoord cannot be null");

    statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d), null);
  }

  @Test
  public void NormalVertex_createNormalVertexWithValidCoords_normalVertexIsCreated() {
    BigDecimal iCoord = BigDecimal.valueOf(-1.1d);
    BigDecimal jCoord = BigDecimal.valueOf(0);
    BigDecimal kCoord = BigDecimal.valueOf(3.3d);

    NormalVertex normalVertex = statementFactory.createNormalVertex(iCoord, jCoord, kCoord);

    assertValidNormalVertex(iCoord, jCoord, kCoord, normalVertex);
  }

  @Test
  public void NoramlVertex_copyNormalVertexWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("normalVertex cannot be null");

    statementFactory.copyNormalVertex(null);
  }

  @Test
  public void NoramlVertex_copyNormalVertex_normalVertexIsCopied() {
    BigDecimal iCoord = BigDecimal.valueOf(-1.1d);
    BigDecimal jCoord = BigDecimal.valueOf(0);
    BigDecimal kCoord = BigDecimal.valueOf(3.3d);
    NormalVertex originalNormalVertex = statementFactory.createNormalVertex(iCoord, jCoord, kCoord);

    NormalVertex copiedNormalVertex = statementFactory.copyNormalVertex(originalNormalVertex);

    assertValidNormalVertex(iCoord, jCoord, kCoord, copiedNormalVertex);
  }

  @Test
  public void NormalVertex_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    NormalVertex first;
    NormalVertex second;

    first = statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(-3.3d));
    second = statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(-3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different iCoord
    first = statementFactory.createNormalVertex(BigDecimal.valueOf(-1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(-3.3d));
    second = statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(-3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different jCoord
    first = statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(-3.3d));
    second = statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(-2.2d), BigDecimal.valueOf(-3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different kCoord
    first = statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(-3.3d));
    second = statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
