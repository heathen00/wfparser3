package com.ht.wfp3.api.statement;

class PointImp extends StatementsUsingVertexReferenceGroupsImp implements Point {
  private static final String KEYWORD = "p";

  PointImp() {
    super(KEYWORD);
  }

  PointImp(Point point) {
    this();
    copyVertexReferenceGroupsInConstructor(point.getVertexReferenceGroupList());
  }

  @Override
  public String toString() {
    return "PointImp [super.toString()=" + super.toString() + "]";
  }
}
