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

  public void assertExpectedChannel(String expectedChannelName, boolean expectedIsEnabled,
      List<Event> expectedEventsList, List<Publisher> expectedPublishersList,
      List<Subscriber> expectedSubscribersList, Channel channel) {
    assertNotNull(channel);
    assertEquals(expectedChannelName, channel.getName());
    assertEquals(expectedEventsList, channel.getEventList());
  }

  public void assertExpectedEvent(Channel expectedEventChannel, String expectedEventFamily,
      String expectedEventName, Event event) {
    assertNotNull(event);
    assertEquals(expectedEventChannel, event.getChannel());
    assertEquals(expectedEventFamily, event.getFamily());
    assertEquals(expectedEventName, event.getName());
    assertEquals(String.join(".", event.getChannel().getName(), event.getFamily(), event.getName()),
        event.getFullyQualifiedName());
    assertTrue(expectedEventChannel.getEventList().contains(event));
  }

  public void assertExpectedEvent(Event expectedEvent, Subject expectedSubject, Event event) {
    assertNotNull(event);
    assertEquals(expectedSubject, event.getSubject());
    assertEquals(expectedSubject.isDefined(), event.getSubject().isDefined());
    assertEquals(expectedEvent.getFullyQualifiedName(), event.getFullyQualifiedName());
    assertNotEquals(expectedEvent, event);
    assertExpectedSubject(expectedSubject.isDefined(), event.getSubject());
  }

  public void assertExpectedEvent(Event expectedEvent, Event event) {
    assertNotNull(event);
    assertEquals(expectedEvent, event);
    assertExpectedEvent(expectedEvent.getChannel(), expectedEvent.getFamily(),
        expectedEvent.getName(), event);
    assertExpectedSubject(expectedEvent.getSubject().isDefined(), event.getSubject());
  }

  public void assertExpectedSubject(boolean expectedIsDefined, Subject subject) {
    assertNotNull(subject);
    assertEquals(expectedIsDefined, subject.isDefined());
  }

  public void assertExpectedPublisher(Channel expectedChannel, Publisher publisher) {
    assertNotNull(publisher);
    assertEquals(expectedChannel, publisher.getChannel());
  }
}
