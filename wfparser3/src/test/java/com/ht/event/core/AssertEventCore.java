package com.ht.event.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class AssertEventCore {

  public static AssertEventCore createAssertEventCore() {
    return new AssertEventCore();
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

  public void assertExpectedChannel(String expectedChannelName, boolean expectedIsEnabled,
      List<Event> expectedEventsList, List<Publisher> expectedPublishersList,
      List<Subscriber> expectedSubscribersList, Channel channel) {
    assertNotNull(channel);
    assertEquals(expectedChannelName, channel.getName());
    assertEquals(expectedEventsList, channel.getEventList());
  }

  public void assertExpectedPublisher(Channel expectedChannel, Publisher publisher) {
    assertNotNull(publisher);
    assertEquals(expectedChannel, publisher.getChannel());    
  }
}
