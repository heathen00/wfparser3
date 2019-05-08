package com.ht.wfp3.api.statement;

import java.util.List;

class FaceImp extends StatementsUsingVertexReferenceGroupsImp implements Face {
  private static final String KEYWORD = "f";

  FaceImp(List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(KEYWORD, vertexReferenceGroupList);
    if (getVertexReferenceGroupList().size() < MINIMUM_VERTEX_REFERENCE_GROUPS) {
      throw new IllegalArgumentException("vertexReferenceGroupList must contain at least "
          + MINIMUM_VERTEX_REFERENCE_GROUPS + " vertex reference groups");
    }
  }

  FaceImp(Face face) {
    this(face.getVertexReferenceGroupList());
  }

  @Override
  public String toString() {
    return "FaceImp [super.toString()=" + super.toString() + "]";
  }
}
