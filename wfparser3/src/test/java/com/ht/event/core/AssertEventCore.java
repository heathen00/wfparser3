package com.ht.event.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;

public class AssertEventCore {
  public static AssertEventCore createAssertEventCore() {
    return new AssertEventCore();
  }

  public void assertExpectedChannel(String expectedChannelName, boolean expectedIsOpen,
      List<Event> expectedEventsList, List<Publisher> expectedPublishersList,
      List<Subscriber> expectedSubscribersList, Channel channel) {
    assertNotNull(channel);
    assertEquals(expectedChannelName, channel.getName());
    assertEquals(expectedIsOpen, channel.isOpen());
    assertEquals(expectedEventsList, channel.getEventList());
    assertEquals(expectedPublishersList, channel.getPublisherList());
    assertEquals(expectedSubscribersList, channel.getSubscriberList());
  }

  public void assertExpectedEvent(Channel expectedchannel, String expectedfamily,
      String expectedEventName, Event event) {
    assertNotNull(event);
    assertEquals(expectedchannel, event.getChannel());
    assertEquals(expectedfamily, event.getFamily());
    assertEquals(expectedEventName, event.getName());
    assertEquals(String.join(".", event.getChannel().getName(), event.getFamily(), event.getName()),
        event.getFullyQualifiedName());
    assertTrue(expectedchannel.getEventList().contains(event));
  }

  public void assertExpectedEvent(Event expectedEvent, Subject expectedSubject, Event event) {
    assertNotNull(event);
    assertEquals(expectedSubject, event.getSubject());
    assertEquals(expectedEvent.getFullyQualifiedName(), event.getFullyQualifiedName());
    assertNotEquals(expectedEvent, event);
  }

  public void assertExpectedEvent(Event expectedEvent, Event event) {
    assertNotNull(event);
    assertEquals(expectedEvent, event);
    assertExpectedEvent(expectedEvent.getChannel(), expectedEvent.getFamily(),
        expectedEvent.getName(), event);
  }

  public void assertExpectedSubject(boolean expectedIsDefined, Subject subject) {
    assertNotNull(subject);
  }

  public void assertExpectedPublisher(Channel expectedChannel, Publisher publisher) {
    assertNotNull(publisher);
    assertEquals(expectedChannel, publisher.getChannel());
  }

  public void assertExpectedEventDescription(Channel expectedChannel, String expectedfamily,
      String expectedEventName, EventDescription eventDescription) {
    assertNotNull(eventDescription);
    assertEquals(expectedChannel, eventDescription.getChannel());
    assertEquals(expectedfamily, eventDescription.getFamily());
    assertEquals(expectedEventName, eventDescription.getName());
    assertEquals(String.join(".", eventDescription.getChannel().getName(),
        eventDescription.getFamily(), eventDescription.getName()),
        eventDescription.getFullyQualifiedName());
  }

  public void assertExpectedChannel_NEW(String expectedChannelName, boolean expectedIsOpen,
      List<EventDescription> expectedEventDescriptionList, List<Event> expectedEventList,
      List<Publisher> expectedPublisherList, List<Subscriber> expectedSubscriberList,
      Channel channel) {
    assertNotNull(channel);
    assertEquals(expectedChannelName, channel.getName());
    assertEquals(expectedIsOpen, channel.isOpen());
    assertEquals(expectedEventDescriptionList, channel.getEventDescriptionList());
    assertEquals(expectedEventList, channel.getEventList());
    assertEquals(expectedPublisherList, channel.getPublisherList());
    assertEquals(expectedSubscriberList, channel.getSubscriberList());
  }
}
