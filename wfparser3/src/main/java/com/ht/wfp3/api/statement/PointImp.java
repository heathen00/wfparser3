package com.ht.wfp3.api.statement;

class PointImp extends StatementsUsingVertexReferencesImp implements Point {
  private static final String KEYWORD = "p";
  private static final boolean CAN_COMMENT = true;

  PointImp() {
    super(KEYWORD, CAN_COMMENT);
  }

  PointImp(Point point) {
    this();
    copyVertexReferenceGroupsInConstructor(point.getReferenceNumbers());
  }

  @Override
  public String toString() {
    return "PointImp [super.toString()=" + super.toString() + "]";
  }
}
