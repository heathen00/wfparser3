package com.ht.wfp3.message;

import java.util.Locale;
import java.util.ResourceBundle;

public interface Localization {

  Locale getLocale();

  ResourceBundle getPriorityResourceBundle();

}
