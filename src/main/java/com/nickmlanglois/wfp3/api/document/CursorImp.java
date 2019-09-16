package com.nickmlanglois.wfp3.api.document;

import com.google.common.annotations.VisibleForTesting;

class CursorImp implements Cursor {
  private static final int FIRST_LINE = 1;
  private int currentLine;
  private DocumentViewImp documentView;

  CursorImp(DocumentViewImp documentView) {
    if (null == documentView) {
      throw new NullPointerException("document cannot be null");
    }
    this.currentLine = 1;
    this.documentView = documentView;
  }

  @SuppressWarnings("unused")
  private CursorImp() {}

  @Override
  public void toEof() {
    currentLine = documentView.getLineNumberAtEof();
  }

  @Override
  public void toBof() {
    currentLine = FIRST_LINE;
  }

  @Override
  public void toCursor(Cursor cursor) throws IllegalArgumentException {
    if (null == cursor) {
      throw new NullPointerException("cursor cannot be null");
    }
    if (!documentView.equals(((CursorImp) cursor).getDocumentImp())) {
      throw new IllegalArgumentException("Cursor " + cursor + " is from another document");
    }
    currentLine = cursor.getLineNumber().intValue();
  }

  @Override
  public void toLineNumber(Integer lineNumber) throws NonExistentLineException {
    if (null == lineNumber) {
      throw new NullPointerException("lineNumber is null");
    }
    if (lineNumber.intValue() < FIRST_LINE
        || lineNumber.intValue() > documentView.getLineNumberAtEof()) {
      throw new NonExistentLineException("line " + lineNumber + " is invalid");
    }
    currentLine = lineNumber.intValue();
  }

  @Override
  public void toPreviousLine() throws NonExistentLineException {
    if (!hasPreviousLine()) {
      throw new NonExistentLineException("cannot iterate before first line of document");
    }
    currentLine--;
  }

  @Override
  public void toNextLine() throws NonExistentLineException {
    if (!hasNextLine()) {
      throw new NonExistentLineException("cannot iterate beyond last line of document");
    }
    currentLine++;
  }

  @Override
  public Integer getLineNumber() {
    return Integer.valueOf(currentLine);
  }

  @VisibleForTesting
  public DocumentViewImp getDocumentImp() {
    return documentView;
  }

  @Override
  public boolean hasNextLine() {
    return currentLine < documentView.getNumberOfLines();
  }

  @Override
  public boolean hasPreviousLine() {
    return currentLine > FIRST_LINE;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + currentLine;
    result = prime * result + ((documentView == null) ? 0 : documentView.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CursorImp other = (CursorImp) obj;
    if (currentLine != other.currentLine) {
      return false;
    }
    if (documentView == null) {
      if (other.documentView != null) {
        return false;
      }
    } else if (!documentView.equals(other.documentView)) {
      return false;
    }
    return true;
  }
}
