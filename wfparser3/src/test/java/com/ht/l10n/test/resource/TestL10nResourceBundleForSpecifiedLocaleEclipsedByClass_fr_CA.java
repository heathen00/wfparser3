package com.ht.l10n.test.resource;

import java.util.ListResourceBundle;

public class TestL10nResourceBundleForSpecifiedLocaleEclipsedByClass_fr_CA
    extends ListResourceBundle {

  @Override
  protected Object[][] getContents() {
    return new Object[][] { //
        { "test00", "this" }, //
        { "test01", "content" }, //
        { "test02", "does" }, //
        { "test03", "not" }, //
        { "test04", "matter" }, //
    };
  }

}
