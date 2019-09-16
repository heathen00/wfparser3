package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.GeoVertexReference;
import com.nickmlanglois.wfp3.api.statement.NormalVertexReference;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.TexVertexReference;
import com.nickmlanglois.wfp3.api.statement.VertexReferenceGroup;
import com.nickmlanglois.wfp3.api.statement.VertexReferenceGroupBuilder;

public class VertexReferenceGroupAcceptanceTests {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;
  private VertexReferenceGroupBuilder vertexReferenceGroupBuilder;

  private void assertExpectedIsSetValues(boolean expectedIsGeoRefSet, boolean expectedIsTexRefSet,
      boolean expectedIsNormalRefSet, VertexReferenceGroup vertexReferenceGroup) {
    assertEquals(expectedIsGeoRefSet, vertexReferenceGroup.getGeoVertexRef().isSet());
    assertEquals(expectedIsTexRefSet, vertexReferenceGroup.isTexVertexRefSet());
    assertEquals(expectedIsNormalRefSet, vertexReferenceGroup.isNormalVertexRefSet());
  }

  private void assertValidVertexReferenceGroup(GeoVertexReference geoVertexReference,
      VertexReferenceGroup vertexReferenceGroup) {
    assertNotNull(vertexReferenceGroup);
    assertEquals(geoVertexReference.getVertexIndex(),
        vertexReferenceGroup.getGeoVertexRef().getVertexIndex());
    assertExpectedIsSetValues(true, false, false, vertexReferenceGroup);
  }

  private void assertValidVertexReferenceGroup(GeoVertexReference geoVertexReference,
      TexVertexReference texVertexReference, VertexReferenceGroup vertexReferenceGroup) {
    assertNotNull(vertexReferenceGroup);
    assertEquals(geoVertexReference.getVertexIndex(),
        vertexReferenceGroup.getGeoVertexRef().getVertexIndex());
    assertEquals(texVertexReference.getVertexIndex(),
        vertexReferenceGroup.getTexVertexRef().getVertexIndex());
    assertExpectedIsSetValues(true, true, false, vertexReferenceGroup);

  }

  private void assertValidVertexReferenceGroup(GeoVertexReference geoVertexReference,
      TexVertexReference texVertexReference, NormalVertexReference normalVertexReference,
      VertexReferenceGroup vertexReferenceGroup) {
    assertNotNull(vertexReferenceGroup);
    assertEquals(geoVertexReference.getVertexIndex(),
        vertexReferenceGroup.getGeoVertexRef().getVertexIndex());
    assertEquals(texVertexReference.getVertexIndex(),
        vertexReferenceGroup.getTexVertexRef().getVertexIndex());
    assertEquals(normalVertexReference.getVertexIndex(),
        vertexReferenceGroup.getNormalVertexRef().getVertexIndex());
    assertExpectedIsSetValues(true, true, true, vertexReferenceGroup);
  }

  private void assertValidVertexReferenceGroup(GeoVertexReference geoVertexReference,
      NormalVertexReference normalVertexReference, VertexReferenceGroup vertexReferenceGroup) {
    assertNotNull(vertexReferenceGroup);
    assertEquals(geoVertexReference.getVertexIndex(),
        vertexReferenceGroup.getGeoVertexRef().getVertexIndex());
    assertEquals(normalVertexReference.getVertexIndex(),
        vertexReferenceGroup.getNormalVertexRef().getVertexIndex());
    assertExpectedIsSetValues(true, false, true, vertexReferenceGroup);
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
    vertexReferenceGroupBuilder = statementFactory.createVertexReferenceGroupBuilder();
  }

  @Test
  public void VertexReferenceGroup_createVertexReferenceGroupBuilder_vertexReferenceGroupBuilderIsCreated() {
    assertNotNull(vertexReferenceGroupBuilder);
  }

  @Test
  public void VertexReferenceGroup_geoVertexIsNull_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("geometricVertexReferenceNumber cannot be null");

    vertexReferenceGroupBuilder.geoVertexRef(null);
  }

  @Test
  public void VertexReferenceGroup_texVertexIsNull_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("textureVertexReferenceNumber cannot be null");

    vertexReferenceGroupBuilder.texVertexRef(null);
  }

  @Test
  public void VertexReferenceGroup_normalVertexIsNull_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("normalVertexReferenceNumber cannot be null");

    vertexReferenceGroupBuilder.normalVertexRef(null);
  }

  @Test
  public void VertexReferenceGroup_clearVertexReferenceGroupAndBuild_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("geometricVertexReferenceNumber must be set");

    vertexReferenceGroupBuilder.clear().build();
  }

  @Test
  public void VertexReferenceGroup_setGeoVertexAndBuild_vertexReferenceGroupIsBuilt() {
    Integer geoVertexIndex = Integer.valueOf(1);
    GeoVertexReference geoVertexReference =
        statementFactory.createGeoVertexReference(geoVertexIndex);

    VertexReferenceGroup vertexReferenceGroup =
        vertexReferenceGroupBuilder.clear().geoVertexRef(geoVertexIndex).build();

    assertValidVertexReferenceGroup(geoVertexReference, vertexReferenceGroup);
  }

  @Test
  public void VertexReferenceGroup_setGeoVertexAndTexVertexAndBuild_vertexReferenceGroupIsBuilt() {
    Integer geoVertexIndex = Integer.valueOf(1);
    Integer texVertexIndex = Integer.valueOf(-99);
    GeoVertexReference geoVertexReference =
        statementFactory.createGeoVertexReference(geoVertexIndex);
    TexVertexReference texVertexReference =
        statementFactory.createTexVertexReference(texVertexIndex);

    VertexReferenceGroup vertexReferenceGroup = vertexReferenceGroupBuilder.clear()
        .geoVertexRef(geoVertexIndex).texVertexRef(texVertexIndex).build();

    assertValidVertexReferenceGroup(geoVertexReference, texVertexReference, vertexReferenceGroup);
  }

  @Test
  public void VertexReferenceGroup_setGeoVertexTexVertexAndNormalVertexAndBuild_vertexReferenceGroupIsBuilt() {
    Integer geoVertexIndex = Integer.valueOf(1);
    Integer texVertexIndex = Integer.valueOf(-99);
    Integer normalVertexIndex = Integer.valueOf(56);
    GeoVertexReference geoVertexReference =
        statementFactory.createGeoVertexReference(geoVertexIndex);
    TexVertexReference texVertexReference =
        statementFactory.createTexVertexReference(texVertexIndex);
    NormalVertexReference normalVertexReference =
        statementFactory.createNormalVertexReference(normalVertexIndex);

    VertexReferenceGroup vertexReferenceGroup =
        vertexReferenceGroupBuilder.clear().geoVertexRef(geoVertexIndex)
            .texVertexRef(texVertexIndex).normalVertexRef(normalVertexIndex).build();

    assertValidVertexReferenceGroup(geoVertexReference, texVertexReference, normalVertexReference,
        vertexReferenceGroup);
  }

  @Test
  public void VertexReferenceGroup_setGeoVertexAndNormalVertexAndBuild_vertexReferenceGroupIsBuilt() {
    Integer geoVertexIndex = Integer.valueOf(1);
    Integer normalVertexIndex = Integer.valueOf(56);
    GeoVertexReference geoVertexReference =
        statementFactory.createGeoVertexReference(geoVertexIndex);
    NormalVertexReference normalVertexReference =
        statementFactory.createNormalVertexReference(normalVertexIndex);

    VertexReferenceGroup vertexReferenceGroup = vertexReferenceGroupBuilder.clear()
        .geoVertexRef(geoVertexIndex).normalVertexRef(normalVertexIndex).build();

    assertValidVertexReferenceGroup(geoVertexReference, normalVertexReference,
        vertexReferenceGroup);
  }

  @Test
  public void VertexReferenceGroup_setTexVertexAndBuild_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("geometricVertexReferenceNumber must be set");

    vertexReferenceGroupBuilder.clear().texVertexRef(Integer.valueOf(33)).build();
  }

  @Test
  public void VertexReferenceGroup_setNormalVertexAndBuild_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("geometricVertexReferenceNumber must be set");

    vertexReferenceGroupBuilder.clear().normalVertexRef(Integer.valueOf(-101)).build();
  }

  @Test
  public void VertexReferenceGroup_setTexVertexAndNormalVertexAndBuild_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("geometricVertexReferenceNumber must be set");

    vertexReferenceGroupBuilder.clear().texVertexRef(Integer.valueOf(22))
        .normalVertexRef(Integer.valueOf(33)).build();
  }

  @Test
  public void VertexReferenceGroup_copyVertexReferenceGroupWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("vertexReferenceGroup cannot be null");

    statementFactory.copyVertexReferenceGroup(null);
  }

  @Test
  public void VertexReferenceGroup_copyVertexReferenceGroup_vertexReferenceGroupIsCopied() {
    Integer geoVertexIndex = Integer.valueOf(1);
    Integer texVertexIndex = Integer.valueOf(-99);
    Integer normalVertexIndex = Integer.valueOf(56);
    GeoVertexReference geoVertexReference =
        statementFactory.createGeoVertexReference(geoVertexIndex);
    TexVertexReference texVertexReference =
        statementFactory.createTexVertexReference(texVertexIndex);
    NormalVertexReference normalVertexReference =
        statementFactory.createNormalVertexReference(normalVertexIndex);
    VertexReferenceGroup originalVertexReferenceGroup =
        vertexReferenceGroupBuilder.clear().geoVertexRef(geoVertexIndex)
            .texVertexRef(texVertexIndex).normalVertexRef(normalVertexIndex).build();

    VertexReferenceGroup copiedVertexReferenceGroup =
        statementFactory.copyVertexReferenceGroup(originalVertexReferenceGroup);

    assertValidVertexReferenceGroup(geoVertexReference, texVertexReference, normalVertexReference,
        copiedVertexReferenceGroup);
  }

  @Test
  public void VertexReferenceGroup_accessTexVertexWhenItIsNotSet_unsupportedOperationException() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot access texture vertex reference when it is not set");

    Integer geoVertexIndex = Integer.valueOf(1);
    GeoVertexReference geoVertexReference =
        statementFactory.createGeoVertexReference(geoVertexIndex);
    VertexReferenceGroup vertexReferenceGroup =
        vertexReferenceGroupBuilder.clear().geoVertexRef(geoVertexIndex).build();

    assertValidVertexReferenceGroup(geoVertexReference, vertexReferenceGroup);

    vertexReferenceGroup.getTexVertexRef();
  }

  @Test
  public void VertexReferenceGroup_accessNormalVertexWhenItIsNotSet_unspportedOperationException() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot access normal vertex reference when it is not set");

    Integer geoVertexIndex = Integer.valueOf(1);
    GeoVertexReference geoVertexReference =
        statementFactory.createGeoVertexReference(geoVertexIndex);
    VertexReferenceGroup vertexReferenceGroup =
        vertexReferenceGroupBuilder.clear().geoVertexRef(geoVertexIndex).build();

    assertValidVertexReferenceGroup(geoVertexReference, vertexReferenceGroup);

    vertexReferenceGroup.getNormalVertexRef();
  }

  @Test
  public void VertexReferenceGroup_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    VertexReferenceGroup first;
    VertexReferenceGroup second;

    // just geoVertexReference

    first = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(54)).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(54)).build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different geoVertexReference
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(-54)).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(54)).build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // geoVertexReference and texVertexReference

    first = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(22))
        .texVertexRef(Integer.valueOf(34)).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(22))
        .texVertexRef(Integer.valueOf(34)).build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different geoVertexReference
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(21))
        .texVertexRef(Integer.valueOf(34)).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(22))
        .texVertexRef(Integer.valueOf(34)).build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different texVertexReference
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(22))
        .texVertexRef(Integer.valueOf(31)).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(22))
        .texVertexRef(Integer.valueOf(34)).build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // geoVertexReference and normalVertexReference

    first = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(45))
        .normalVertexRef(Integer.valueOf(1)).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(45))
        .normalVertexRef(Integer.valueOf(1)).build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different geoVertexReference
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(45))
        .normalVertexRef(Integer.valueOf(1)).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(450))
        .normalVertexRef(Integer.valueOf(1)).build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different normalVertexReference
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(45))
        .normalVertexRef(Integer.valueOf(1)).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(Integer.valueOf(45))
        .normalVertexRef(Integer.valueOf(10)).build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // geoVertexReference, texVertexReference, and normalVertexReference

    first = vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(2).normalVertexRef(3)
        .build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(2).normalVertexRef(3)
        .build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different geoVertexReference
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(2).normalVertexRef(3)
        .build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(11).texVertexRef(2).normalVertexRef(3)
        .build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different texVertexReference
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(2).normalVertexRef(3)
        .build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(21).normalVertexRef(3)
        .build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different normalVertexReference
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(2).normalVertexRef(3)
        .build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(2).normalVertexRef(31)
        .build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: vertexReferenceGroups with different vertex references
    first = vertexReferenceGroupBuilder.clear().geoVertexRef(1).normalVertexRef(3).build();
    second = vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(2).normalVertexRef(3)
        .build();
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
