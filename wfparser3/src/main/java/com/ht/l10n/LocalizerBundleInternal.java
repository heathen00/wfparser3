package com.ht.l10n;

import java.util.Locale;

interface LocalizerBundleInternal extends LocalizerBundle, DefinedObject {

  void loadL10nResource(Locale locale) throws LocalizerException;
}
