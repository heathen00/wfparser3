package com.ht.wfp3.api.statement;

import java.util.List;

class LineImp extends StatementsUsingVertexReferenceGroupsImp implements Line {
  private static final String KEYWORD = "l";

  LineImp(List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(KEYWORD, MINIMUM_VERTEX_REFERENCE_GROUPS, vertexReferenceGroupList);
  }

  LineImp(Line line) {
    this(line.getVertexReferenceGroupList());
  }

  @Override
  public String toString() {
    return "LineImp [super.toString()=" + super.toString() + "]";
  }

  @Override
  public int compareTo(Line o) {
    return compareToCommon(o);
  }
}
