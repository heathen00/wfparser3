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

  private MessageSystemInternal messageSystemInternal;
  private MessageFactory messageFactory;

  @Before
  public void setup() {
    messageSystemInternal = (MessageSystemInternal) MessageSystem.createMessageSystem();
    messageSystemInternal.resetToDefault();
    messageFactory = messageSystemInternal.getMessageFactory();
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
        "uidkey.cannot.be.too.long.or.the.message.system.will.throw.a.constraint.violation");
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
    assertNotNull(priorityUid);
    Priority testingPriority = messageFactory.getPriority(priorityUid);
    assertNotNull(testingPriority);
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
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.CANADA_FRENCH);
    String testingUidKey = "testing";
    UID<Priority> priorityUid = messageFactory.addPriority(testingUidKey);
    Priority priority = messageFactory.getPriority(priorityUid);
    assertNotNull(priority);
    assertTrue(priority.getName().matches("^.*fr\\.CA.*$"));

    messageSystemInternal.getConfig().getLocalization().setDefaultLocale();
    assertTrue(priority.getName().matches("^.*default.*$"));
  }

  @Test
  public void MessageFactory_setLocaleToNotAvailableLocaleResourcesAndGetPriorityName_nameFromNoLocaleResourcesReturned()
      throws Exception {
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.TRADITIONAL_CHINESE);
    String testingUidKey = "testing";
    UID<Priority> priorityUid = messageFactory.addPriority(testingUidKey);
    Priority priority = messageFactory.getPriority(priorityUid);
    assertNotNull(priority);
    assertTrue(priority.getName().matches("^.*default.*$"));

    messageSystemInternal.getConfig().getLocalization().setDefaultLocale();
    assertTrue(priority.getName().matches("^.*default.*$"));
  }

  @Test
  public void MessageFactory_setLocaleGetPriorityNameSetDifferentLocaleGetPriorityName_nameforSpecifiedLocaleReturned()
      throws Exception {
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.CANADA_FRENCH);
    String testingUidKey = "testing";
    UID<Priority> priorityUid = messageFactory.addPriority(testingUidKey);
    Priority priority = messageFactory.getPriority(priorityUid);
    assertNotNull(priority);
    assertTrue(priority.getName().matches("^.*fr\\.CA.*$"));

    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.GERMANY);
    assertFalse(priority.getName().matches("^.*fr\\.CA.*$"));
  }

  @Test
  public void MessageFactory_checkAllDefaultSystemPriorities_allDefaultPrioritiesAreAdded() {
    List<String> expectedPriorityKeyList =
        Arrays.asList("undefined", "debug", "info", "warn", "error");
    Set<UID<Priority>> priorityUidList = messageFactory.getPriorityUidSet();

    assertNotNull(priorityUidList);
    for (String expectedPriorityUidKey : expectedPriorityKeyList) {
      UID<Priority> expectedPriorityUid = messageFactory.getPriorityUid(expectedPriorityUidKey);
      assertNotNull(expectedPriorityUidKey, expectedPriorityUid);
      assertTrue(expectedPriorityUidKey, priorityUidList.contains(expectedPriorityUid));
    }
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addTopicWithNullUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addTopic(null);
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addTopicWithEmptyUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addTopic(" \t");
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addTopicWithUidKeyContainingWhitespaceOrNewLines_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addTopic("not\tvalid");
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addTopicWithUidKeyIsTooLong_constraintViolationExceptionIsThrown()
      throws Exception {
    try {
      messageFactory.addTopic(
          "uidkey.cannot.be.too.long.or.the.message.system.will.throw.a.constraint.violation");
    } catch (ConstraintViolationException cve) {
      System.out.println(cve);
      throw cve;
    }

  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addTopicWithUidKeyContainingInvalidCharacters_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addTopic("invalid_key_012345");
  }

  @Test
  public void MessageFactory_addTheTestingTopic_testingTopicIsAdded() throws Exception {
    String testingUidKey = "testing";
    UID<Topic> topicUid = messageFactory.addTopic(testingUidKey);
    assertNotNull(topicUid);
    Topic testingTopic = messageFactory.getTopic(topicUid);
    assertNotNull(testingTopic);
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addTopicWithDuplicateUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    String testingUidKey = "testing.defined.in.all";
    messageFactory.addTopic(testingUidKey);

    messageFactory.addTopic(testingUidKey);
  }

  @Test
  public void MessageFactory_setLocaleToAvailableLocaleResourcesAndGetTopicName_nameFromAvailableResourcesReturned()
      throws Exception {
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.CANADA_FRENCH);
    String testingUidKey = "testing";
    UID<Topic> topicUid = messageFactory.addTopic(testingUidKey);
    Topic topic = messageFactory.getTopic(topicUid);
    assertNotNull(topic);
    assertNotNull(topic.getName());
    assertTrue(topic.getName().matches("^.*fr\\.CA.*$"));

    messageSystemInternal.getConfig().getLocalization().setDefaultLocale();
    assertTrue(topic.getName().matches("^.*default.*$"));
  }

  @Test
  public void MessageFactory_setLocaleToNotAvailableLocaleResourcesAndGetTopicName_nameFromNoLocaleResourcesReturned()
      throws Exception {
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.TRADITIONAL_CHINESE);
    String testingUidKey = "testing";
    UID<Topic> topicUid = messageFactory.addTopic(testingUidKey);
    Topic topic = messageFactory.getTopic(topicUid);
    assertNotNull(topic);
    assertTrue(topic.getName().matches("^.*default.*$"));

    messageSystemInternal.getConfig().getLocalization().setDefaultLocale();
    assertTrue(topic.getName().matches("^.*default.*$"));
  }

  @Test
  public void MessageFactory_setLocaleGetTopicNameSetDifferentLocaleGetTopicName_nameforSpecifiedLocaleReturned()
      throws Exception {
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.CANADA_FRENCH);
    String testingUidKey = "testing";
    UID<Topic> topicUid = messageFactory.addTopic(testingUidKey);
    Topic topic = messageFactory.getTopic(topicUid);
    assertNotNull(topic);
    assertTrue(topic.getName().matches("^.*fr\\.CA.*$"));

    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.GERMANY);
    assertFalse(topic.getName().matches("^.*fr\\.CA.*$"));
  }

  @Test
  public void MessageFactory_checkAllDefaultSystemTopic_allDefaultTopicsAreAdded() {
    List<String> expectedTopicKeyList = Arrays.asList("undefined", "system");
    Set<UID<Topic>> topicUidList = messageFactory.getTopicUidSet();

    assertNotNull(topicUidList);
    for (String expectedTopicUidKey : expectedTopicKeyList) {
      UID<Topic> expectedTopicUid = messageFactory.getTopicUid(expectedTopicUidKey);
      assertNotNull(expectedTopicUidKey, expectedTopicUid);
      assertTrue(expectedTopicUidKey, topicUidList.contains(expectedTopicUid));
    }
  }

  // TODO What if name as configured in L10N is too long? How to validate all localization values
  // over all values.
  // test: priority defined in default cfg, but value is too long.
  // test: priority defined in current locale, but value is too long.
  // TODO you will need to handle "null", too, both being passed in AND out.
  // test: you need a test for priority and topic (and others when you implement them) to test for
  // adding a topic whose configuration does NOT exist in the locale bundle. Should throw an
  // exception.
}
