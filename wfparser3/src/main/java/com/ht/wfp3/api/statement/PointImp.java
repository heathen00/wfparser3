package com.ht.wfp3.api.statement;

class PointImp extends StatementsUsingVertexReferencesImp implements Point {

  PointImp() {
    super();
  }

  PointImp(Point point) {
    this();
    copyVertexReferenceGroupsInConstructor(point.getReferenceNumbers());
  }

  @Override
  public boolean canComment() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getKeyword() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setComment(Comment comment) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getComment() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    return "PointImp [super.toString()=" + super.toString() + "]";
  }
}
