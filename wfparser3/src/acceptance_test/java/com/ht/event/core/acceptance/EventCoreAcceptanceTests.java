package com.ht.event.core.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.event.core.AccumulatorSubscriberStub;
import com.ht.event.core.AssertEventCore;
import com.ht.event.core.Channel;
import com.ht.event.core.Event;
import com.ht.event.core.EventFactory;
import com.ht.event.core.Publisher;
import com.ht.event.core.Subject;
import com.ht.event.core.SubjectStub;
import com.ht.event.core.Subscriber;

public class EventCoreAcceptanceTests {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private EventFactory eventFactory;
  private AssertEventCore assertEventCore;
  private AccumulatorSubscriberStub accumulatorSubscriberStub;

  private Subscriber createSubscriberStub() {
    return new Subscriber();
  }

  private Channel createUnsupportedExternalChannelImplementation() {
    return new Channel() {

      @Override
      public boolean isEnabled() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public List<Subscriber> getSubscriberList() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public List<Publisher> getPublisherList() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public List<Event> getEventList() {
        throw new UnsupportedOperationException("method not supported by stub");
      }

      @Override
      public int compareTo(Channel o) {
        throw new UnsupportedOperationException("method not supported by stub");
      }
    };
  }

  private Subject createSubjectStub(String subjectName) {
    return new SubjectStub(subjectName);
  }

  @Before
  public void setup() {
    eventFactory = EventFactory.createFactory();
    assertEventCore = AssertEventCore.createAssertEventCore();
    accumulatorSubscriberStub = new AccumulatorSubscriberStub();
  }

  @Test
  public void EventCore_createTestingAssets_testingAssetsCreated() {
    assertNotNull(eventFactory);
    assertNotNull(assertEventCore);
    assertNotNull(accumulatorSubscriberStub);
  }

  @Test
  public void EventCore_createChannelWithValidChannelName_channelCreated() {
    final String expectedChannelName = "test.channel";
    boolean expectedIsEnabled = false;
    List<Event> expectedEventsList = Collections.emptyList();
    List<Publisher> expectedPublishersList = Collections.emptyList();
    List<Subscriber> expectedSubscribersList = Collections.emptyList();
    final Channel channel = eventFactory.createChannel(expectedChannelName);

    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsEnabled,
        expectedEventsList, expectedPublishersList, expectedSubscribersList, channel);
  }

  @Test
  public void EventCore_createEventWithValidChannelFamilyAndNameBeforeChannelIsEnabled_eventCreated() {
    final String expectedChannelName = "test.channel";
    boolean expectedIsEnabled = false;
    List<Publisher> expectedPublishersList = Collections.emptyList();
    List<Subscriber> expectedSubscribersList = Collections.emptyList();
    final Channel expectedChannel = eventFactory.createChannel(expectedChannelName);
    final String expectedEventFamily = "test.family";
    final String expectedEventName = "test.name";
    final boolean expectedIsDefined = false;

    Event event = eventFactory.createEvent(expectedChannel, expectedEventFamily, expectedEventName);

    assertEventCore.assertExpectedEvent(expectedChannel, expectedEventFamily, expectedEventName,
        event);
    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsEnabled,
        Arrays.asList(event), expectedPublishersList, expectedSubscribersList, expectedChannel);
    assertEventCore.assertExpectedSubject(expectedIsDefined, event.getSubject());
  }

  @Test
  public void EventCore_createPublisherWithValidChannelBeforeChannelIsEnabled_publisherCreated() {
    final String expectedChannelName = "test.channel";
    final boolean expectedIsEnabled = false;
    final List<Event> expectedEventsList = Collections.emptyList();
    final List<Subscriber> expectedSubscribersList = Collections.emptyList();
    final Channel expectedChannel = eventFactory.createChannel(expectedChannelName);

    Publisher publisher = eventFactory.createPublisher(expectedChannel);

    assertEventCore.assertExpectedPublisher(expectedChannel, publisher);
    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsEnabled,
        expectedEventsList, Arrays.asList(publisher), expectedSubscribersList, expectedChannel);
  }

  @Test
  public void EventCore_addValidSubscriberToChannelBeforeChannelIsEnabled_subscriberSuccessfullyRegistered() {
    final Subscriber expectedSubscriber = createSubscriberStub();
    final String expectedChannelName = "test.channel";
    final boolean expectedIsEnabled = false;
    final List<Event> expectedEventsList = Collections.emptyList();
    final List<Publisher> expectedPublishersList = Collections.emptyList();
    Channel channel = eventFactory.createChannel(expectedChannelName);

    eventFactory.addSubscriber(channel, expectedSubscriber);

    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsEnabled,
        expectedEventsList, expectedPublishersList, Arrays.asList(expectedSubscriber), channel);
  }

  @Test
  public void EventCore_publishValidEvent_subscriberReceivesEventPublish() {
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);

    eventFactory.enableChannel(channel);

    publisher.publish(expectedEvent);

    assertTrue(accumulatorSubscriberStub.getProcessedPublishedEventList().contains(expectedEvent));
  }

  @Test
  public void EventCore_unpublishValidEvent_subscriberReceivesEventUnPublish() {
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);

    publisher.publish(expectedEvent);
    assertTrue(accumulatorSubscriberStub.getProcessedPublishedEventList().contains(expectedEvent));

    publisher.unpublish(expectedEvent);
    assertTrue(
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().contains(expectedEvent));
  }

  @Test
  public void EventCore_createChannelWithNullChannelName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("channelName cannot equal null");

    eventFactory.createChannel(null);
  }

  @Test
  public void EventCore_createChannelWithEmptyChannelName_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "channelName can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    eventFactory.createChannel("\n");
  }

  @Test
  public void EventCore_createChannelWithInvalidCharactersInChannelName_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "channelName can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    eventFactory.createChannel("test.#@#@$channel");
  }

  @Test
  public void EventCore_createEventWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventChannel cannot equal null");

    eventFactory.createEvent(null, "test.family", "test.name");
  }

  @Test
  public void EventCore_createEventWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage("unknown eventChannel implementation");

    eventFactory.createEvent(createUnsupportedExternalChannelImplementation(), "test.family",
        "test.name");
  }

  @Test
  public void EventCore_createEventWithNullFamilyParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventFamily cannot equal null");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEvent(channel, null, "test.name");
  }

  @Test
  public void EventCore_createEventWithEmptyFamilyParameter_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "eventFamily can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEvent(channel, "", "test.name");
  }

  @Test
  public void EventCore_createEventWithInvalidCharactersInFamilyParameter_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "eventFamily can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEvent(channel, ".not.valid", "test.name");
  }

  @Test
  public void EventCore_createEventWithNullNameParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventName cannot equal null");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEvent(channel, "test.family", null);
  }

  @Test
  public void EventCore_createEventWithEmptyNameParameter_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "eventName can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEvent(channel, "test.family", " \n \t");
  }

  @Test
  public void EventCore_createEventWithInvalidCharactersInNameParameter_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "eventName can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEvent(channel, "test.family", "invalid.name.");
  }

  @Test
  public void EventCore_createPublisherWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventChannel cannot equal null");

    eventFactory.createPublisher(null);
  }

  @Test
  public void EventCore_createPublisherWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage("unknown eventChannel implementation");

    eventFactory.createPublisher(createUnsupportedExternalChannelImplementation());
  }

  @Test
  public void EventCore_addSubscriberWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventChannel cannot equal null");

    eventFactory.addSubscriber(null, createSubscriberStub());
  }

  @Test
  public void EventCore_addSubscriberWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage("unknown eventChannel implementation");

    eventFactory.addSubscriber(createUnsupportedExternalChannelImplementation(),
        createSubscriberStub());
  }

  @Test
  public void EventCore_addSubscriberWithNullSubscriberParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventSubscriber cannot equal null");
    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.addSubscriber(channel, null);
  }

  @Test
  public void EventCore_enableChannelWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventChannel cannot equal null");

    eventFactory.enableChannel(null);
  }

  @Test
  public void EventCore_enableChannelWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage("unknown eventChannel implementation");

    eventFactory.enableChannel(createUnsupportedExternalChannelImplementation());
  }

  @Test
  public void EventCore_publishEventOnChannelBeforeEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("channel is not enabled");

    Channel channel = eventFactory.createChannel("disabled.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    assertFalse(channel.isEnabled());

    publisher.publish(event);
  }

  @Test
  public void EventCore_unpublishEventOnChannelBeforeEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("channel is not enabled");

    Channel channel = eventFactory.createChannel("disabled.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    assertFalse(channel.isEnabled());

    publisher.unpublish(event);
  }

  @Test
  public void EventCore_createEventForChannelAfterEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot create events after enabling channel");

    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.enableChannel(channel);

    eventFactory.createEvent(channel, "test.family", "test.name");
  }

  @Test
  public void EventCore_createPublisherForChannelAfterEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot create publishers after enabling channel");

    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.enableChannel(channel);

    eventFactory.createPublisher(channel);
  }

  @Test
  public void EventCore_addSubscriberForChannelAfterEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot add subscribers after enabling channel");

    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.enableChannel(channel);

    eventFactory.addSubscriber(channel, createSubscriberStub());
  }

  @Test
  public void EventCore_createSameEventMultipleTimes_eventOnlyCreatedOnce() {
    final String expectedChannelName = "test.channel";
    final boolean expectedIsChannelEnabled = false;
    final List<Publisher> expectedPublishersList = Collections.emptyList();
    final List<Subscriber> expectedSubscribers = Collections.emptyList();
    final String expectedEventFamily = "test.family";
    final String expectedEventName = "test.name";
    final boolean expectedIsDefined = false;
    Channel expectedEventChannel = eventFactory.createChannel(expectedChannelName);

    Event firstEvent =
        eventFactory.createEvent(expectedEventChannel, expectedEventFamily, expectedEventName);
    Event secondEvent =
        eventFactory.createEvent(expectedEventChannel, expectedEventFamily, expectedEventName);

    assertEventCore.assertExpectedEvent(expectedEventChannel, expectedEventFamily,
        expectedEventName, firstEvent);
    assertEventCore.assertExpectedSubject(expectedIsDefined, firstEvent.getSubject());
    assertEventCore.assertExpectedEvent(expectedEventChannel, expectedEventFamily,
        expectedEventName, secondEvent);
    assertEventCore.assertExpectedSubject(expectedIsDefined, secondEvent.getSubject());
    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsChannelEnabled,
        Arrays.asList(firstEvent), expectedPublishersList, expectedSubscribers,
        expectedEventChannel);
    assertEquals(firstEvent, secondEvent);
    assertTrue(firstEvent == secondEvent);
  }

  @Test
  public void EventCore_addSameSubscriberToChannelMultipleTimes_subscriberOnlyAddedOnce() {
    final Subscriber expectedSubscriber = createSubscriberStub();
    final String expectedChannelName = "test.channel";
    final boolean expectedIsEnabled = false;
    final List<Event> expectedEventsList = Collections.emptyList();
    final List<Publisher> expectedPublishersList = Collections.emptyList();
    Channel channel = eventFactory.createChannel(expectedChannelName);

    eventFactory.addSubscriber(channel, expectedSubscriber);
    eventFactory.addSubscriber(channel, expectedSubscriber);

    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsEnabled,
        expectedEventsList, expectedPublishersList, Arrays.asList(expectedSubscriber), channel);
  }

  @Test
  public void EventCore_addSameSubscriberToMultipleChannels_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("subscriber already subscribed to channel ");
    Channel firstChannel = eventFactory.createChannel("first.channel");
    Channel secondChannel = eventFactory.createChannel("second.channel");
    Subscriber subscriber = createSubscriberStub();

    eventFactory.addSubscriber(firstChannel, subscriber);
    eventFactory.addSubscriber(secondChannel, subscriber);
  }

  @Test
  public void EventCore_publishSameValidEventTwice_subscriberOnlyNotifiedOfEventOnce() {
    final int expectedSubscriberProcessedPublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    String expectedEventFullyQualifiedName = event.getFullyQualifiedName();
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);

    publisher.publish(event);
    publisher.publish(event);

    assertEquals(expectedSubscriberProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEquals(expectedEventFullyQualifiedName,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(0).getFullyQualifiedName());
  }

  @Test
  public void EventCore_unpublishSameValidEventTwice_subscriberOnlyNotifiedOfUnbpublishOnce() {
    final int expectedSubscriberProcessedUnpublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    String expectedEventFullyQualifiedName = event.getFullyQualifiedName();
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);
    publisher.publish(event);

    publisher.unpublish(event);
    publisher.unpublish(event);
    assertEquals(expectedSubscriberProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
    assertEquals(expectedEventFullyQualifiedName, accumulatorSubscriberStub
        .getProcessedUnpublishedEventList().get(0).getFullyQualifiedName());
  }

  @Test
  public void EventCore_publishEventFromOneChannelToAnotherChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage(" is not defined in this channel");
    Channel channelOne = eventFactory.createChannel("test.channel.one");
    Channel channelTwo = eventFactory.createChannel("test.channel.two");
    Event eventForChannelOne = eventFactory.createEvent(channelOne, "test.family", "test.name");
    eventFactory.createEvent(channelTwo, "test.family", "test.name");
    Publisher publisherForChannelTwo = eventFactory.createPublisher(channelTwo);
    eventFactory.enableChannel(channelTwo);

    publisherForChannelTwo.publish(eventForChannelOne);
  }

  @Test
  public void EventCore_unpublishEventFromOneChannelInAnotherChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage(" is not defined in this channel");
    Channel channelOne = eventFactory.createChannel("test.channel.one");
    Channel channelTwo = eventFactory.createChannel("test.channel.two");
    Event eventForChannelOne = eventFactory.createEvent(channelOne, "test.family", "test.name");
    eventFactory.createEvent(channelTwo, "test.family", "test.name");
    Publisher publisherForChannelTwo = eventFactory.createPublisher(channelTwo);
    eventFactory.enableChannel(channelTwo);

    publisherForChannelTwo.unpublish(eventForChannelOne);
  }

  @Test
  public void EventCore_unpublishEventThatWasNeverPublished_subscribersReceiveNoNotification() {
    final int expectedSubscriberProcessedUnpublishedEventsSize = 0;
    Channel channel = eventFactory.createChannel("test.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);

    publisher.unpublish(event);

    assertEquals(expectedSubscriberProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
  }

  @Test
  public void EventCore_publishNullEvent_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("event cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = createSubscriberStub();
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.enableChannel(channel);

    publisher.publish(null);
  }

  @Test
  public void EventCore_unpublishNullEvent_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("event cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = createSubscriberStub();
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.enableChannel(channel);

    publisher.unpublish(null);
  }

  @Test
  public void EventCore_publishEventWithNullSubject_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("subject cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = createSubscriberStub();
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.enableChannel(channel);

    publisher.publish(event, (Subject) null);
  }

  @Test
  public void EventCore_unpublishEventWithNullSubject_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("subject cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = createSubscriberStub();
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.enableChannel(channel);

    publisher.unpublish(event, (Subject) null);
  }

  @Test
  public void EventCore_publishNullEventWithValidSubject_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("event cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = createSubscriberStub();
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.enableChannel(channel);

    publisher.publish(null, createSubjectStub("test.subject"));
  }

  @Test
  public void EventCore_unpublishNullEventWithValidSubject_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("event cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = createSubscriberStub();
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.enableChannel(channel);

    publisher.unpublish(null, createSubjectStub("test.subject"));
  }

  @Test
  public void EventCore_publishEventWithSubjectWhenChannelNotEnabled_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("channel is not enabled");
    Channel channel = eventFactory.createChannel("test.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = createSubscriberStub();
    eventFactory.addSubscriber(channel, subscriber);

    publisher.publish(event, createSubjectStub("test.subject"));
  }

  @Test
  public void EventCore_unpublishEventWithSubjectWhenChannelNotEnabled_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("channel is not enabled");
    Channel channel = eventFactory.createChannel("test.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = createSubscriberStub();
    eventFactory.addSubscriber(channel, subscriber);

    publisher.unpublish(event, createSubjectStub("test.subject"));
  }

  @Test
  public void EventCore_publishValidEventWithValidSubject_publishEventWithSubjectReceivedBySubscribers() {
    int expectedProcessedPubishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);

    publisher.publish(expectedEvent, expectedSubject);

    assertEquals(expectedProcessedPubishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubject, event);
  }

  @Test
  public void EventCore_unpublishValidPublishedEventWithValidSubject_unpublishEventWithSubjectReceivedBySubscribers() {
    int expectedProcessedUnpubishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);
    publisher.publish(expectedEvent, expectedSubject);

    publisher.unpublish(expectedEvent, expectedSubject);

    assertEquals(expectedProcessedUnpubishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubject, event);
  }

  @Test
  public void EventCore_publishSameEventWithSameSubjectTwice_publishEventWithSubjectReceivedOnceBySubscribers() {
    int expectedProcessedPublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);

    publisher.publish(expectedEvent, expectedSubject);
    publisher.publish(expectedEvent, expectedSubject);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubject, event);
  }

  @Test
  public void EventCore_unpublishSamePublishedEventWithSameSubjectTwice_unpublishEventWithSubjectReceivedOnceBySubscribers() {
    int expectedProcessedUnpublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);
    publisher.publish(expectedEvent, expectedSubject);

    publisher.unpublish(expectedEvent, expectedSubject);
    publisher.unpublish(expectedEvent, expectedSubject);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubject, event);
  }

  @Test
  public void EventCore_publishSameEventWithDifferentSubjects_publishForAllTheSameEventsWithDifferentSubjectsReceivedBySubscribers() {
    int expectedProcessedPublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Subject expectedSubjectOne = createSubjectStub("test.subject.one");
    Subject expectedSubjectTwo = createSubjectStub("test.subject.two");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);

    publisher.publish(expectedEvent, expectedSubjectOne);
    publisher.publish(expectedEvent, expectedSubjectTwo);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubjectOne, event);

    event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubjectTwo, event);
  }

  @Test
  public void EventCore_unpublishSameEventWithDifferentSubjects_unpublishForAllTheSameEventsWithDifferentSubjectsReceivedBySubscribers() {
    int expectedProcessedUnpublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Subject expectedSubjectOne = createSubjectStub("test.subject.one");
    Subject expectedSubjectTwo = createSubjectStub("test.subject.two");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);
    publisher.publish(expectedEvent, expectedSubjectOne);
    publisher.publish(expectedEvent, expectedSubjectTwo);

    publisher.unpublish(expectedEvent, expectedSubjectOne);
    publisher.unpublish(expectedEvent, expectedSubjectTwo);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubjectOne, event);

    event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubjectTwo, event);
  }

  @Test
  public void EventCore_publishDifferentEventsWithTheSameSubjects_publishForAllDifferentEventsWithTheSameSubjectsReceivedBySubscribers() {
    int expectedProcessedPublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEventOne = eventFactory.createEvent(channel, "test.family", "test.name.one");
    Event expectedEventTwo = eventFactory.createEvent(channel, "test.family", "test.name.two");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);

    publisher.publish(expectedEventOne, expectedSubject);
    publisher.publish(expectedEventTwo, expectedSubject);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventOne, expectedSubject, event);

    event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEventTwo, expectedSubject, event);
  }

  @Test
  public void EventCore_unpublishDifferentEventsWithTheSameSubjects_unpublishForAllDifferentEventsWithTheSameSubjectsReceivedBySubscribers() {
    int expectedProcessedUnpublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEventOne = eventFactory.createEvent(channel, "test.family", "test.name.one");
    Event expectedEventTwo = eventFactory.createEvent(channel, "test.family", "test.name.two");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);
    publisher.publish(expectedEventOne, expectedSubject);
    publisher.publish(expectedEventTwo, expectedSubject);

    publisher.unpublish(expectedEventOne, expectedSubject);
    publisher.unpublish(expectedEventTwo, expectedSubject);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventOne, expectedSubject, event);

    event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEventTwo, expectedSubject, event);
  }

  @Test
  public void EventCore_publishEventWithSubjectAndWithNoSubject_publishForBothEventWithSubjectAndNoSubjectReceivedBySubscribers() {
    int expectedProcessedPublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);

    publisher.publish(expectedEvent, expectedSubject);
    publisher.publish(expectedEvent);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubject, event);

    event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEvent, event);
  }

  @Test
  public void EventCore_unpublishEventWithSubjectAndWithNoSubject_unpublishForBothEventWithSubjectAndNoSubjectReceivedBySubscribers() {
    int expectedProcessedUnpublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.enableChannel(channel);
    publisher.publish(expectedEvent, expectedSubject);
    publisher.publish(expectedEvent);

    publisher.unpublish(expectedEvent, expectedSubject);
    publisher.unpublish(expectedEvent);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEvent, expectedSubject, event);

    event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEvent, event);
  }

  @Test
  public void EventCore_multiplePublishersPublishDifferentEventsToOneSubscriber_allPublishedEventsReceivedBySubscriber() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_multiplePublishersUnpublishDifferentEventsToOneSubscriber_allUnpublishedEventsReceivedBySubscriber() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_multiplePublishersPublishSameEventToOneSubscriber_onePublishedEventReceivedBySubscriber() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_multiplePublishersUnpublishSameEventToOneSubscriber_oneUnpublishedEventReceivedBySubscriber() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_onePublisherPublishesOneEventToMultipleSubscribers_onePublishedEventReceivedByAllSubscribers() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_onePublisherUnpublishesOneEventToMultipleSubscribers_oneUnpublishedEventReceivedByAllSubscribers() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_multiplePublishersPublishDifferentEventsToMultipleSubscribers_allPublishedEventsReceivedByAllSubscribers() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_multiplePublishersUnpublishDifferentEventsToMultipleSubscribers_allUnpublishedEventsReceivedByAllSubscribers() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_multiplePublishersPublisheSameEventToMultipleSubscribers_onePublishedEventReceivedByAllSubscribers() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_multiplePublishersUnpublisheSameEventToMultipleSubscribers_oneUnpublishedEventReceivedByAllSubscribers() {
    fail("not implemented");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_publisherPublishesEventUnpublishesEventThenPublishesEventAgain_oneEachOfPublishedUnpublishedAndPublishedEventReceivedBySubscriber() {
    fail("not implemented");
  }


  /*
   * Rough list of test scenarios:
   * 
   * Register two publishers. First publisher publishes multiple events. Second publisher publishes
   * multiple events. The first and second publishers share some events in common. First publisher
   * requests to unpublish all its published events. All events that are exclusive to the first
   * publisher are unpublished. All events that are exclusive to the second publisher are NOT
   * unpublished. All events that are shared between the first and second publisher are NOT
   * unpublished.
   * 
   * !!! MORE TEST SCENARIOS!!!: The factories are no longer singletons. What if you use an instance
   * of ANY of the classes in EventCore from one factory in another factory?
   * 
   * !!! MORE TEST SCENARIOS!!!: creating Event instances with different combinations of Channel,
   * Family, and Name to ensure they are handle properly, i.e. unique / not unique, as appropriate.
   * Similar For Channel.
   * 
   * !!! MORE TEST SCENARIOS!!! What if the subscriber process of publish / unpublish event throws
   * an exception?
   * 
   * Register two subscribers. Publish a number of events. First subscriber requests a resend of all
   * published events. First subscriber receives all published events in the order they were
   * originally sent. Second subscriber does not.
   * 
   * Also, it would be useful for testing and debugging to get a list of published events and who
   * published them. Maybe a single method to get an event report for a given channel that lists all
   * defined events, whether the events are currently published and who published them.
   * 
   * Maybe you should also have a ChannelEvent and handler specifically for handling Channel
   * lifecycle events, such as being enabled and disabled.
   * 
   */

}
