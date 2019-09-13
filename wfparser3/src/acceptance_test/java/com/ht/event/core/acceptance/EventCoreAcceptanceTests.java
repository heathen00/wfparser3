package com.ht.event.core.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.event.core.AccumulatorSubscriberStub;
import com.ht.event.core.AssertEventCore;
import com.ht.event.core.AssertNaturalOrder;
import com.ht.event.core.AssertNaturalOrder.Relation;
import com.ht.event.core.Channel;
import com.ht.event.core.Event;
import com.ht.event.core.EventDescription;
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
  private AssertNaturalOrder assertNaturalOrder;

  private Channel createUnsupportedExternalChannelImplementation() {
    return new Channel() {

      @Override
      public boolean isOpen() {
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

      @Override
      public List<EventDescription> getEventDescriptionList() {
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
    accumulatorSubscriberStub =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber");
    assertNaturalOrder = AssertNaturalOrder.createAssertNaturalOrder();
  }

  @Test
  public void EventCore_createTestingAssets_testingAssetsCreated() {
    assertNotNull(eventFactory);
    assertNotNull(assertEventCore);
    assertNotNull(accumulatorSubscriberStub);
    assertNotNull(assertNaturalOrder);
  }

  @Test
  public void EventCore_createChannelWithValidChannelName_channelCreated() {
    final String expectedChannelName = "test.channel";
    boolean expectedIsOpen = false;
    List<Event> expectedEventsList = Collections.emptyList();
    List<Publisher> expectedPublishersList = Collections.emptyList();
    List<Subscriber> expectedSubscribersList = Collections.emptyList();
    final Channel channel = eventFactory.createChannel(expectedChannelName);

    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsOpen, expectedEventsList,
        expectedPublishersList, expectedSubscribersList, channel);
  }

  @Test
  public void EventCore_createEventDescriptionWithValidChannelFamilyAndNameBeforeChannelIsOpen_eventDescriptionCreated() {
    final String expectedChannelName = "test.channel";
    boolean expectedIsOpen = false;
    List<Publisher> expectedPublishersList = Collections.emptyList();
    List<Subscriber> expectedSubscribersList = Collections.emptyList();
    final Channel expectedChannel = eventFactory.createChannel(expectedChannelName);
    final String expectedfamily = "test.family";
    final String expectedEventName = "test.name";

    EventDescription event =
        eventFactory.createEventDescription(expectedChannel, expectedfamily, expectedEventName);

    assertEventCore.assertExpectedEventDescription(expectedChannel, expectedfamily,
        expectedEventName, event);
    assertEventCore.assertExpectedChannel_NEW(expectedChannelName, expectedIsOpen,
        Arrays.asList(event), expectedPublishersList, expectedSubscribersList, expectedChannel);
  }

  @Test
  public void EventCore_createPublisherWithValidChannelBeforeChannelIsOpen_publisherCreated() {
    final String expectedChannelName = "test.channel";
    final boolean expectedIsOpen = false;
    final List<Event> expectedEventsList = Collections.emptyList();
    final List<Subscriber> expectedSubscribersList = Collections.emptyList();
    final Channel expectedChannel = eventFactory.createChannel(expectedChannelName);

    Publisher publisher = eventFactory.createPublisher(expectedChannel);

    assertEventCore.assertExpectedPublisher(expectedChannel, publisher);
    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsOpen, expectedEventsList,
        Arrays.asList(publisher), expectedSubscribersList, expectedChannel);
  }

  @Test
  public void EventCore_addValidSubscriberToChannelBeforeChannelIsOpen_subscriberSuccessfullyRegistered() {
    final Subscriber expectedSubscriber = accumulatorSubscriberStub;
    final String expectedChannelName = "test.channel";
    final boolean expectedIsOpen = false;
    final List<Event> expectedEventsList = Collections.emptyList();
    final List<Publisher> expectedPublishersList = Collections.emptyList();
    Channel channel = eventFactory.createChannel(expectedChannelName);

    eventFactory.addSubscriber(channel, expectedSubscriber);

    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsOpen, expectedEventsList,
        expectedPublishersList, Arrays.asList(expectedSubscriber), channel);
  }

  @Test
  public void EventCore_publishValidEvent_subscriberReceivesEventPublish() {
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);

    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescription);

    assertTrue(accumulatorSubscriberStub.getProcessedPublishedEventList()
        .contains(expectedEventDescription));
  }

  @Test
  public void EventCore_unpublishValidEvent_subscriberReceivesEventUnPublish() {
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescription);
    assertTrue(accumulatorSubscriberStub.getProcessedPublishedEventList()
        .contains(expectedEventDescription));

    publisher.unpublish(expectedEventDescription);
    assertTrue(accumulatorSubscriberStub.getProcessedUnpublishedEventList()
        .contains(expectedEventDescription));
  }

  @Test
  public void EventCore_createChannelWithNullChannelName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("name cannot equal null");

    eventFactory.createChannel(null);
  }

  @Test
  public void EventCore_createChannelWithEmptyChannelName_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "name can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    eventFactory.createChannel("\n");
  }

  @Test
  public void EventCore_createChannelWithInvalidCharactersInChannelName_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "name can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    eventFactory.createChannel("test.#@#@$channel");
  }

  @Test
  public void EventCore_createEventDescriptionWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("channel cannot equal null");

    eventFactory.createEventDescription(null, "test.family", "test.name");
  }

  @Test
  public void EventCore_createEventDescriptionWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage("unknown channel implementation");

    eventFactory.createEventDescription(createUnsupportedExternalChannelImplementation(),
        "test.family", "test.name");
  }

  @Test
  public void EventCore_createEventDescriptionWithNullFamilyParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("family cannot equal null");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEventDescription(channel, null, "test.name");
  }

  @Test
  public void EventCore_createEventDescriptionWithEmptyFamilyParameter_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "family can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEventDescription(channel, "", "test.name");
  }

  @Test
  public void EventCore_createEventDescriptionWithInvalidCharactersInFamilyParameter_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "family can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEventDescription(channel, ".not.valid", "test.name");
  }

  @Test
  public void EventCore_createEventDescriptionWithNullNameParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("name cannot equal null");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEventDescription(channel, "test.family", null);
  }

  @Test
  public void EventCore_createEventDescriptionWithEmptyNameParameter_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "name can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEventDescription(channel, "test.family", " \n \t");
  }

  @Test
  public void EventCore_createEventDescriptionWithInvalidCharactersInNameParameter_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage(
        "name can only contain lower case letters, numbers, and periods, and cannot start or end with a period");

    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.createEventDescription(channel, "test.family", "invalid.name.");
  }

  @Test
  public void EventCore_createPublisherWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("channel cannot equal null");

    eventFactory.createPublisher(null);
  }

  @Test
  public void EventCore_createPublisherWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage("unknown channel implementation");

    eventFactory.createPublisher(createUnsupportedExternalChannelImplementation());
  }

  @Test
  public void EventCore_addSubscriberWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("channel cannot equal null");

    eventFactory.addSubscriber(null, accumulatorSubscriberStub);
  }

  @Test
  public void EventCore_addSubscriberWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage("unknown channel implementation");

    eventFactory.addSubscriber(createUnsupportedExternalChannelImplementation(),
        accumulatorSubscriberStub);
  }

  @Test
  public void EventCore_addSubscriberWithNullSubscriberParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventSubscriber cannot equal null");
    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.addSubscriber(channel, null);
  }

  @Test
  public void EventCore_openChannelWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("channel cannot equal null");

    eventFactory.openChannel(null);
  }

  @Test
  public void EventCore_openChannelWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    thrown.expect(InvalidParameterException.class);
    thrown.expectMessage("unknown channel implementation");

    eventFactory.openChannel(createUnsupportedExternalChannelImplementation());
  }

  @Test
  public void EventCore_publishEventOnChannelBeforeEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("channel is not open");

    Channel channel = eventFactory.createChannel("disabled.channel");
    EventDescription event =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    assertFalse(channel.isOpen());

    publisher.publish(event);
  }

  @Test
  public void EventCore_unpublishEventOnChannelBeforeEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("channel is not open");

    Channel channel = eventFactory.createChannel("disabled.channel");
    EventDescription event =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    assertFalse(channel.isOpen());

    publisher.unpublish(event);
  }

  @Test
  public void EventCore_createEventForChannelAfterEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot create events after enabling channel");

    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.openChannel(channel);

    eventFactory.createEventDescription(channel, "test.family", "test.name");
  }

  @Test
  public void EventCore_createPublisherForChannelAfterEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot create publishers after enabling channel");

    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.openChannel(channel);

    eventFactory.createPublisher(channel);
  }

  @Test
  public void EventCore_addSubscriberForChannelAfterEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot add subscribers after enabling channel");

    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.openChannel(channel);

    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
  }

  @Test
  public void EventCore_createSameEventMultipleTimes_eventOnlyCreatedOnce() {
    final String expectedChannelName = "test.channel";
    final boolean expectedIsChannelOpen = false;
    final List<Publisher> expectedPublishersList = Collections.emptyList();
    final List<Subscriber> expectedSubscribers = Collections.emptyList();
    final String expectedfamily = "test.family";
    final String expectedEventName = "test.name";
    final boolean expectedIsDefined = false;
    Channel expectedchannel = eventFactory.createChannel(expectedChannelName);

    EventDescription firstEventDescription =
        eventFactory.createEventDescription(expectedchannel, expectedfamily, expectedEventName);
    EventDescription secondEventDescription =
        eventFactory.createEventDescription(expectedchannel, expectedfamily, expectedEventName);

    assertEventCore.assertExpectedEventDescription(expectedchannel, expectedfamily,
        expectedEventName, firstEventDescription);
    assertEventCore.assertExpectedEventDescription(expectedchannel, expectedfamily,
        expectedEventName, secondEventDescription);
    assertEventCore.assertExpectedChannel_NEW(expectedChannelName, expectedIsChannelOpen,
        Arrays.asList(firstEventDescription), expectedPublishersList, expectedSubscribers,
        expectedchannel);
    assertEquals(firstEventDescription, secondEventDescription);
    assertTrue(firstEventDescription == secondEventDescription);
  }

  @Test
  public void EventCore_addSameSubscriberToChannelMultipleTimes_subscriberOnlyAddedOnce() {
    final Subscriber expectedSubscriber = accumulatorSubscriberStub;
    final String expectedChannelName = "test.channel";
    final boolean expectedIsOpen = false;
    final List<Event> expectedEventsList = Collections.emptyList();
    final List<Publisher> expectedPublishersList = Collections.emptyList();
    Channel channel = eventFactory.createChannel(expectedChannelName);

    eventFactory.addSubscriber(channel, expectedSubscriber);
    eventFactory.addSubscriber(channel, expectedSubscriber);

    assertEventCore.assertExpectedChannel(expectedChannelName, expectedIsOpen, expectedEventsList,
        expectedPublishersList, Arrays.asList(expectedSubscriber), channel);
  }

  @Test
  public void EventCore_addSameSubscriberToMultipleChannels_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("subscriber already subscribed to channel ");
    Channel firstChannel = eventFactory.createChannel("first.channel");
    Channel secondChannel = eventFactory.createChannel("second.channel");

    eventFactory.addSubscriber(firstChannel, accumulatorSubscriberStub);
    eventFactory.addSubscriber(secondChannel, accumulatorSubscriberStub);
  }

  @Test
  public void EventCore_publishSameValidEventTwice_subscriberOnlyNotifiedOfEventOnce() {
    final int expectedSubscriberProcessedPublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription eventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    String expectedEventFullyQualifiedName = eventDescription.getFullyQualifiedName();
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.publish(eventDescription);
    publisher.publish(eventDescription);

    assertEquals(expectedSubscriberProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEquals(expectedEventFullyQualifiedName,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(0).getFullyQualifiedName());
  }

  @Test
  public void EventCore_unpublishSameValidEventTwice_subscriberOnlyNotifiedOfUnbpublishOnce() {
    final int expectedSubscriberProcessedUnpublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription eventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    String expectedEventFullyQualifiedName = eventDescription.getFullyQualifiedName();
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisher.publish(eventDescription);

    publisher.unpublish(eventDescription);
    publisher.unpublish(eventDescription);
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
    EventDescription eventDescriptionForChannelOne =
        eventFactory.createEventDescription(channelOne, "test.family", "test.name");
    eventFactory.createEventDescription(channelTwo, "test.family", "test.name");
    Publisher publisherForChannelTwo = eventFactory.createPublisher(channelTwo);
    eventFactory.openChannel(channelTwo);

    publisherForChannelTwo.publish(eventDescriptionForChannelOne);
  }

  @Test
  public void EventCore_unpublishEventFromOneChannelInAnotherChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage(" is not defined in this channel");
    Channel channelOne = eventFactory.createChannel("test.channel.one");
    Channel channelTwo = eventFactory.createChannel("test.channel.two");
    EventDescription eventDescriptionForChannelOne =
        eventFactory.createEventDescription(channelOne, "test.family", "test.name");
    eventFactory.createEventDescription(channelTwo, "test.family", "test.name");
    Publisher publisherForChannelTwo = eventFactory.createPublisher(channelTwo);
    eventFactory.openChannel(channelTwo);

    publisherForChannelTwo.unpublish(eventDescriptionForChannelOne);
  }

  @Test
  public void EventCore_unpublishEventThatWasNeverPublished_subscribersReceiveNoNotification() {
    final int expectedSubscriberProcessedUnpublishedEventsSize = 0;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription eventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.unpublish(eventDescription);

    assertEquals(expectedSubscriberProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
  }

  @Test
  public void EventCore_publishNullEvent_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("event cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = accumulatorSubscriberStub;
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.openChannel(channel);

    publisher.publish(null);
  }

  @Test
  public void EventCore_unpublishNullEvent_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("event cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = accumulatorSubscriberStub;
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.openChannel(channel);

    publisher.unpublish(null);
  }

  @Test
  public void EventCore_publishEventWithNullSubject_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("subject cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription eventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = accumulatorSubscriberStub;
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.openChannel(channel);

    publisher.publish(eventDescription, (Subject) null);
  }

  @Test
  public void EventCore_unpublishEventWithNullSubject_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("subject cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription eventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = accumulatorSubscriberStub;
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.openChannel(channel);

    publisher.unpublish(eventDescription, (Subject) null);
  }

  @Test
  public void EventCore_publishNullEventWithValidSubject_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("event cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = accumulatorSubscriberStub;
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.openChannel(channel);

    publisher.publish(null, createSubjectStub("test.subject"));
  }

  @Test
  public void EventCore_unpublishNullEventWithValidSubject_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("event cannot be null");
    Channel channel = eventFactory.createChannel("test.channel");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = accumulatorSubscriberStub;
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.openChannel(channel);

    publisher.unpublish(null, createSubjectStub("test.subject"));
  }

  @Test
  public void EventCore_publishEventWithSubjectWhenChannelNotOpen_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("channel is not open");
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription eventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = accumulatorSubscriberStub;
    eventFactory.addSubscriber(channel, subscriber);

    publisher.publish(eventDescription, createSubjectStub("test.subject"));
  }

  @Test
  public void EventCore_unpublishEventWithSubjectWhenChannelNotOpen_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("channel is not open");
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription eventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    Subscriber subscriber = accumulatorSubscriberStub;
    eventFactory.addSubscriber(channel, subscriber);

    publisher.unpublish(eventDescription, createSubjectStub("test.subject"));
  }

  @Test
  public void EventCore_publishValidEventWithValidSubject_publishEventWithSubjectReceivedBySubscribers() {
    int expectedProcessedPubishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescription, expectedSubject);

    assertEquals(expectedProcessedPubishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubject, event);
  }

  @Test
  public void EventCore_unpublishValidPublishedEventWithValidSubject_unpublishEventWithSubjectReceivedBySubscribers() {
    int expectedProcessedUnpubishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisher.publish(expectedEventDescription, expectedSubject);

    publisher.unpublish(expectedEventDescription, expectedSubject);

    assertEquals(expectedProcessedUnpubishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubject, event);
  }

  @Test
  public void EventCore_publishSameEventWithSameSubjectTwice_publishEventWithSubjectReceivedOnceBySubscribers() {
    int expectedProcessedPublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescription, expectedSubject);
    publisher.publish(expectedEventDescription, expectedSubject);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubject, event);
  }

  @Test
  public void EventCore_unpublishSamePublishedEventWithSameSubjectTwice_unpublishEventWithSubjectReceivedOnceBySubscribers() {
    int expectedProcessedUnpublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisher.publish(expectedEventDescription, expectedSubject);

    publisher.unpublish(expectedEventDescription, expectedSubject);
    publisher.unpublish(expectedEventDescription, expectedSubject);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubject, event);
  }

  @Test
  public void EventCore_publishSameEventWithDifferentSubjects_publishForAllTheSameEventsWithDifferentSubjectsReceivedBySubscribers() {
    int expectedProcessedPublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject expectedSubjectOne = createSubjectStub("test.subject.one");
    Subject expectedSubjectTwo = createSubjectStub("test.subject.two");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescription, expectedSubjectOne);
    publisher.publish(expectedEventDescription, expectedSubjectTwo);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubjectOne, event);

    event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubjectTwo, event);
  }

  @Test
  public void EventCore_unpublishSameEventWithDifferentSubjects_unpublishForAllTheSameEventsWithDifferentSubjectsReceivedBySubscribers() {
    int expectedProcessedUnpublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject expectedSubjectOne = createSubjectStub("test.subject.one");
    Subject expectedSubjectTwo = createSubjectStub("test.subject.two");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisher.publish(expectedEventDescription, expectedSubjectOne);
    publisher.publish(expectedEventDescription, expectedSubjectTwo);

    publisher.unpublish(expectedEventDescription, expectedSubjectOne);
    publisher.unpublish(expectedEventDescription, expectedSubjectTwo);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubjectOne, event);

    event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubjectTwo, event);
  }

  @Test
  public void EventCore_publishDifferentEventsWithTheSameSubjects_publishForAllDifferentEventsWithTheSameSubjectsReceivedBySubscribers() {
    int expectedProcessedPublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescriptionOne =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    EventDescription expectedEventDescriptionTwo =
        eventFactory.createEventDescription(channel, "test.family", "test.name.two");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescriptionOne, expectedSubject);
    publisher.publish(expectedEventDescriptionTwo, expectedSubject);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne, expectedSubject, event);

    event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo, expectedSubject, event);
  }

  @Test
  public void EventCore_unpublishDifferentEventsWithTheSameSubjects_unpublishForAllDifferentEventsWithTheSameSubjectsReceivedBySubscribers() {
    int expectedProcessedUnpublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescriptionOne =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    EventDescription expectedEventDescriptionTwo =
        eventFactory.createEventDescription(channel, "test.family", "test.name.two");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisher.publish(expectedEventDescriptionOne, expectedSubject);
    publisher.publish(expectedEventDescriptionTwo, expectedSubject);

    publisher.unpublish(expectedEventDescriptionOne, expectedSubject);
    publisher.unpublish(expectedEventDescriptionTwo, expectedSubject);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne, expectedSubject, event);

    event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo, expectedSubject, event);
  }

  @Test
  public void EventCore_publishEventWithSubjectAndWithNoSubject_publishForBothEventWithSubjectAndNoSubjectReceivedBySubscribers() {
    int expectedProcessedPublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescription, expectedSubject);
    publisher.publish(expectedEventDescription);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubject, event);

    event = accumulatorSubscriberStub.getProcessedPublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEventDescription, event);
  }

  @Test
  public void EventCore_unpublishEventWithSubjectAndWithNoSubject_unpublishForBothEventWithSubjectAndNoSubjectReceivedBySubscribers() {
    int expectedProcessedUnpublishedEventsSize = 2;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject expectedSubject = createSubjectStub("test.subject");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisher.publish(expectedEventDescription, expectedSubject);
    publisher.publish(expectedEventDescription);

    publisher.unpublish(expectedEventDescription, expectedSubject);
    publisher.unpublish(expectedEventDescription);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());

    Event event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0);
    assertEventCore.assertExpectedEvent(expectedEventDescription, expectedSubject, event);

    event = accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(1);
    assertEventCore.assertExpectedEvent(expectedEventDescription, event);
  }

  @Test
  public void EventCore_multiplePublishersPublishDifferentEventsToOneSubscriber_allPublishedEventsReceivedBySubscriber() {
    int expectedProcessedPublishedEventsSize = 3;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescriptionOne =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    EventDescription expectedEventDescriptionTwo =
        eventFactory.createEventDescription(channel, "test.family", "test.name.two");
    EventDescription expectedEventDescriptionThree =
        eventFactory.createEventDescription(channel, "test.family", "test.name.three");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    Publisher publisherThree = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisherOne.publish(expectedEventDescriptionOne);
    publisherTwo.publish(expectedEventDescriptionTwo);
    publisherThree.publish(expectedEventDescriptionThree);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionThree,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(2));
  }

  @Test
  public void EventCore_multiplePublishersUnpublishDifferentEventsToOneSubscriber_allUnpublishedEventsReceivedBySubscriber() {
    int expectedProcessedUnpublishedEventsSize = 3;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescriptionOne =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    EventDescription expectedEventDescriptionTwo =
        eventFactory.createEventDescription(channel, "test.family", "test.name.two");
    EventDescription expectedEventDescriptionThree =
        eventFactory.createEventDescription(channel, "test.family", "test.name.three");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    Publisher publisherThree = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisherOne.publish(expectedEventDescriptionOne);
    publisherTwo.publish(expectedEventDescriptionTwo);
    publisherThree.publish(expectedEventDescriptionThree);

    publisherOne.unpublish(expectedEventDescriptionOne);
    publisherTwo.unpublish(expectedEventDescriptionTwo);
    publisherThree.unpublish(expectedEventDescriptionThree);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionThree,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(2));
  }

  @Test
  public void EventCore_multiplePublishersPublishSameEventToOneSubscriber_onePublishedEventReceivedBySubscriber() {
    int expectedProcessedPublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    Publisher publisherThree = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisherOne.publish(expectedEventDescription);
    publisherTwo.publish(expectedEventDescription);
    publisherThree.publish(expectedEventDescription);

    assertEquals(expectedProcessedPublishedEventsSize,
        accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(0));
  }

  @Test
  public void EventCore_multiplePublishersUnpublishSameEventToOneSubscriber_oneUnpublishedEventReceivedBySubscriber() {
    int expectedProcessedUnpublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    Publisher publisherThree = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisherOne.publish(expectedEventDescription);
    publisherTwo.publish(expectedEventDescription);
    publisherThree.publish(expectedEventDescription);

    publisherOne.unpublish(expectedEventDescription);
    publisherTwo.unpublish(expectedEventDescription);
    publisherThree.unpublish(expectedEventDescription);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0));
  }

  @Test
  public void EventCore_onePublisherPublishesOneEventToMultipleSubscribers_onePublishedEventReceivedByAllSubscribers() {
    int expectedProcessedPublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    Publisher publisher = eventFactory.createPublisher(channel);
    AccumulatorSubscriberStub subscriberOne =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.one");
    AccumulatorSubscriberStub subscriberTwo =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.two");
    AccumulatorSubscriberStub subscriberThree =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.three");
    eventFactory.addSubscriber(channel, subscriberOne);
    eventFactory.addSubscriber(channel, subscriberTwo);
    eventFactory.addSubscriber(channel, subscriberThree);
    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescription);

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberOne.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberOne.getProcessedPublishedEventList().get(0));

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberTwo.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberTwo.getProcessedPublishedEventList().get(0));

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberThree.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberThree.getProcessedPublishedEventList().get(0));
  }

  @Test
  public void EventCore_onePublisherUnpublishesOneEventToMultipleSubscribers_oneUnpublishedEventReceivedByAllSubscribers() {
    int expectedProcessedUnpublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    Publisher publisher = eventFactory.createPublisher(channel);
    AccumulatorSubscriberStub subscriberOne =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.one");
    AccumulatorSubscriberStub subscriberTwo =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.two");
    AccumulatorSubscriberStub subscriberThree =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.three");
    eventFactory.addSubscriber(channel, subscriberOne);
    eventFactory.addSubscriber(channel, subscriberTwo);
    eventFactory.addSubscriber(channel, subscriberThree);
    eventFactory.openChannel(channel);
    publisher.publish(expectedEventDescription);

    publisher.unpublish(expectedEventDescription);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberOne.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberOne.getProcessedUnpublishedEventList().get(0));

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberTwo.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberTwo.getProcessedUnpublishedEventList().get(0));

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberThree.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberThree.getProcessedUnpublishedEventList().get(0));
  }

  @Test
  public void EventCore_multiplePublishersPublishDifferentEventsToMultipleSubscribers_allPublishedEventsReceivedByAllSubscribers() {
    int expectedProcessedPublishedEventsSize = 3;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescriptionOne =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    EventDescription expectedEventDescriptionTwo =
        eventFactory.createEventDescription(channel, "test.family", "test.name.two");
    EventDescription expectedEventDescriptionThree =
        eventFactory.createEventDescription(channel, "test.family", "test.name.three");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    Publisher publisherThree = eventFactory.createPublisher(channel);
    AccumulatorSubscriberStub subscriberOne =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.one");
    AccumulatorSubscriberStub subscriberTwo =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.two");
    AccumulatorSubscriberStub subscriberThree =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.three");
    eventFactory.addSubscriber(channel, subscriberOne);
    eventFactory.addSubscriber(channel, subscriberTwo);
    eventFactory.addSubscriber(channel, subscriberThree);
    eventFactory.openChannel(channel);

    publisherOne.publish(expectedEventDescriptionOne);
    publisherTwo.publish(expectedEventDescriptionTwo);
    publisherThree.publish(expectedEventDescriptionThree);

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberOne.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne,
        subscriberOne.getProcessedPublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo,
        subscriberOne.getProcessedPublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionThree,
        subscriberOne.getProcessedPublishedEventList().get(2));

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberTwo.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne,
        subscriberTwo.getProcessedPublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo,
        subscriberTwo.getProcessedPublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionThree,
        subscriberTwo.getProcessedPublishedEventList().get(2));

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberThree.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne,
        subscriberThree.getProcessedPublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo,
        subscriberThree.getProcessedPublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionThree,
        subscriberThree.getProcessedPublishedEventList().get(2));
  }

  @Test
  public void EventCore_multiplePublishersUnpublishDifferentEventsToMultipleSubscribers_allUnpublishedEventsReceivedByAllSubscribers() {
    int expectedProcessedUnpublishedEventsSize = 3;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescriptionOne =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    EventDescription expectedEventDescriptionTwo =
        eventFactory.createEventDescription(channel, "test.family", "test.name.two");
    EventDescription expectedEventDescriptionThree =
        eventFactory.createEventDescription(channel, "test.family", "test.name.three");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    Publisher publisherThree = eventFactory.createPublisher(channel);
    AccumulatorSubscriberStub subscriberOne =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.one");
    AccumulatorSubscriberStub subscriberTwo =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.two");
    AccumulatorSubscriberStub subscriberThree =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.three");
    eventFactory.addSubscriber(channel, subscriberOne);
    eventFactory.addSubscriber(channel, subscriberTwo);
    eventFactory.addSubscriber(channel, subscriberThree);
    eventFactory.openChannel(channel);
    publisherOne.publish(expectedEventDescriptionOne);
    publisherTwo.publish(expectedEventDescriptionTwo);
    publisherThree.publish(expectedEventDescriptionThree);

    publisherOne.unpublish(expectedEventDescriptionOne);
    publisherTwo.unpublish(expectedEventDescriptionTwo);
    publisherThree.unpublish(expectedEventDescriptionThree);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberOne.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne,
        subscriberOne.getProcessedUnpublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo,
        subscriberOne.getProcessedUnpublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionThree,
        subscriberOne.getProcessedUnpublishedEventList().get(2));

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberTwo.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne,
        subscriberTwo.getProcessedUnpublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo,
        subscriberTwo.getProcessedUnpublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionThree,
        subscriberTwo.getProcessedUnpublishedEventList().get(2));

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberThree.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescriptionOne,
        subscriberThree.getProcessedUnpublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionTwo,
        subscriberThree.getProcessedUnpublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(expectedEventDescriptionThree,
        subscriberThree.getProcessedUnpublishedEventList().get(2));
  }

  @Test
  public void EventCore_multiplePublishersPublisheSameEventToMultipleSubscribers_onePublishedEventReceivedByAllSubscribers() {
    int expectedProcessedPublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    Publisher publisherThree = eventFactory.createPublisher(channel);
    AccumulatorSubscriberStub subscriberOne =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.one");
    AccumulatorSubscriberStub subscriberTwo =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.two");
    AccumulatorSubscriberStub subscriberThree =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.three");
    eventFactory.addSubscriber(channel, subscriberOne);
    eventFactory.addSubscriber(channel, subscriberTwo);
    eventFactory.addSubscriber(channel, subscriberThree);
    eventFactory.openChannel(channel);

    publisherOne.publish(expectedEventDescription);
    publisherTwo.publish(expectedEventDescription);
    publisherThree.publish(expectedEventDescription);

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberOne.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberOne.getProcessedPublishedEventList().get(0));

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberTwo.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberTwo.getProcessedPublishedEventList().get(0));

    assertEquals(expectedProcessedPublishedEventsSize,
        subscriberThree.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberThree.getProcessedPublishedEventList().get(0));
  }

  @Test
  public void EventCore_multiplePublishersUnpublisheSameEventToMultipleSubscribers_oneUnpublishedEventReceivedByAllSubscribers() {
    int expectedProcessedUnpublishedEventsSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name.one");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    Publisher publisherThree = eventFactory.createPublisher(channel);
    AccumulatorSubscriberStub subscriberOne =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.one");
    AccumulatorSubscriberStub subscriberTwo =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.two");
    AccumulatorSubscriberStub subscriberThree =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.three");
    eventFactory.addSubscriber(channel, subscriberOne);
    eventFactory.addSubscriber(channel, subscriberTwo);
    eventFactory.addSubscriber(channel, subscriberThree);
    eventFactory.openChannel(channel);
    publisherOne.publish(expectedEventDescription);
    publisherTwo.publish(expectedEventDescription);
    publisherThree.publish(expectedEventDescription);

    publisherOne.unpublish(expectedEventDescription);
    publisherTwo.unpublish(expectedEventDescription);
    publisherThree.unpublish(expectedEventDescription);

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberOne.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberOne.getProcessedUnpublishedEventList().get(0));

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberTwo.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberTwo.getProcessedUnpublishedEventList().get(0));

    assertEquals(expectedProcessedUnpublishedEventsSize,
        subscriberThree.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        subscriberThree.getProcessedUnpublishedEventList().get(0));
  }

  @Test
  public void EventCore_publisherPublishesEventUnpublishesEventThenPublishesEventAgain_oneEachOfPublishedUnpublishedAndPublishedEventReceivedBySubscriber() {
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription expectedEventDescription =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);

    publisher.publish(expectedEventDescription);

    assertEquals(1, accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(0));

    publisher.unpublish(expectedEventDescription);

    assertEquals(1, accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0));

    publisher.publish(expectedEventDescription);

    assertEquals(2, accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(expectedEventDescription,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(1));
  }

  @Test
  public void EventCore_multiplePublishersPublishOverlappingEventsAndOnePublisherUnpublishesOverlappingEvents_overlappingEventsNotUnpublishedUntilUnpublishedByAllPublishers() {
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription eventFromPublisherOne =
        eventFactory.createEventDescription(channel, "test.family", "event.from.publisher.one");
    EventDescription eventFromPublisherTwo =
        eventFactory.createEventDescription(channel, "test.family", "event.from.publisher.two");
    EventDescription eventFromBothPublishers =
        eventFactory.createEventDescription(channel, "test.family", "event.from.both.publishers");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisherOne.publish(eventFromPublisherOne);
    publisherOne.publish(eventFromBothPublishers);
    publisherTwo.publish(eventFromPublisherTwo);
    publisherTwo.publish(eventFromBothPublishers);

    assertEquals(3, accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(eventFromPublisherOne,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(0));
    assertEventCore.assertExpectedEvent(eventFromBothPublishers,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(1));
    assertEventCore.assertExpectedEvent(eventFromPublisherTwo,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(2));

    publisherOne.unpublish(eventFromPublisherOne);
    publisherOne.unpublish(eventFromBothPublishers);

    assertEquals(1, accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(eventFromPublisherOne,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(0));

    publisherTwo.unpublish(eventFromBothPublishers);

    assertEquals(2, accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(eventFromBothPublishers,
        accumulatorSubscriberStub.getProcessedUnpublishedEventList().get(1));
  }

  @Test
  public void EventCore_publisherAttemptsToUnpublishAnotherPublishersEvent_subscriberReceivesNoUnpublishEvent() {
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription event =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisherOne.publish(event);

    publisherTwo.unpublish(event);

    assertEquals(1, accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(event,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(0));
    assertEquals(0, accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
  }

  @Test
  public void EventCore_publisherAttemptsToUnpublishAnotherPublishersEventWithSubject_subscriberReceivesNoUnpublishEvent() {
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription event =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Subject subject = createSubjectStub("test.subject");
    Publisher publisherOne = eventFactory.createPublisher(channel);
    Publisher publisherTwo = eventFactory.createPublisher(channel);
    eventFactory.addSubscriber(channel, accumulatorSubscriberStub);
    eventFactory.openChannel(channel);
    publisherOne.publish(event, subject);

    publisherTwo.unpublish(event, subject);

    assertEquals(1, accumulatorSubscriberStub.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(event, subject,
        accumulatorSubscriberStub.getProcessedPublishedEventList().get(0));
    assertEquals(0, accumulatorSubscriberStub.getProcessedUnpublishedEventList().size());
  }

  @Test
  public void EventCore_createEventUsingChannelFromAnotherFactoryInstance_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage(" does not exist in this factory");
    EventFactory eventFactoryOne = EventFactory.createFactory();
    EventFactory eventFactoryTwo = EventFactory.createFactory();
    Channel channel = eventFactoryOne.createChannel("test.channel");

    eventFactoryTwo.createEventDescription(channel, "test.family", "test.name");
  }

  @Test
  public void EventCore_createPublisherUsingChannelFromAnotherFactoryInstance_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage(" does not exist in this factory");
    EventFactory eventFactoryOne = EventFactory.createFactory();
    EventFactory eventFactoryTwo = EventFactory.createFactory();
    Channel channel = eventFactoryOne.createChannel("test.channel");

    eventFactoryTwo.createPublisher(channel);
  }

  @Test
  public void EventCore_addSubscriberUsingChannelFromAnotherFactoryInstance_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage(" does not exist in this factory");
    EventFactory eventFactoryOne = EventFactory.createFactory();
    EventFactory eventFactoryTwo = EventFactory.createFactory();
    Channel channel = eventFactoryOne.createChannel("test.channel");

    eventFactoryTwo.addSubscriber(channel, accumulatorSubscriberStub);
  }

  @Test
  public void EventCore_openChannelFromAnotherFactoryInstance_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage(" does not exist in this factory");
    EventFactory eventFactoryOne = EventFactory.createFactory();
    EventFactory eventFactoryTwo = EventFactory.createFactory();
    Channel channel = eventFactoryOne.createChannel("test.channel");

    eventFactoryTwo.openChannel(channel);
  }

  @Test
  public void EventCore_publishValidEventAndOneSubscriberThrowsUncheckedException_eventCoreDoesNotDieAndAllOtherSubscribersReceiveEvent() {
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription event =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    AccumulatorSubscriberStub stableSubscriberOne =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.stable.one");
    Subscriber unstableSubscriber = new Subscriber() {

      @Override
      public void processPublishEvent(Event event) {
        throw new NullPointerException();
      }

      @Override
      public String getName() {
        return "test.unstable.subscriber";
      }
    };
    AccumulatorSubscriberStub stableSubscriberTwo =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.subscriber.stable.two");
    eventFactory.addSubscriber(channel, stableSubscriberOne);
    eventFactory.addSubscriber(channel, unstableSubscriber);
    eventFactory.addSubscriber(channel, stableSubscriberTwo);
    eventFactory.openChannel(channel);

    publisher.publish(event);

    assertEquals(1, stableSubscriberOne.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(event,
        stableSubscriberOne.getProcessedPublishedEventList().get(0));
    assertEquals(1, stableSubscriberTwo.getProcessedPublishedEventList().size());
    assertEventCore.assertExpectedEvent(event,
        stableSubscriberTwo.getProcessedPublishedEventList().get(0));
  }

  @Test
  public void EventCore_unpublishValidEventAndOneSubscriberThrowsUncheckedException_eventCoreDoesNotDieAndAllOtherSubscribersReceiveEvent() {
    Channel channel = eventFactory.createChannel("test.channel");
    EventDescription event =
        eventFactory.createEventDescription(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    AccumulatorSubscriberStub stableSubscriberOne =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.stable.subscriber.one");
    Subscriber unstableSubscriber = new Subscriber() {

      @Override
      public void processUnpublishEvent(Event event) {
        throw new NullPointerException();
      }

      @Override
      public String getName() {
        return "test.unstable.subscriber";
      }
    };
    AccumulatorSubscriberStub stableSubscriberTwo =
        AccumulatorSubscriberStub.createAccumulatorSubscriber("test.stable.subscriber.two");
    eventFactory.addSubscriber(channel, stableSubscriberOne);
    eventFactory.addSubscriber(channel, unstableSubscriber);
    eventFactory.addSubscriber(channel, stableSubscriberTwo);
    eventFactory.openChannel(channel);
    publisher.publish(event);

    publisher.unpublish(event);

    assertEquals(1, stableSubscriberOne.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(event,
        stableSubscriberOne.getProcessedUnpublishedEventList().get(0));
    assertEquals(1, stableSubscriberTwo.getProcessedUnpublishedEventList().size());
    assertEventCore.assertExpectedEvent(event,
        stableSubscriberTwo.getProcessedUnpublishedEventList().get(0));
  }

  @Test
  public void EventCore_ensureChannelRespectsNaturalOrderContract_naturalOrderContractRespected() {
    Channel leftOperand;
    Channel rightOperand;

    leftOperand = eventFactory.createChannel("test.channel");
    rightOperand = eventFactory.createChannel("test.channel");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.EQ, rightOperand);

    leftOperand = eventFactory.createChannel("aaa");
    rightOperand = eventFactory.createChannel("bbb");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.LT, rightOperand);

    leftOperand = eventFactory.createChannel("zzz");
    rightOperand = eventFactory.createChannel("yyy");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.GT, rightOperand);
  }

  @Test
  public void EventCore_ensureEventRespectsNaturalOrderContract_naturalOrderConstractRespected() {
    EventDescription leftOperand;
    EventDescription rightOperand;

    leftOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "same.family", "same.name");
    rightOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "same.family", "same.name");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.EQ, rightOperand);

    leftOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "same.family", "aaa");
    rightOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "same.family", "zzz");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.LT, rightOperand);

    leftOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "same.family", "zzz");
    rightOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "same.family", "aaa");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.GT, rightOperand);

    leftOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "aaa", "zzz");
    rightOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "zzz", "aaa");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.LT, rightOperand);

    leftOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "zzz", "aaa");
    rightOperand = eventFactory.createEventDescription(eventFactory.createChannel("same.channel"),
        "aaa", "zzz");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.GT, rightOperand);

    leftOperand =
        eventFactory.createEventDescription(eventFactory.createChannel("aaa"), "zzz", "zzz");
    rightOperand =
        eventFactory.createEventDescription(eventFactory.createChannel("zzz"), "aaa", "aaa");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.LT, rightOperand);

    leftOperand =
        eventFactory.createEventDescription(eventFactory.createChannel("zzz"), "aaa", "aaa");
    rightOperand =
        eventFactory.createEventDescription(eventFactory.createChannel("aaa"), "zzz", "zzz");
    assertNaturalOrder.assertExpectedRelation(leftOperand, Relation.GT, rightOperand);
  }

  /*
   * Rough:
   * 
   * Looking at the current Event test cases, maybe it would make more sense to differentiate the
   * types between when the Event instances are being created and when they are being processed
   * since it seems a little awkward right now. Perhaps during creation the type could be
   * Description and during processing it would remain Event. However, the Event would become a
   * composite containing Description and optionally Subject. The behaviour would be more intuitive.
   * 
   * There is a lot of duplicate code in the channel implementation. Or code that is mostly exactly
   * the same. It could be generalized and the duplicates could be removed. It might be easier,
   * though, if done after differentiating between Description and Event.
   * 
   * Could the channel be broken down into parameter validation / cache / processing layers, too,
   * using the decorator pattern in a similar way to the factory?
   * 
   * Register two subscribers. Publish a number of events. First subscriber requests a resend of all
   * published events. First subscriber receives all published events in the order they were
   * originally sent. Second subscriber does not. As a point of clarification, the objective is NOT
   * to replay the entire publish/unpublish history for all events since the channel was opened. The
   * objective is to simply send all current published events to the subscriber that requested it.
   * 
   * Also, it would be useful for testing and debugging to get a list of published events and who
   * published them. Maybe a single method to get an event report for a given channel that lists all
   * defined events, whether the events are currently published and who published them. This would
   * be an internal interface for debugging purposes only.
   * 
   * Maybe you should also have channel specific Events that are published in band in each channel
   * so that clients can receive events like the channel is opened / closed. The events would be no
   * different in type from standard client defined events. They could be differentiated from the
   * client defined events by being in their own family. You could even enforce a specific naming
   * convention like "event.core.*", so perhaps "event.core.channel.info",
   * "event.core.system.error", etc. You can report events like when subscribers misbehave. Should
   * the messages be in band? Does that make sense from the perspective of the subscribers? The
   * subscribers should only know about what is relevant to them, so perhaps the messages should be
   * limited to those events that all subscribers care about such as when the channel is opened or
   * closed.
   * 
   * Maybe implement an InternalSystem that is a singleton that is created on demand. This
   * InternalSystem could have the instance cache and all factories could reference it, so for
   * scenarios where Channels and Events are being created by different factories aren't an issue.
   * The InternalSystem as its name implies would not be accessible by clients. Existing test cases
   * where UnsupportedOperationExceptions are being thrown would need to be rewritten. There is some
   * potential for a memory leak with this design since if nothing is referencing the factory, then
   * all the resources created through this factory would still exist. I would need "delete" options
   * at the Factory level which, in turn implies "disable". After that, I could potentially
   * implement some intelligence in the InternalSystem itself for monitoring to ensure instances are
   * still being used and closing them otherwise.
   * 
   * After all the refactoring, implement something to get rid of the two warnings that have been
   * lingering forever.
   */

}
