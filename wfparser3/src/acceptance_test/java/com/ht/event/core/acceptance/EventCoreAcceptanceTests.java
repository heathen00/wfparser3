package com.ht.event.core.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ht.event.core.Channel;
import com.ht.event.core.Event;
import com.ht.event.core.EventFactory;
import com.ht.event.core.Publisher;
import com.ht.event.core.Subscriber;
import com.ht.uid.UidFactory;

public class EventCoreAcceptanceTests {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private EventFactory eventFactory;

  private Subscriber createSubscriberStub() {
    return new Subscriber();
  }

  @Before
  public void setup() {
    eventFactory = EventFactory.createFactory(UidFactory.createUidFactory());
  }

  @Test
  public void EventCore_createTestingAssets_testingAssetsCreated() {
    assertNotNull(eventFactory);
  }

  @Test
  public void EventCore_createChannelWithValidChannelName_channelCreated() {
    final String expectedChannelName = "test.channel";
    final Channel channel = eventFactory.createChannel(expectedChannelName);

    assertNotNull(channel);
    assertEquals(expectedChannelName, channel.getName());
    assertNotNull(channel.getUid());
    assertEquals(expectedChannelName, channel.getUid().getKey());
    assertTrue(channel.getEventUidList().isEmpty());
    assertTrue(channel.getPublisherList().isEmpty());
    assertTrue(channel.getSubscriberList().isEmpty());
    assertFalse(channel.isEnabled());
  }

  @Test
  public void EventCore_createEventWithValidChannelFamilyAndNameBeforeChannelIsEnabled_eventCreated() {
    final Channel expectedChannel = eventFactory.createChannel("test.channel");
    final String expectedEventFamily = "test.family";
    final String expectedEventName = "test.name";
    final String expectedUidKey =
        String.join(".", expectedChannel.getName(), expectedEventFamily, expectedEventName);
    final int expectedEvenUidListSize = 1;

    Event event = eventFactory.createEvent(expectedChannel, expectedEventFamily, expectedEventName);

    assertNotNull(event);
    assertEquals(expectedChannel, event.getChannel());
    assertEquals(expectedEventFamily, event.getFamily());
    assertEquals(expectedEventName, event.getName());
    assertNotNull(event.getUid());
    assertEquals(expectedUidKey, event.getUid().getKey());
    assertTrue(expectedChannel.getEventUidList().contains(event.getUid()));
    assertEquals(expectedEvenUidListSize, expectedChannel.getEventUidList().size());
  }

  @Test
  public void EventCore_createPublisherWithValidChannelBeforeChannelIsEnabled_publisherCreated() {
    final Channel expectedChannel = eventFactory.createChannel("test.channel");
    final int expectedPublisherListSize = 1;

    Publisher publisher = eventFactory.createPublisher(expectedChannel);

    assertNotNull(publisher);
    assertEquals(expectedChannel, publisher.getChannel());
    assertTrue(expectedChannel.getPublisherList().contains(publisher));
    assertEquals(expectedPublisherListSize, expectedChannel.getPublisherList().size());
  }

  @Test
  public void EventCore_addValidSubscriberToChannelBeforeChannelIsEnabled_subscriberSuccessfullyRegistered() {
    final Subscriber expectedSubscriber = createSubscriberStub();
    final int expectedSubscriberListSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");

    eventFactory.addSubscriber(channel, expectedSubscriber);

    assertTrue(channel.getSubscriberList().contains(expectedSubscriber));
    assertEquals(expectedSubscriberListSize, channel.getSubscriberList().size());
  }

  @Test
  public void EventCore_publishValidEvent_subscriberReceivesEventPublish() {
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    List<String> expectedProcessedEventList = new ArrayList<>();
    Subscriber subscriber = new Subscriber() {

      @Override
      public void processPublishEvent(Event event) {
        expectedProcessedEventList.add(event.getFullyQualifiedName());
      }

      @Override
      public void processUnpublishEvent(Event event) {}
    };
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.enableChannel(channel);

    publisher.publish(expectedEvent.getUid());

    assertTrue(expectedProcessedEventList.contains(expectedEvent.getFullyQualifiedName()));
  }

  @Test
  public void EventCore_unpublishValidEvent_subscriberReceivesEventUnPublish() {
    Channel channel = eventFactory.createChannel("test.channel");
    Event expectedEvent = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    List<String> expectedProcessedEventList = new ArrayList<>();
    Subscriber subscriber = new Subscriber() {

      @Override
      public void processUnpublishEvent(Event event) {
        expectedProcessedEventList.remove(event.getFullyQualifiedName());
      }

      @Override
      public void processPublishEvent(Event event) {
        expectedProcessedEventList.add(event.getFullyQualifiedName());
      }
    };
    eventFactory.addSubscriber(channel, subscriber);
    eventFactory.enableChannel(channel);

    publisher.publish(expectedEvent.getUid());
    assertTrue(expectedProcessedEventList.contains(expectedEvent.getFullyQualifiedName()));

    publisher.unpublish(expectedEvent.getUid());
    assertFalse(expectedProcessedEventList.contains(expectedEvent.getFullyQualifiedName()));
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

    Channel channel = eventFactory.createChannel("\n");
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
  @Ignore("not worked on yet")
  public void EventCore_createEventWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    fail("not implemented yet");
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
  @Ignore("not worked on yet")
  public void EventCore_createPublisherWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  public void EventCore_addSubscriberWithNullChannelParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("eventChannel cannot equal null");

    eventFactory.addSubscriber(null, createSubscriberStub());
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_addSubscriberWithUnknownExternalChannelImplementation_invalidParameterExceptionIsThrown() {
    fail("not implemented yet");
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
  @Ignore("not worked on yet")
  public void EventCore_createSameEventMultipleTimes_eventOnlyCreatedOnce() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_addSameSubscriberToChannelMultipleTimes_subscriberOnlyAddedOnce() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_addSameSubscriberToMultipleChannels_unsupportedOperationExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  public void EventCore_publishEventOnChannelBeforeEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot publish events unless channel enabled");

    Channel channel = eventFactory.createChannel("disabled.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    assertFalse(channel.isEnabled());

    publisher.publish(event.getUid());
  }

  @Test
  public void EventCore_unpublishEventOnChannelBeforeEnablingChannel_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("cannot unpublish events unless channel enabled");

    Channel channel = eventFactory.createChannel("disabled.channel");
    Event event = eventFactory.createEvent(channel, "test.family", "test.name");
    Publisher publisher = eventFactory.createPublisher(channel);
    assertFalse(channel.isEnabled());

    publisher.unpublish(event.getUid());
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
  @Ignore("not worked on yet")
  public void EventCore_publishSameValidEventTwice_subscriberOnlyNotifiedOfEventOnce() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_publishEventFromOneChannelToAnotherChannel_unsupportedOperationExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void EventCore_unpublishEventThatWasNeverPublished_subscribersReceiveNoNotification() {
    fail("not implemented yet");
  }

  /*
   * Rough list of test scenarios:
   * 
   * !!! MORE TEST SCENARIOS!!!: creating Event instances with different combinations of Channel,
   * Family, and Name to ensure they are handle properly, i.e. unique / not unique, as appropriate.
   * 
   * publish event with a subject. Event with subject is received by subscribers in channel.
   * 
   * publish event with a null subject. Same behaviour as publishing event without subject.
   * 
   * publish same event with same subject multiple times. Event with subject is received by
   * subscribers only once.
   * 
   * publish same event with different subjects multiple times. Event with different subjects
   * received by subscribers as many times as there are different subjects.
   * 
   * publish different event with same subject multiple times. All events with the same subject are
   * received by subscribers.
   * 
   * Register multiple publishers and have them all publish different events. Ensure all events are
   * published.
   * 
   * Register multiple publishers and have them all publish the same event. Ensure the event is only
   * published once.
   * 
   * Register multiple subscribers and have the publisher publish an event. Ensure all subscribers
   * receive the event.
   * 
   * Register multiple subscribers and multiple publishers and have the publishers send multiple
   * events. Ensure all events are sent.
   * 
   * Register two subscribers. Publish a number of events. First subscriber requests a resend of all
   * published events. First subscriber receives all published events in the order they were
   * originally sent. Second subscriber does not.
   * 
   * Register two publishers. First publisher publishes multiple events. Second publisher publishes
   * multiple events. The first and second publishers share some events in common. First publisher
   * requests to unpublish all its published events. All events that are exclusive to the first
   * publisher are unpublished. All events that are exclusive to the second publisher are NOT
   * unpublished. All events that are shared between the first and second publisher are NOT
   * unpublished.
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
