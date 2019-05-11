package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.NormalVertex;
import com.ht.wfp3.api.statement.StatementFactory;

public class NormalVertexAcceptanceTests {

  private static final String NORMAL_VERTEX_KEYWORD = "vn";

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

  @Test(expected = NullPointerException.class)
  public void NormalVertex_createNormalVertexWithNullICoord_nullPointerExceptionIsThrown() {
    statementFactory.createNormalVertex(null, BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d));
  }

  @Test(expected = NullPointerException.class)
  public void NormalVertex_createNormalVertexWithNullJCoord_nullPointerExceptionIsThrown() {
    statementFactory.createNormalVertex(BigDecimal.valueOf(1.1d), null, BigDecimal.valueOf(3.3d));
  }

  @Test(expected = NullPointerException.class)
  public void NormalVertex_createNormalVertexWithNullKCoord_nullPointerExceptinIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void NoramlVertex_copyNormalVertexWithNullParameter_nullPointerExceptionIsThrown() {
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

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
