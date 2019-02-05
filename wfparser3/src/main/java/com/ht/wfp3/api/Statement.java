package com.ht.wfp3.api;

/**
 * A statement represents a full OBJ statement which comprises a full line. Statements can be added
 * directly to documents.
 * 
 * @author nickl
 *
 */
public interface Statement extends SubStatment, Commentable {
  boolean canComment();
}
