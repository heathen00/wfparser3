package com.ht.wfp3.api;

/**
 * A cursor marks a line in a document. Cursors can be used to mark the beginning of file (BOF), the
 * end of file (EOF), or somewhere in between. Any valid cursor for a document will be greater than
 * BOF and less than or equal to EOF. Any cursor that does not meet this criteria or is a cursor for
 * document other than the one this cursor belongs to is invalid.
 * 
 * @author nickl
 *
 */
public interface Cursor {
  void setTo(Cursor cursor);

  Cursor previousLine();
}
