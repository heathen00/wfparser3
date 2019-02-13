package com.ht.wfp3.api.statement;

/**
 * A statement represents a full OBJ statement which comprises a full line. Statements can be added
 * directly to documents.
 * 
 * @author nickl
 *
 */
public interface Statement extends SubStatment, Commentable {
  static StatementFactory createStatementFactory() {
    // TODO Auto-generated method stub
    return null;
  }

  boolean canComment();
}
