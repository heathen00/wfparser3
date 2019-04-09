package com.ht.wfp3.api.statement;

public class UnknownStatementStub implements Statement {

  @Override
  public String getKeyword() {
    return null;
  }

  @Override
  public void setComment(Comment comment) {}

  @Override
  public Comment getComment() {
    return null;
  }

  @Override
  public boolean canComment() {
    return false;
  }

}
