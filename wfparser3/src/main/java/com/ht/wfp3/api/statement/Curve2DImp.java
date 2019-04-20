package com.ht.wfp3.api.statement;

import java.util.List;

class Curve2DImp extends StatementsUsingVertexReferenceGroupsImp implements Curve2D {
  private static final String KEYWORD = "curv2";

  Curve2DImp(List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(KEYWORD, vertexReferenceGroupList);
  }

  Curve2DImp(Curve2D curv2) {
    this(curv2.getVertexReferenceGroupList());
  }
}
