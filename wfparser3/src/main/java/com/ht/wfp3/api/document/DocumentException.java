package com.ht.wfp3.api.document;

public abstract class DocumentException extends Exception {
  private static final long serialVersionUID = 8243092495771472405L;

  DocumentException(String message) {
    super(message);
  }

  DocumentException() {
    super();
  }
}
