package com.ht.wfp3.api.document;

public class NonExistentLineException extends DocumentException {
  private static final long serialVersionUID = 8989572196396059579L;

  NonExistentLineException(String message) {
    super(message);
  }

  NonExistentLineException() {
    super();
  }
}
