package com.ht.wfp3.message.OLD;

public class ConstraintViolationException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -6965953951638268930L;

  @SuppressWarnings("unused")
  private ConstraintViolationException() {}

  public ConstraintViolationException(String string) {
    super(string);
  }
}
