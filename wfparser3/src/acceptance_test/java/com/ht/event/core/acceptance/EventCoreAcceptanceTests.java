package com.ht.event.core.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.event.core.Channel;
import com.ht.event.core.Event;
import com.ht.event.core.EventFactory;
import com.ht.event.core.Publisher;
import com.ht.event.core.Subscriber;
import com.ht.uid.UidFactory;

/*
 * I see the use of this API from three different roles:
 * 
 * Initializer: The person who creates the Event and Channel definitions.
 * 
 * Publisher: The person who sends the predefined events created by the EventInitializer to a
 * specified Channel.
 * 
 * Subscriber: The person who receives messages sent on a channel.
 * 
 * 
 * And here are the Event subsystem model concepts and how they relate to one another:
 * 
 * Channel: A communications Channel of interest between like minded Publishers and Subscribers. It
 * is identified by a name (String) and can have any number of Publishers and Subscribers
 * subscribed. It can also have any number of Events defined for it. Events from one Channel cannot
 * be sent to another Channel. Sending the same Event over the Channel does nothing.
 * 
 * Event: A "happening" of interest to like minded Publishers and Subscribers. It is only defined
 * within a given Channel. The Event is defined based on a Family and Name. The Family accumulates
 * related Events together (I don't want to use the name Group because that implies incorrectly that
 * subscribers and not the events are being grouped). The Name is the unique name for that event
 * within the given Channel and Family. Thus, two different Events may have the same Family and Name
 * if they belong to different Channels. And two different Events may have the same Name if they are
 * in the same Channel BUT different Families. Optionally, an Event may also have a Subject that
 * identifies that a given Event occurred with respect to that Subject. Thus if the same Event is
 * sent on the Channel twice, but the Event specifies two distinct Subjects then the Events are
 * treated as separate Events within that channel. Events must be initialized before they can be
 * processed in a Channel, however Subjects are specified when the Event is published.
 * 
 * Family: An accumulation of related Event instances. The idea comes from the HTTP protocol where
 * the HTTP response codes are subdivided into 5 Families as identified by the first digit of the
 * HTTP response code, so (2XX for success cases, 5XX for internal server errors, 4XX for client
 * errors, etc).
 * 
 * Name: A unique identifier for an Event within the context of the Event's Channel and Family. In
 * the absence of a Subject registered with the event, the Name is the only attribute that uniquely
 * identifies an Event on a Channel for a given Family. And the Subject differentiates two Events
 * that have the same Channel, Family, and Name assuming the Subjects are themselves unique.
 * 
 * Subscriber: A Subscriber receives Events published on a Channel. A Subscriber can only register
 * to a single Channel. Multiple Subscribers can register to the same Channel. All Subscribers
 * receive all Events published on the Channel. Optionally, a Subscriber can request all Events
 * currently published on its Channel.
 * 
 * Publisher: A Publisher publishes and unpublishes Events to a Channel. A Publisher can only
 * register with a single Channel. Multiple Publishers can register to the same Channel.
 * 
 * 
 * Implementation Notes:
 * 
 * Subscribers are interfaces that must be implemented and registered.
 * 
 * Subjects are interfaces that must be implemented.
 * 
 * There must be some standard means of comparing Subjects. I will also define an interface that is
 * called "NaturalOrder" that accumulates the "equals", "hashCode", and "compareTo" interfaces since
 * I like to keep them all consistent, anyway. That should make testing easier, since I could just
 * send in "NaturalOrder" instances into an "AssertNaturalOrder" instance to ensure the contract is
 * respected for all defined test scenarios.
 * 
 * To be consistent with the Localizer subsystem that you will be integrating with, you should use
 * the ID subsystem.
 * 
 * For the factory, ensure you separate out the creation, the validationm and the caching.
 * 
 * For initialization, use ONLY the actual instances of Channel, Event, when creating the instances.
 * However, for processing requests, use ONLY the UIDs.
 * 
 */
public class EventCoreAcceptanceTests {

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
  }

  @Test
  public void EventCore_createEventWithValidChannelFamilyAndName_eventCreated() {
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
  public void EventCore_createPublisherWithValidChannel_publisherCreated() {
    final Channel expectedChannel = eventFactory.createChannel("test.channel");
    final int expectedPublisherListSize = 1;

    Publisher publisher = eventFactory.createPublisher(expectedChannel);

    assertNotNull(publisher);
    assertEquals(expectedChannel, publisher.getChannel());
    assertTrue(expectedChannel.getPublisherList().contains(publisher));
    assertEquals(expectedPublisherListSize, expectedChannel.getPublisherList().size());
  }

  @Test
  public void EventCore_registerValidSubscriberToChannel_subscriberSuccessfullyRegistered() {
    final Subscriber expectedSubscriber = createSubscriberStub();
    final int expectedSubscriberListSize = 1;
    Channel channel = eventFactory.createChannel("test.channel");

    channel.registerSubscriber(expectedSubscriber);

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
    channel.registerSubscriber(subscriber);

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
    channel.registerSubscriber(subscriber);

    publisher.publish(expectedEvent.getUid());
    assertTrue(expectedProcessedEventList.contains(expectedEvent.getFullyQualifiedName()));

    publisher.unpublish(expectedEvent.getUid());
    assertFalse(expectedProcessedEventList.contains(expectedEvent.getFullyQualifiedName()));
  }
}
