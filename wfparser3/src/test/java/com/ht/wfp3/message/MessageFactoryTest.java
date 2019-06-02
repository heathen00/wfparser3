package com.ht.wfp3.message;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class MessageFactoryTest {

  private MessageSystem messageSystem;
  private MessageFactory messageFactory;

  @Before
  public void setup() {
    messageSystem = MessageSystem.createMessageSystem();
    messageSystem.resetToDefault();
    messageFactory = MessageSystem.createMessageSystem().getMessageFactory();
  }

  // TODO you should research more detailed exception testing since in this case you need to ensure
  // The correct behaviour of the exception, too. There is something about Rules.
  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addPriorityWithNullUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addPriority(null);
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addPriorityWithEmptyUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addPriority("   \t");
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addPriorityWithUidKeyContainingWhitespaceOrNewLines_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addPriority("not\tvalid");
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addPriorityWithUidKeyIsTooLong_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addPriority(
        "priorityUidKeyMustBeLessThanMaximumLengthSoIfItIsTooLongThenAConstraintViolationExceptionWillBeThrown");
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addPriorityWithUidKeyContainingInvalidCharacters_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addPriority("Invalid_Key_012345");
  }

  @Test
  public void MessageFactory_addTheTestingPriority_testingPriorityIsAdded() throws Exception {
    String testingUidKey = "testing";
    UID<Priority> priorityUid = messageFactory.addPriority(testingUidKey);
    Priority undefinedPriority = messageFactory.getPriority(priorityUid);

    assertNotNull(undefinedPriority);
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addPriorityWithDuplicateUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    String testingUidKey = "testing.defined.in.all";
    messageFactory.addPriority(testingUidKey);

    messageFactory.addPriority(testingUidKey);
  }

  @Test
  public void MessageFactory_setLocaleToAvailableLocaleResourcesAndGetPriorityName_nameFromAvailableResourcesReturned()
      throws Exception {
    messageSystem.getConfig().getLocalization().setLocale(Locale.CANADA_FRENCH);
    String testingUidKey = "testing";
    UID<Priority> priorityUid = messageFactory.addPriority(testingUidKey);
    Priority priority = messageFactory.getPriority(priorityUid);
    assertNotNull(priority);
    assertTrue(priority.getName().matches("^.*fr\\.CA.*$"));

    messageSystem.getConfig().getLocalization().setDefaultLocale();
    assertTrue(priority.getName().matches("^.*default.*$"));
  }

  @Test
  public void MessageFactory_setLocaleToNotAvailableLocaleResourcesAndGetPriorityName_nameFromNoLocaleResourcesReturned()
      throws Exception {
    messageSystem.getConfig().getLocalization().setLocale(Locale.TRADITIONAL_CHINESE);
    String testingUidKey = "testing";
    UID<Priority> priorityUid = messageFactory.addPriority(testingUidKey);
    Priority priority = messageFactory.getPriority(priorityUid);
    assertNotNull(priority);
    assertTrue(priority.getName().matches("^.*default.*$"));

    messageSystem.getConfig().getLocalization().setDefaultLocale();
    assertTrue(priority.getName().matches("^.*default.*$"));
  }

  @Test
  public void MessageFactory_setLocaleGetPriorityNameSetDifferentLocaleGetPriorityName_nameforSpecifiedLocaleReturned()
      throws Exception {
    messageSystem.getConfig().getLocalization().setLocale(Locale.CANADA_FRENCH);
    String testingUidKey = "testing";
    UID<Priority> priorityUid = messageFactory.addPriority(testingUidKey);
    Priority priority = messageFactory.getPriority(priorityUid);
    assertNotNull(priority);
    assertTrue(priority.getName().matches("^.*fr\\.CA.*$"));

    messageSystem.getConfig().getLocalization().setLocale(Locale.GERMANY);
    assertFalse(priority.getName().matches("^.*fr\\.CA.*$"));
  }

  @Test
  public void MessageFactory_checkAllDefaultSystemPriorities_allDefaultPrioritiesAreAdded() {
    List<String> expectedPriorityKeyList =
        Arrays.asList("undefined", "debug", "info", "warn", "error");
    Set<UID<Priority>> priorityUidList = messageSystem.getPriorityUidList();

    assertNotNull(priorityUidList);
    for (String expectedPriorityUidKey : expectedPriorityKeyList) {
      UID<Priority> expectedPriorityUid = messageFactory.getPriorityUid(expectedPriorityUidKey);
      assertNotNull(expectedPriorityUidKey, expectedPriorityUid);
      assertTrue(expectedPriorityUidKey, priorityUidList.contains(expectedPriorityUid));
    }
  }

  // TODO What if name as configured in L10N is too long? How to validate all localization values
  // over all values.
  // test: priority defined in default cfg, but value is too long.
  // test: priority defined in current locale, but value is too long.
}
