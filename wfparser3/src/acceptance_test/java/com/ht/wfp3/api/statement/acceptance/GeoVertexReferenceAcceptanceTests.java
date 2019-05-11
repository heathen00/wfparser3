package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.GeoVertexReference;
import com.ht.wfp3.api.statement.StatementFactory;

public class GeoVertexReferenceAcceptanceTests {

  private StatementFactory statementFactory;


  private void assertValidGeoVertexReference(boolean isSet, Integer vertexIndex,
      GeoVertexReference geoVertexReference) {
    assertNotNull(geoVertexReference);
    assertEquals(vertexIndex, geoVertexReference.getVertexIndex());
    assertEquals(isSet, geoVertexReference.isSet());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void GeoVertexReference_createGeoVertexReferenceWithNullVertexIndex_nullPointerExceptionIsThrown() {
    statementFactory.createGeoVertexReference(null);
  }

  // @Test(expected = IllegalArgumentException.class)
  // public void
  // GeoVertexReference_createGeoVertexReferenceWithZeroValueVertexIndex_illegalArgumentExceptionIsThrown()
  // {
  // statementFactory.createGeoVertexReference(Integer.valueOf(0));
  // }

  @Test
  public void GeoVertexReference_createGeoVertexReferenceWithVertexIndexLessThanZero_geoVertexReferenceIsCreated() {
    boolean expectedIsSet = true;
    Integer vertexIndex = Integer.valueOf(-2345);

    GeoVertexReference geoVertexReference = statementFactory.createGeoVertexReference(vertexIndex);

    assertValidGeoVertexReference(expectedIsSet, vertexIndex, geoVertexReference);
  }

  @Test
  public void GeoVertexReference_createGeoVertexReferenceWithVertexIndexGreaterThanZero_geoVertexReferenceIsCreated() {
    boolean expectedIsSet = true;
    Integer vertexIndex = Integer.valueOf(350);

    GeoVertexReference geoVertexReference = statementFactory.createGeoVertexReference(vertexIndex);

    assertValidGeoVertexReference(expectedIsSet, vertexIndex, geoVertexReference);
  }

  @Test(expected = NullPointerException.class)
  public void GeoVertexReference_copyGeoVertexReferenceWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyGeoVertexReference(null);
  }

  @Test
  public void GeoVertexReference_copyGeoVertexReference_geoVertexReferenceIsCopied() {
    boolean expectedIsSet = true;
    Integer vertexIndex = Integer.valueOf(-99);
    GeoVertexReference originalGeoVertexReference =
        statementFactory.createGeoVertexReference(vertexIndex);

    GeoVertexReference copiedGeoVertexReference =
        statementFactory.copyGeoVertexReference(originalGeoVertexReference);

    assertValidGeoVertexReference(expectedIsSet, vertexIndex, copiedGeoVertexReference);
  }

  // TODO you need tests for when the vertex reference is NOT set.  Might require some refactoring.
  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}