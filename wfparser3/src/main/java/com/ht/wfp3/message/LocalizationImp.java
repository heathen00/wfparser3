package com.ht.wfp3.message;

import java.util.Locale;
import java.util.ResourceBundle;

class LocalizationImp implements Localization {
  private static final String PRIORITY_L10N_BASENAME = "com/ht/wfp3/message/PriorityL10nBundle";

  private Locale locale;
  private ResourceBundle priorityResourceBundle;

  LocalizationImp() {
    locale = Locale.getDefault();
    priorityResourceBundle = ResourceBundle.getBundle(PRIORITY_L10N_BASENAME, locale);
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public ResourceBundle getPriorityResourceBundle() {
    return priorityResourceBundle;
  }
}
