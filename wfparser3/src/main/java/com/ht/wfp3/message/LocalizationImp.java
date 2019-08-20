package com.ht.wfp3.message;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class LocalizationImp implements Localization {
  private static final String SYSTEM_L10N_BUNDLE_BASENAME = "com.ht.wfp3.message.SystemL10nBundle";

  private Locale locale;
  private ResourceBundle systemResourceBundle;

  LocalizationImp() {
    locale = Locale.getDefault();
    systemResourceBundle = ResourceBundle.getBundle(SYSTEM_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(Locale locale) {
    this.locale = locale;
    systemResourceBundle = ResourceBundle.getBundle(SYSTEM_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public void setDefaultLocale() {
    locale = Locale.getDefault();
    systemResourceBundle = ResourceBundle.getBundle(SYSTEM_L10N_BUNDLE_BASENAME, locale);
  }

  @Override
  public String getPriorityName(String priorityUidKey) {
    String l10NValue = null;
    try {
      l10NValue = systemResourceBundle.getString(priorityUidKey);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }

  @Override
  public String getTopicName(String topicUidKey) {
    String l10NValue = null;
    try {
      l10NValue = systemResourceBundle.getString(topicUidKey);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }

  @Override
  public String getDescriptionUnformattedText(String descriptionUidKey) {
    String l10NValue = null;
    try {
      l10NValue = systemResourceBundle.getString(descriptionUidKey);
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
          String.format(locale, systemResourceBundle.getString(descriptionUidKey), parameters);
    } catch (MissingResourceException mre) {
      l10NValue = Localization.UNKNOWN_L10N_KEY;
    }
    return l10NValue;
  }
}
