package com.ht.wfp3.message.OLD;

import com.ht.uid.UniqueComponent;

/**
 * 
 * The message topic. The topic is used to group related messages according to what task the system
 * is currently performing, such as "parse" or "validate", and is loosely associated with the
 * subsystems that perform those tasks. The topic has a human readable name that supports
 * internationalization (i18n). The first reserved topic is "UNDEFINED" and is used for error
 * scenarios that require it. The second reserved topic is "SYSTEM" and is used for implementation
 * errors that occurred in the reporting system that are not related to client problems and during
 * the message system bootstrapping process. The topic name and unique identifier are explicitly
 * specified before the messaging system initialization.
 * 
 * @author nickl
 *
 */
public interface Topic extends UniqueComponent<Topic> {

  /**
   * The name of the message topic.
   * 
   * @return A string representation of the message topic name.
   */
  String getName();
}
