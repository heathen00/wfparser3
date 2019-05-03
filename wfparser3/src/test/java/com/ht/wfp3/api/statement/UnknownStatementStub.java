package com.ht.wfp3.api.statement;

public class UnknownStatementStub implements Statement {

  @Override
  public String getKeyword() {
    return null;
  }

  @Override
  public int compareTo(Statement o) {
    return 0;
  }
}
