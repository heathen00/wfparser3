package com.ht.wfp3.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;
import com.ht.wfp3.message.Localization;
import com.ht.wfp3.message.MessageFactory;
import com.ht.wfp3.message.MessageSystem;

public class MessageSystemTest {

  @Test
  public void MessageSystem_createDefaultMessageSystem_defaultMessageSystemCreated() {
    MessageSystem messageSystem = MessageSystem.createMessageSystem();
    MessageSystem singletonMessageSystem = MessageSystem.createMessageSystem();
    MessageFactory messageFactory = messageSystem.createMessageFactory();
    MessageFactory singletonMessageFactory = messageSystem.createMessageFactory();
    MessageSystem.Config messageSystemConfig = messageSystem.getConfig();
    MessageSystem.Config singletonMessageSystemConfig = messageSystem.getConfig();
    Localization localization = messageSystemConfig.getLocalization();
    Locale locale = localization.getLocale();
    ResourceBundle priorityResourceBundle = localization.getPriorityResourceBundle();

    assertNotNull(messageSystem);
    assertTrue(messageSystem == singletonMessageSystem);
    assertNotNull(messageFactory);
    assertTrue(messageFactory == singletonMessageFactory);
    assertNotNull(messageSystemConfig);
    assertTrue(messageSystemConfig == singletonMessageSystemConfig);
    assertNotNull(localization);
    assertNotNull(locale);
    assertEquals(Locale.getDefault(), locale);
    assertNotNull(priorityResourceBundle);
  }
}
