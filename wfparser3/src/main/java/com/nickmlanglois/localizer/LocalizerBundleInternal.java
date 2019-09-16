package com.nickmlanglois.localizer;

import java.util.Locale;

interface LocalizerBundleInternal extends LocalizerBundle {
  String getFormattedString(LocalizerInstance localizerInstance, Object... parameters)
      throws LocalizerException;

  String getUnformattedString(LocalizerInstance localizerInstance) throws LocalizerException;

  void loadL10nResource(Locale locale) throws LocalizerException;
}
