package com.ht.l10n.acceptance;

import static org.junit.Assert.*;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;

public class LocalizerAcceptanceTest {
  private Factory localizerFactory;

  @Before
  public void setup() {
    localizerFactory = Factory.createFactory();
  }

  @Test
  public void Localizer_createFactory_factoryIsCreated() {
    assertNotNull(localizerFactory);
  }


  @Test(expected = NullPointerException.class)
  public void Localizer_setLocaleToNull_nullPointerExceptionIsThrown() {
    Localizer localize = localizerFactory.createLocalizer(Locale.CHINESE);
    localize.setLocale(null);
  }

  @Test
  public void Localizer_setLocaleToANewLocale_localeChangedToNewLocale() {
    Locale originalLocale = Locale.CHINESE;
    Locale newLocale = Locale.CANADA_FRENCH;
    Localizer localize = localizerFactory.createLocalizer(originalLocale);

    assertNotNull(localize);
    assertNotNull(localize.getLocale());
    assertEquals(originalLocale, localize.getLocale());

    localize.setLocale(newLocale);
    assertNotNull(localize.getLocale());
    assertEquals(newLocale, localize.getLocale());
  }

}
