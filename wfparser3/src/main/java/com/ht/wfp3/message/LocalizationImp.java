package com.ht.wfp3.message;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

class LocalizationImp implements Localization {
  private static final String PRIORITY_L10N_BUNDLE_BASENAME =
      "com.ht.wfp3.message.PriorityL10nBundle";
  private static final String L10N_KEY_SEPARATOR = ".";
  private static final String PRIORITY_L10N_TOPIC = "system";
  private static final String PRIORITY_L10N_TYPE = "priority";
  private static final String PRIORITY_L10N_FIELD_NAME = "name";
  private static final String PRIORITY_L10N_PREFIX = PRIORITY_L10N_TOPIC + L10N_KEY_SEPARATOR
      + PRIORITY_L10N_TYPE + L10N_KEY_SEPARATOR + PRIORITY_L10N_FIELD_NAME + L10N_KEY_SEPARATOR;

  private Locale locale;
  private ResourceBundle priorityResourceBundle;

  LocalizationImp() {
    locale = Locale.getDefault();
    priorityResourceBundle = ResourceBundle.getBundle(PRIORITY_L10N_BUNDLE_BASENAME, locale);
  }

  private String mapUidKeyToL10NKey(String uidKey) {
    return PRIORITY_L10N_PREFIX + uidKey;
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(Locale locale) {
    this.locale = locale;
    priorityResourceBundle = ResourceBundle.getBundle(PRIORITY_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public void setDefaultLocale() {
    locale = Locale.getDefault();
    priorityResourceBundle = ResourceBundle.getBundle(PRIORITY_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public String getPriorityName(String uidKey) {
    String l10NKey = mapUidKeyToL10NKey(uidKey);
    String l10NValue = null;
    try {
      l10NValue = priorityResourceBundle.getString(l10NKey);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }
}
