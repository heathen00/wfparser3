package com.ht.wfp3.api.statement;

class LineImp extends StatementsUsingVertexReferenceGroupsImp implements Line {
  private static final String KEYWORD = "l";

  LineImp() {
    super(KEYWORD);
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
