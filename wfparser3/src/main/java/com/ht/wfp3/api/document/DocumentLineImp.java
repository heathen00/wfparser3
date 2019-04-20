package com.ht.wfp3.api.document;

import com.ht.wfp3.api.statement.Statement;

class DocumentLineImp implements DocumentLine {
  private final Statement statement;
  private final Comment comment;

  DocumentLineImp(Statement statement, Comment comment) {
    super();
    this.statement = statement;
    this.comment = comment;
  }

  @Override
  public Comment getComment() {
    return comment;
  }

  @Override
  public Statement getStatement() {
    return statement;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((comment == null) ? 0 : comment.hashCode());
    result = prime * result + ((statement == null) ? 0 : statement.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DocumentLineImp other = (DocumentLineImp) obj;
    if (comment == null) {
      if (other.comment != null)
        return false;
    } else if (!comment.equals(other.comment))
      return false;
    if (statement == null) {
      if (other.statement != null)
        return false;
    } else if (!statement.equals(other.statement))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "DocumentLineImp [statement=" + statement + ", comment=" + comment + "]";
  }
}
