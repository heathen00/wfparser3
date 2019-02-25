package com.ht.wfp3.api.statement;

abstract class StatementImp extends SubStatementImp implements Statement {
  private final boolean canComment;
  
  public StatementImp(String keyword, boolean canComment) {
    super(keyword);
    this.canComment = canComment;
  }
  
  @Override
  public boolean canComment() {
    return this.canComment;
  }

  @Override
  public void setComment(Comment comment) {
    // TODO Auto-generated method stub
  }

  @Override
  public Comment getComment() {
    // TODO Auto-generated method stub
    return null;
  }
  
  // TODO: will need to update equals, hashCode, and toString after implementing Comment.

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (canComment ? 1231 : 1237);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    StatementImp other = (StatementImp) obj;
    if (canComment != other.canComment) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "StatementImp [canComment=" + canComment + ", super.toString()=" + super.toString() + "]";
  }
}
