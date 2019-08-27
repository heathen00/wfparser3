package com.ht.event.core;

/**
 * Confusing name. What it means is this is the published interface, i.e. the attributes that are
 * exposed to the API clients, for the Subscriber type. So, "published versus internal" instead of
 * "published versus subscribed". Again, confusing name.
 */
interface SubscriberPublished {
  void processPublishEvent(Event event);

  void processUnpublishEvent(Event event);
}
