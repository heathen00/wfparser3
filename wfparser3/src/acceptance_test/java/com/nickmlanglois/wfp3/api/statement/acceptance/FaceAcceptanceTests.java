package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.Face;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.VertexReferenceGroup;

public class FaceAcceptanceTests {
  private static final String FACE_KEYWORD = "f";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private VertexReferenceGroup createTestVertexReferenceGroup(int geoVertexRef, int texVertexRef,
      int normalVertexRef) {
    return statementFactory.createVertexReferenceGroupBuilder().clear().geoVertexRef(geoVertexRef)
        .texVertexRef(texVertexRef).normalVertexRef(normalVertexRef).build();
  }

  private void assertValidFace(List<VertexReferenceGroup> expectedVertexReferenceGroupList,
      Face face) {
    assertNotNull(face);
    assertEquals(FACE_KEYWORD, face.getKeyword());
    assertEquals(expectedVertexReferenceGroupList, face.getVertexReferenceGroupList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void Face_createFaceWithNullVertexReferenceGroupList_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("vertexReferenceGroupList cannot be null");

    statementFactory.createFace(null);
  }

  @Test
  public void Face_createFaceWithEmptyVertexReferenceGroupList_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexReferenceGroupList must have more than ");

    statementFactory.createFace(Collections.emptyList());
  }

  @Test
  public void Face_createFaceWithLessThanThreeVertexReferenceGroupsInList_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexReferenceGroupList must have more than ");

    statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
        createTestVertexReferenceGroup(2, 2, 2)));
  }

  @Test
  public void Face_createFaceWithVertexReferenceGroupsListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexReferenceGroupList cannot contain null members");

    statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1), null,
        createTestVertexReferenceGroup(3, 3, 3)));
  }

  @Test
  public void Face_createFaceWithThreeVertexReferenceGroupsInList_faceIsCreated() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
            createTestVertexReferenceGroup(2, 2, 2), createTestVertexReferenceGroup(3, 3, 3));

    Face face = statementFactory.createFace(vertexReferenceGroupList);

    assertValidFace(vertexReferenceGroupList, face);
  }

  @Test
  public void Face_createFaceWithMoreThanThreeVertexReferenceGroupsInList_faceIsCreated() {
    List<VertexReferenceGroup> vertexReferenceGroupList = Arrays.asList(
        createTestVertexReferenceGroup(1, 1, 1), createTestVertexReferenceGroup(2, 2, 2),
        createTestVertexReferenceGroup(3, 3, 3), createTestVertexReferenceGroup(4, 4, 4),
        createTestVertexReferenceGroup(5, 5, 5), createTestVertexReferenceGroup(6, 6, 6));

    Face face = statementFactory.createFace(vertexReferenceGroupList);

    assertValidFace(vertexReferenceGroupList, face);
  }

  @Test
  public void Face_copyFaceWithNullParameter_nullPointerExpressionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("face cannot be null");

    statementFactory.copyFace(null);
  }

  @Test
  public void Face_copyFace_faceIsCopied() {
    List<VertexReferenceGroup> vertexReferenceGroupList =
        Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
            createTestVertexReferenceGroup(2, 2, 2), createTestVertexReferenceGroup(3, 3, 3));
    Face originalFace = statementFactory.createFace(vertexReferenceGroupList);

    Face copiedFace = statementFactory.copyFace(originalFace);

    assertValidFace(vertexReferenceGroupList, copiedFace);
  }

  @Test
  public void Face_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Face first;
    Face second;

    first = statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
        createTestVertexReferenceGroup(2, 2, 2), createTestVertexReferenceGroup(3, 3, 3)));
    second = statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
        createTestVertexReferenceGroup(2, 2, 2), createTestVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different number of vertexReferenceGroups
    first = statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
        createTestVertexReferenceGroup(2, 2, 2), createTestVertexReferenceGroup(3, 3, 3),
        createTestVertexReferenceGroup(4, 4, 4)));
    second = statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
        createTestVertexReferenceGroup(2, 2, 2), createTestVertexReferenceGroup(3, 3, 3)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different vertexReferenceGroup values
    first = statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
        createTestVertexReferenceGroup(2, 2, 2), createTestVertexReferenceGroup(3, 3, 3)));
    second = statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
        createTestVertexReferenceGroup(3, 3, 3), createTestVertexReferenceGroup(4, 4, 4)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
