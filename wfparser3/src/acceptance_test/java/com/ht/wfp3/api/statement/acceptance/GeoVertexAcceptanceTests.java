package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.GeoVertex;
import com.ht.wfp3.api.statement.StatementFactory;

public class GeoVertexAcceptanceTests {

  private static final Object GEO_VERTEX_KEYWORD = "v";
  private StatementFactory statementFactory;

  private void assertValidGeoVertex(BigDecimal expectedXCoord, BigDecimal expectedYCoord,
      BigDecimal expectedZCoord, BigDecimal expectedWCoord, GeoVertex geoVertex) {
    assertNotNull(geoVertex);
    assertEquals(GEO_VERTEX_KEYWORD, geoVertex.getKeyword());
    assertEquals(expectedXCoord, geoVertex.getXCoord());
    assertEquals(expectedYCoord, geoVertex.getYCoord());
    assertEquals(expectedZCoord, geoVertex.getZCoord());
    assertEquals(expectedWCoord, geoVertex.getWCoord());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void GeoVertex_createGeoVertexWithNullXCoord_nullPointerExceptionIsThrown() {
    statementFactory.createGeoVertex(null, BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d),
        BigDecimal.valueOf(4.4d));
  }

  @Test(expected = NullPointerException.class)
  public void GeoVertex_createGeoVertexWithNullYCoord_nullPointerExceptionIsThrown() {
    statementFactory.createGeoVertex(BigDecimal.valueOf(1.1d), null, BigDecimal.valueOf(3.3d),
        BigDecimal.valueOf(4.4d));
  }

  @Test(expected = NullPointerException.class)
  public void GeoVertex_createGeoVertexWithNullZCoord_nullPointerExceptionIsThrown() {
    statementFactory.createGeoVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d), null,
        BigDecimal.valueOf(4.4d));
  }

  @Test(expected = NullPointerException.class)
  public void GeoVertex_createGeoVertexWithNullWCoord_nullPointerExceptionIsThrown() {
    statementFactory.createGeoVertex(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d),
        BigDecimal.valueOf(3.3d), null);
  }

  @Test
  public void GeoVertex_createGeoVertexWithValidXYZAndWCoords_geoVertexIsCreated() {
    BigDecimal xCoord = BigDecimal.valueOf(1000.1d);
    BigDecimal yCoord = BigDecimal.valueOf(2000.2d);
    BigDecimal zCoord = BigDecimal.valueOf(3000.3d);
    BigDecimal wCoord = BigDecimal.valueOf(4000.4d);

    GeoVertex geoVertex = statementFactory.createGeoVertex(xCoord, yCoord, zCoord, wCoord);

    assertValidGeoVertex(xCoord, yCoord, zCoord, wCoord, geoVertex);
  }

  @Test
  public void GeoVertex_createGeoVertexWithValidXYAndZCoords_geoVertexIsCreated() {
    BigDecimal xCoord = BigDecimal.valueOf(1000.1d);
    BigDecimal yCoord = BigDecimal.valueOf(2000.2d);
    BigDecimal zCoord = BigDecimal.valueOf(3000.3d);
    BigDecimal expectedWCoord = BigDecimal.ONE;

    GeoVertex geoVertex = statementFactory.createGeoVertex(xCoord, yCoord, zCoord);

    assertValidGeoVertex(xCoord, yCoord, zCoord, expectedWCoord, geoVertex);
  }

  @Test(expected = NullPointerException.class)
  public void GeoVertex_copyGeoVertexWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyGeoVertex(null);
  }

  @Test
  public void GeoVertex_copyGeoVertex_geoVertexIsCopied() {
    BigDecimal xCoord = BigDecimal.valueOf(1000.1d);
    BigDecimal yCoord = BigDecimal.valueOf(2000.2d);
    BigDecimal zCoord = BigDecimal.valueOf(3000.3d);
    BigDecimal expectedWCoord = BigDecimal.ONE;
    GeoVertex originalGeoVertex = statementFactory.createGeoVertex(xCoord, yCoord, zCoord);

    GeoVertex copiedGeoVertex = statementFactory.copyGeoVertex(originalGeoVertex);

    assertValidGeoVertex(xCoord, yCoord, zCoord, expectedWCoord, copiedGeoVertex);
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
