package com.ht.wfp3.api.statement;

/**
 * A statement represents a full OBJ statement which comprises a full line. Statements can be added
 * directly to documents.
 * 
 * @author nickl
 *
 */
public interface Statement {
  String getKeyword();

  boolean equals(Object object);

  public int hashCode();
}
