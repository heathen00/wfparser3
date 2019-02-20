package com.ht.wfp3.api.statement;

class LineImp extends StatementsUsingVertexReferencesImp implements Line {

  LineImp() {
    super();
  }

  LineImp(Line line) {
    this();
    copyVertexReferenceGroupsInConstructor(line.getReferenceNumbers());
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
    return "LineImp [super.toString()=" + super.toString() + "]";
  }
}
