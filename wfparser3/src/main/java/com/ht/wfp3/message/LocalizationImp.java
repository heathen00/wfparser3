package com.ht.wfp3.message;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class LocalizationImp implements Localization {
  private static final String SYSTEM_L10N_BUNDLE_BASENAME = "com.ht.wfp3.message.SystemL10nBundle";
  private static final String L10N_KEY_SEPARATOR = ".";
  private static final String SYSTEM_L10N_TOPIC = "system";

  private Locale locale;
  private ResourceBundle priorityResourceBundle;

  LocalizationImp() {
    locale = Locale.getDefault();
    priorityResourceBundle = ResourceBundle.getBundle(SYSTEM_L10N_BUNDLE_BASENAME, locale);
  }

  private String mapPriorityUidKeyToL10NKey(String fieldName, String priorityUidKey) {
    final String priorityTopic = SYSTEM_L10N_TOPIC;
    final String priorityType = "priority";
    final String priorityPrefix = priorityTopic + L10N_KEY_SEPARATOR + priorityType
        + L10N_KEY_SEPARATOR + fieldName + L10N_KEY_SEPARATOR;
    return priorityPrefix + priorityUidKey;
  }

  private String mapTopicUidKeyToL10NKey(String fieldName, String topicUidKey) {
    final String topicTopic = SYSTEM_L10N_TOPIC;
    final String topicType = "topic";
    final String topicFieldName = "name";
    final String topicPrefix = topicTopic + L10N_KEY_SEPARATOR + topicType + L10N_KEY_SEPARATOR
        + topicFieldName + L10N_KEY_SEPARATOR;
    return topicPrefix + topicUidKey;
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(Locale locale) {
    this.locale = locale;
    priorityResourceBundle = ResourceBundle.getBundle(SYSTEM_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public void setDefaultLocale() {
    locale = Locale.getDefault();
    priorityResourceBundle = ResourceBundle.getBundle(SYSTEM_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public String getPriorityName(String priorityUidKey) {
    final String fieldName = "name";
    String l10NKey = mapPriorityUidKeyToL10NKey(fieldName, priorityUidKey);
    String l10NValue = null;
    try {
      l10NValue = priorityResourceBundle.getString(l10NKey);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }

  @Override
  public String getTopicName(String topicUidKey) {
    final String fieldName = "name";
    final String l10NKey = mapTopicUidKeyToL10NKey(fieldName, topicUidKey);
    String l10NValue = null;
    try {
      l10NValue = priorityResourceBundle.getString(l10NKey);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }
}
