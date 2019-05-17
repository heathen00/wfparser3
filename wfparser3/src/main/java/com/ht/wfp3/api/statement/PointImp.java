package com.ht.wfp3.api.statement;

import java.util.List;

class PointImp extends StatementsUsingVertexReferenceGroupsImp implements Point {
  private static final String KEYWORD = "p";

  PointImp(List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(KEYWORD, MINIMUM_VERTEX_REFERENCE_GROUPS, vertexReferenceGroupList);
  }

  PointImp(Point point) {
    this(point.getVertexReferenceGroupList());
  }

  @Override
  public String toString() {
    return "PointImp [super.toString()=" + super.toString() + "]";
  }
}
