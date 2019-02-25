package com.ht.wfp3.api.statement;

class LineImp extends StatementsUsingVertexReferencesImp implements Line {
  private static final String KEYWORD = "l";
  private static final boolean CAN_COMMENT = true;

  LineImp() {
    super(KEYWORD, CAN_COMMENT);
  }

  LineImp(Line line) {
    this();
    copyVertexReferenceGroupsInConstructor(line.getReferenceNumbers());
  }
  
  @Override
  public String toString() {
    return "LineImp [super.toString()=" + super.toString() + "]";
  }
}
