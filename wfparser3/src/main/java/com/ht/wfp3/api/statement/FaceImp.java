package com.ht.wfp3.api.statement;

class FaceImp extends StatementsUsingVertexReferencesImp implements Face {

  FaceImp() {
    super();
  }

  FaceImp(Face face) {
    this();
    copyVertexReferenceGroupsInConstructor(face.getReferenceNumbers());
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
    return "FaceImp [super.toString()=" + super.toString() + "]";
  }
}
