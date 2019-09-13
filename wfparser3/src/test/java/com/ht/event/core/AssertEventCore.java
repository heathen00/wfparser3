package com.ht.event.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;

public class AssertEventCore {
  public static AssertEventCore createAssertEventCore() {
    return new AssertEventCore();
  }

  public void assertExpectedChannel_NEW(String expectedChannelName, boolean expectedIsOpen,
      List<EventDescription> expectedEventDescriptionList, List<Publisher> expectedPublisherList,
      List<Subscriber> expectedSubscriberList, Channel channel) {
    assertNotNull(channel);
    assertEquals(expectedChannelName, channel.getName());
    assertEquals(expectedIsOpen, channel.isOpen());
    assertEquals(expectedEventDescriptionList, channel.getEventDescriptionList());
    assertEquals(expectedPublisherList, channel.getPublisherList());
    assertEquals(expectedSubscriberList, channel.getSubscriberList());
  }

  public void assertExpectedEventDescription(EventDescription expectedEventDescription,
      EventDescription eventDescription) {
    assertNotNull(eventDescription);
    assertEquals(expectedEventDescription.getChannel(), eventDescription.getChannel());
    assertEquals(expectedEventDescription.getFamily(), eventDescription.getFamily());
    assertEquals(expectedEventDescription.getName(), eventDescription.getName());
    assertEquals(String.join(".", eventDescription.getChannel().getName(),
        eventDescription.getFamily(), eventDescription.getName()),
        eventDescription.getFullyQualifiedName());
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

  public void assertExpectedEvent(EventDescription expectedDescriptionEvent,
      Subject expectedSubject, Event event) {
    assertNotNull(event);
    assertEquals(expectedSubject, event.getSubject());
    assertEquals(expectedDescriptionEvent, event.getEventDescription());
  }

  public void assertExpectedEvent(EventDescription expectedDescriptionEvent, Event event) {
    assertNotNull(event);
    assertExpectedEventDescription(expectedDescriptionEvent, event.getEventDescription());
    assertExpectedSubject(event.getSubject());
  }

  public void assertExpectedSubject(Subject subject) {
    assertNotNull(subject);
  }

  public void assertExpectedPublisher(Channel expectedChannel, Publisher publisher) {
    assertNotNull(publisher);
    assertEquals(expectedChannel, publisher.getChannel());
  }
}
