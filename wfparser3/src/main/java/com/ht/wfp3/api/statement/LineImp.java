package com.ht.wfp3.api.statement;

class LineImp extends StatementsUsingVertexReferenceGroupsImp implements Line {
  private static final String KEYWORD = "l";
  private static final boolean CAN_COMMENT = true;

  LineImp() {
    super(KEYWORD, CAN_COMMENT);
  }

  LineImp(Line line) {
    this();
    copyVertexReferenceGroupsInConstructor(line.getVertexReferenceGroupList());
  }

  @Override
  public String toString() {
    return "LineImp [super.toString()=" + super.toString() + "]";
  }
}
