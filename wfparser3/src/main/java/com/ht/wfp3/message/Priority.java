package com.ht.wfp3.message;

import com.ht.uid.UniqueComponent;

/**
 * The message priority. The priority has a human readable string name that supports i18n and has a
 * system wide defined maximum string length. The priority has a unique key. The first reserved
 * priority is the "UNDEFINED" priority for error scenarios that require it. The second reserved
 * priority is the "DISABLED" priority used to turn off a given message. The priorities are the same
 * across all topics and messages and are defined before message subsystem startup. The priorities
 * are intended to be changeable for a given message, so should NOT be used as a part of uniquely
 * identifying a message either within a task or across the entire message subsystem.
 * 
 * @author nickl
 *
 */
public interface Priority extends UniqueComponent<Priority> {
  String getName();
}
