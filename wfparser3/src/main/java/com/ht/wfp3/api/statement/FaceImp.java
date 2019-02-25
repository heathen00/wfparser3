package com.ht.wfp3.api.statement;

class FaceImp extends StatementsUsingVertexReferencesImp implements Face {
  private static final String KEYWORD = "f";
  private static final boolean CAN_COMMENT = true;

  FaceImp() {
    super(KEYWORD, CAN_COMMENT);
  }

  FaceImp(Face face) {
    this();
    copyVertexReferenceGroupsInConstructor(face.getReferenceNumbers());
  }
  
  @Override
  public String toString() {
    return "FaceImp [super.toString()=" + super.toString() + "]";
  }
}
