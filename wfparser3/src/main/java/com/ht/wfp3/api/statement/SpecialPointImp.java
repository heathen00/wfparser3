package com.ht.wfp3.api.statement;

import java.util.List;

class SpecialPointImp extends StatementsUsingVertexReferenceGroupsImp implements SpecialPoint {
  private static final String KEYWORD = "sp";
  
  SpecialPointImp(List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(KEYWORD, vertexReferenceGroupList);
  }
  
  SpecialPointImp(SpecialPoint sp) {
    this(sp.getVertexReferenceGroupList());
  }
}
