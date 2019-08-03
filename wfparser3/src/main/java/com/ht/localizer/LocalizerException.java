package com.ht.localizer;

import java.util.MissingResourceException;

public class LocalizerException extends Exception {

  /**
   * Generated serial version UID.
   */
  private static final long serialVersionUID = -308072700902302564L;

  @SuppressWarnings("unused")
  private LocalizerException() {

  }

  LocalizerException(String message) {
    super(message);
  }

  LocalizerException(MissingResourceException mre) {
    super(mre);
  }
}
