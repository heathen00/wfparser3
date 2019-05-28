package com.ht.wfp3.message;

/**
 * 
 * The message topic. The topic is used to group related messages according to what task the system
 * is currently performing, such as "parse" or "validate", and is loosely associated with the
 * subsystems that perform those tasks. The topic has a human readable name that supports
 * internationalization (i18n). It has a positive non-zero integer that is unique across all topics,
 * thus the whole messaging subsystem. The zeroth topic is reserved for the standard "UNDEFINED"
 * topic for error scenarios that require it. The first topic is reserved for general "SYSTEM"
 * errors for implementation problems that occurred in the reporting system that are not related to
 * client problems and during the message system bootstrapping process. The topic name and unique
 * identifier are explicitly specified before the messaging system initialization.
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
