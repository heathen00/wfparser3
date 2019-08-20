package com.ht.wfp3.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import org.junit.Test;

public class MessageSystemTest {

  @Test
  public void MessageSystem_createDefaultMessageSystem_defaultMessageSystemCreated()
      throws Exception {
    MessageSystem messageSystem = MessageSystem.createMessageSystem();
    MessageSystem.Config messageSystemConfig = messageSystem.getConfig();
    MessageSystem.Config singletonMessageSystemConfig = messageSystem.getConfig();
    Localization localization = messageSystemConfig.getLocalization();
    Locale locale = localization.getLocale();
    assertNotNull(messageSystem);
    assertNotNull(messageSystemConfig);
    assertTrue(messageSystemConfig == singletonMessageSystemConfig);
    assertNotNull(localization);
    assertNotNull(locale);
    assertEquals(Locale.getDefault(), locale);
  }
}
