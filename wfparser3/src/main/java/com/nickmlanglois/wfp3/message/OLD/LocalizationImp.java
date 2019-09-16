package com.nickmlanglois.wfp3.message.OLD;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class LocalizationImp implements Localization {
  private static final String MESSAGE_L10N_BUNDLE_BASENAME =
      "com.nickmlanglois.wfp3.message.OLD.MessageL10nBundle";

  private Locale locale;
  private ResourceBundle messageResourceBundle;

  LocalizationImp() {
    locale = Locale.getDefault();
    messageResourceBundle = ResourceBundle.getBundle(MESSAGE_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(Locale locale) {
    this.locale = locale;
    messageResourceBundle = ResourceBundle.getBundle(MESSAGE_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public void setDefaultLocale() {
    locale = Locale.getDefault();
    messageResourceBundle = ResourceBundle.getBundle(MESSAGE_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public String getPriorityName(String priorityUidKey) {
    String l10NValue = null;
    try {
      l10NValue = messageResourceBundle.getString(priorityUidKey);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }

  @Override
  public String getTopicName(String topicUidKey) {
    String l10NValue = null;
    try {
      l10NValue = messageResourceBundle.getString(topicUidKey);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }

  @Override
  public String getDescriptionUnformattedText(String descriptionUidKey) {
    String l10NValue = null;
    try {
      l10NValue = messageResourceBundle.getString(descriptionUidKey);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }

  @Override
  public String getDescriptionFormattedText(String descriptionUidKey, Object... parameters) {
    String l10NValue = null;
    try {
      l10NValue =
          String.format(locale, messageResourceBundle.getString(descriptionUidKey), parameters);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }
}
