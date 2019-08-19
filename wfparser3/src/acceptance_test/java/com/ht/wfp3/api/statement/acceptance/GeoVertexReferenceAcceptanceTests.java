package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.GeoVertexReference;
import com.ht.wfp3.api.statement.StatementFactory;

public class GeoVertexReferenceAcceptanceTests {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void GeoVertexReference_createGeoVertexReferenceWithNullVertexIndex_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("vertexIndex cannot be null");

    statementFactory.createGeoVertexReference(null);
  }

  @Test
  public void GeoVertexReference_createGeoVertexReferenceWithZeroValueVertexIndex_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexIndex cannot equal 0");

    statementFactory.createGeoVertexReference(Integer.valueOf(0));
  }

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

  @Test
  public void GeoVertexReference_copyGeoVertexReferenceWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("geoVertexReference cannot be null");

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

  @Test
  public void GeoVertexReference_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    GeoVertexReference first;
    GeoVertexReference second;

    first = statementFactory.createGeoVertexReference(Integer.valueOf(1));
    second = statementFactory.createGeoVertexReference(Integer.valueOf(1));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    first = statementFactory.createGeoVertexReference(Integer.valueOf(1));
    second = statementFactory.createGeoVertexReference(Integer.valueOf(-1));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO you need tests for when the vertex reference is NOT set. Might require some refactoring.
  // TODO copy malicious mutable statement.
}
