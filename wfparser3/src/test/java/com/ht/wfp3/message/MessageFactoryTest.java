package com.ht.wfp3.message;

import static org.junit.Assert.assertEquals;
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
    String testingUidKey = "testing";
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
    assertTrue(priority.getName().matches("^.*de\\.DE.*$"));
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
    messageFactory.addTopic(
        "uidkey.cannot.be.too.long.or.the.message.system.will.throw.a.constraint.violation");

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
    String testingUidKey = "testing";
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
    assertTrue(topic.getName().matches("^.*de\\.DE.*$"));
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

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addDescriptionWithNullUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addDescription(null);
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addDescriptionWithEmptyUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addDescription(" \t");
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addDescriptionWithUidKeyContainingWhitespaceOrNewLines_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addDescription("not\nvalid");
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addDescriptionWithUidKeyIsTooLong_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addDescription(
        "uidkey.cannot.be.too.long.or.the.message.system.will.throw.a.constraint.violation");
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addDescriptionWithUidKeyContainingInvalidCharacters_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addDescription("invalidkey012345");
  }

  @Test
  public void MessageFactory_addTheTestingDescription_testingTopicIsAdded() throws Exception {
    String testingUidKey = "testing";
    UID<Description> descriptionUid = messageFactory.addDescription(testingUidKey);
    assertNotNull(descriptionUid);
    Description testingDescription = messageFactory.getDescription(descriptionUid);
    assertNotNull(testingDescription);
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addDescriptionWithDuplicateUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    String testingUidKey = "testing";
    messageFactory.addDescription(testingUidKey);

    messageFactory.addDescription(testingUidKey);
  }

  @Test
  public void MessageFactory_setLocaleToAvailableLocaleResourcesAndGetAllDescriptionTexts_nameFromAvailableResourcesReturned()
      throws Exception {
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.CANADA_FRENCH);
    String testingUnformattedUidKey = "testing.unformatted";
    String testingFormattedUidKey = "testing.formatted";
    String testingString = "Test Parameter";
    Integer testingInteger = Integer.valueOf(10);

    UID<Description> unformattedDescriptionUid =
        messageFactory.addDescription(testingUnformattedUidKey);
    Description unformattedDescription = messageFactory.getDescription(unformattedDescriptionUid);
    assertNotNull(unformattedDescription);
    assertNotNull(unformattedDescription.getUnformattedText());
    assertTrue(unformattedDescription.getUnformattedText().matches("^.*fr\\.CA.*$"));

    UID<Description> formattedDescriptionUid =
        messageFactory.addDescription(testingFormattedUidKey);
    Description formattedDescription = messageFactory.getDescription(formattedDescriptionUid);
    assertNotNull(formattedDescription);
    assertNotNull(formattedDescription.getFormattedText(testingString, testingInteger));
    assertTrue(formattedDescription.getFormattedText(testingString, testingInteger)
        .matches("^.*fr\\.CA.*$"));

    messageSystemInternal.getConfig().getLocalization().setDefaultLocale();
    assertTrue(unformattedDescription.getUnformattedText().matches("^.*default.*$"));
    assertTrue(formattedDescription.getFormattedText(testingString, testingInteger)
        .matches("^.*default.*$"));
  }

  @Test
  public void MessageFactory_setLocaleToNotAvailableLocaleResourcesAndGetAllDescriptionTexts_nameFromNoLocaleResourcesReturned()
      throws Exception {
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.TRADITIONAL_CHINESE);
    String testingUnformattedUidKey = "testing.unformatted";
    String testingFormattedUidKey = "testing.formatted";
    String testingString = "Test Parameter";
    Integer testingInteger = Integer.valueOf(10);

    UID<Description> unformattedDescriptionUid =
        messageFactory.addDescription(testingUnformattedUidKey);
    Description unformattedDescription = messageFactory.getDescription(unformattedDescriptionUid);
    assertNotNull(unformattedDescription);
    assertTrue(unformattedDescription.getUnformattedText().matches("^.*default.*$"));

    UID<Description> formattedDescriptionUid =
        messageFactory.addDescription(testingFormattedUidKey);
    Description formattedDescription = messageFactory.getDescription(formattedDescriptionUid);
    assertNotNull(formattedDescription);
    assertNotNull(formattedDescription.getFormattedText(testingString, testingInteger));
    assertTrue(formattedDescription.getFormattedText(testingString, testingInteger)
        .matches("^.*default.*$"));

    messageSystemInternal.getConfig().getLocalization().setDefaultLocale();
    assertTrue(unformattedDescription.getUnformattedText().matches("^.*default.*$"));
    assertTrue(formattedDescription.getFormattedText(testingString, testingInteger)
        .matches("^.*default.*$"));
  }

  @Test
  public void MessageFactory_setLocaleGetTopicNameSetDifferentLocaleGetAllDescriptionTexts_nameforSpecifiedLocaleReturned()
      throws Exception {
    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.CANADA_FRENCH);
    String testingUnformattedUidKey = "testing.unformatted";
    String testingFormattedUidKey = "testing.formatted";
    String testingString = "Test Parameter";
    Integer testingInteger = Integer.valueOf(10);

    UID<Description> unformattedDescriptionUid =
        messageFactory.addDescription(testingUnformattedUidKey);
    Description unformattedDescription = messageFactory.getDescription(unformattedDescriptionUid);
    assertNotNull(unformattedDescription);
    assertTrue(unformattedDescription.getUnformattedText().matches("^.*fr\\.CA.*$"));

    UID<Description> formattedDescriptionUid =
        messageFactory.addDescription(testingFormattedUidKey);
    Description formattedDescription = messageFactory.getDescription(formattedDescriptionUid);
    assertNotNull(formattedDescription);
    assertNotNull(formattedDescription.getFormattedText(testingString, testingInteger));
    assertTrue(formattedDescription.getFormattedText(testingString, testingInteger)
        .matches("^.*fr\\.CA.*$"));

    messageSystemInternal.getConfig().getLocalization().setLocale(Locale.GERMANY);
    assertTrue(unformattedDescription.getUnformattedText().matches("^.*de\\.DE.*$"));
    assertTrue(formattedDescription.getFormattedText(testingString, testingInteger)
        .matches("^.*de\\.DE.*$"));
  }

  @Test
  public void MessageFactory_checkAllDefaultSystemDescriptions_allDefaultTopicsAreAdded() {
    List<String> expectedDescriptionKeyList = Arrays.asList("undefined");
    Set<UID<Description>> descriptionUidList = messageFactory.getDescriptionUidSet();

    assertNotNull(descriptionUidList);
    for (String expectedDescriptionUidKey : expectedDescriptionKeyList) {
      UID<Description> expectedDescriptionUid =
          messageFactory.getDescriptionUid(expectedDescriptionUidKey);
      assertNotNull(expectedDescriptionUidKey, expectedDescriptionUid);
      assertTrue(expectedDescriptionUidKey, descriptionUidList.contains(expectedDescriptionUid));
    }
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addMessageWithNullTopicUid_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addMessage(null, messageFactory.addPriority("testing"),
        messageFactory.addDescription("testing.unformatted"));
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addMessageWithNullPriorityUid_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addMessage(messageFactory.addTopic("testing"), null,
        messageFactory.addDescription("testing.unformatted"));
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addMessageWithNullDescriptionUid_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.addMessage(messageFactory.addTopic("testing"),
        messageFactory.addPriority("testing"), null);
  }

  @Test
  public void MessageFactory_addOneMessageWithValidParameters_oneMessageIsAdded() throws Exception {
    UID<Topic> expectedTopicUid = messageFactory.addTopic("testing");
    UID<Priority> expectedPriorityUid = messageFactory.addPriority("testing");
    UID<Description> expectedDescriptionUid = messageFactory.addDescription("testing.unformatted");

    UID<Message> messageUid =
        messageFactory.addMessage(expectedTopicUid, expectedPriorityUid, expectedDescriptionUid);

    assertNotNull(messageUid);
    Message message = messageFactory.getMessage(messageUid);
    assertNotNull(message);
    assertEquals(expectedTopicUid, message.getTopic().getUid());
    assertEquals(expectedPriorityUid, message.getPriority().getUid());
    assertEquals(expectedDescriptionUid, message.getDescription().getUid());
  }

  @Test
  public void MessageFactory_addMultipleMessagesWithSameTopicButDifferentDescriptions_allMessagesAddedSuccessfully()
      throws Exception {
    UID<Topic> expectedTopicUid = messageFactory.addTopic("testing");
    UID<Priority> expectedPriorityUid = messageFactory.addPriority("testing");
    UID<Description> expectedFirstDescriptionUid =
        messageFactory.addDescription("testing.unformatted");
    UID<Description> expectedSecondDescriptionUid =
        messageFactory.addDescription("testing.formatted");

    UID<Message> firstMessageUid = messageFactory.addMessage(expectedTopicUid, expectedPriorityUid,
        expectedFirstDescriptionUid);
    UID<Message> secondMessageUid = messageFactory.addMessage(expectedTopicUid, expectedPriorityUid,
        expectedSecondDescriptionUid);

    assertNotNull(firstMessageUid);
    assertNotNull(secondMessageUid);

    Message firstMessage = messageFactory.getMessage(firstMessageUid);
    Message secondMessage = messageFactory.getMessage(secondMessageUid);

    assertNotNull(firstMessage);
    assertNotNull(secondMessage);
    assertEquals(expectedTopicUid, firstMessage.getTopic().getUid());
    assertEquals(expectedPriorityUid, firstMessage.getPriority().getUid());
    assertEquals(expectedFirstDescriptionUid, firstMessage.getDescription().getUid());
    assertEquals(expectedTopicUid, secondMessage.getTopic().getUid());
    assertEquals(expectedPriorityUid, secondMessage.getPriority().getUid());
    assertEquals(expectedSecondDescriptionUid, secondMessage.getDescription().getUid());
  }

  @Test
  public void MessageFactory_addMultipleMessagesWithDifferentTopicsButSameDescriptions_allMessagesAddedSuccessfully()
      throws Exception {
    UID<Topic> expectedFirstTopicUid = messageFactory.addTopic("testing");
    UID<Topic> expectedSecondTopicUid = messageFactory.addTopic("testing.two");
    UID<Priority> expectedPriorityUid = messageFactory.addPriority("testing");
    UID<Description> expectedDescriptionUid = messageFactory.addDescription("testing.unformatted");

    UID<Message> firstMessageUid = messageFactory.addMessage(expectedFirstTopicUid,
        expectedPriorityUid, expectedDescriptionUid);
    UID<Message> secondMessageUid = messageFactory.addMessage(expectedSecondTopicUid,
        expectedPriorityUid, expectedDescriptionUid);

    assertNotNull(firstMessageUid);
    assertNotNull(secondMessageUid);

    Message firstMessage = messageFactory.getMessage(firstMessageUid);
    Message secondMessage = messageFactory.getMessage(secondMessageUid);

    assertNotNull(firstMessage);
    assertNotNull(secondMessage);
    assertEquals(expectedFirstTopicUid, firstMessage.getTopic().getUid());
    assertEquals(expectedPriorityUid, firstMessage.getPriority().getUid());
    assertEquals(expectedDescriptionUid, firstMessage.getDescription().getUid());
    assertEquals(expectedSecondTopicUid, secondMessage.getTopic().getUid());
    assertEquals(expectedPriorityUid, secondMessage.getPriority().getUid());
    assertEquals(expectedDescriptionUid, secondMessage.getDescription().getUid());
  }

  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_addMultipleMessagesWithSameTopicSameDescriptionButDifferentPriorities_constraintViolationExceptionIsThrown()
      throws Exception {
    UID<Topic> expectedTopicUid = messageFactory.addTopic("testing");
    UID<Priority> expectedFirstPriorityUid = messageFactory.addPriority("testing");
    UID<Priority> expectedSecondPriorityUid = messageFactory.addPriority("testing.two");
    UID<Description> expectedDescriptionUid = messageFactory.addDescription("testing.unformatted");

    messageFactory.addMessage(expectedTopicUid, expectedFirstPriorityUid, expectedDescriptionUid);
    messageFactory.addMessage(expectedTopicUid, expectedSecondPriorityUid, expectedDescriptionUid);
  }

  @Test
  public void MessageFactory_checkAllDefaultSystemMessages_allDefaultMessagesAreAdded() {
    List<String> expectedMessageKeyList = Arrays.asList("undefined.undefined");
    Set<UID<Message>> messageUidSet = messageFactory.getMessageUidSet();

    assertNotNull(messageUidSet);
    for (String expectedMessageUidKey : expectedMessageKeyList) {
      UID<Message> expectedMessageUid = messageFactory.getMessageUid(expectedMessageUidKey);
      assertNotNull(expectedMessageUidKey, expectedMessageUid);
      assertTrue(expectedMessageUidKey, messageUidSet.contains(expectedMessageUid));
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
