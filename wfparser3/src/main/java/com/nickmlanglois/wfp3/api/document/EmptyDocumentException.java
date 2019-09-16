package com.nickmlanglois.wfp3.api.document;

public class EmptyDocumentException extends DocumentException {
  private static final long serialVersionUID = -8366873541105193068L;

  EmptyDocumentException(String message) {
    super(message);
  }

  EmptyDocumentException() {
    super();
  }
}
