package com.ht.wfp3.message.OLD;

import com.ht.uid.Uid;
import com.ht.uid.UniqueComponent;

/**
 * The event message. The event message contains the Topic, Priority, and Description describing a
 * message that is unique to the system. The uniqueness of the message is a combination of the topic
 * unique ID and the description unique ID. The (first, first) reserved message is the "UNDEFINED"
 * message for error scenarios that require it. All other messages are uniquely defined as a
 * combination of their topic and description.
 * 
 * @author nickl
 *
 */
public interface Message extends UniqueComponent<Message> {
  Priority getPriority();

  void setPriority(Uid<Priority> priorityUid);

  Topic getTopic();

  Description getDescription();
}
