package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Face;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.VertexReferenceGroup;

public class FaceAcceptanceTests {

  private static final String FACE_KEYWORD = "f";
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

  @Test(expected = NullPointerException.class)
  public void Face_createFaceWithNullVertexReferenceGroupList_nullPointerExceptionIsThrown() {
    statementFactory.createFace(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Face_createFaceWithEmptyVertexReferenceGroupList_illegalArgumentExceptionIsThrown() {
    statementFactory.createFace(Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Face_createFaceWithLessThanThreeVertexReferenceGroupsInList_illegalArgumentExceptionIsThrown() {
    statementFactory.createFace(Arrays.asList(createTestVertexReferenceGroup(1, 1, 1),
        createTestVertexReferenceGroup(2, 2, 2)));
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

  @Test(expected = NullPointerException.class)
  public void Face_copyFaceWithNullParameter_nullPointerExpressionIsThrown() {
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

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.
  // TODO what about lists with null members?

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}