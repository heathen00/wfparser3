package com.ht.wfp3.message;

/**
 * The unique identity is used by all modules that extend the UniqueComponent interface to provide a
 * standard mechanism to uniquely identify either a message field or an entire message within a
 * given context. What that context is and what other constraints may exist is dependent on the
 * component that is being uniquely identified. Thus, what constitutes valid values for a unique
 * Priority identifier would be different from the Topic. By definition, the mapping between a
 * specific UID instance and the component it uniquely identifies is one-to-one.
 * 
 * @author nickl
 *
 * @param <T>
 */
public interface UID<T> {

  /**
   * The unique identifier for this component. Although the data type is a string, the ID will
   * generally be an integer value or combination of integer values. This field is not intended to
   * be used as a key on its own since it lacks the proper context information.
   * 
   * @return A string representation of this component's unique identifier.
   */
  String getID();

  /**
   * The name of the context in which this identifier is unique.
   * 
   * @return A string representation of the context in which this component identifier is unique.
   */
  String getContext();

  /**
   * The message field or full message that is uniquely identified by this identifier.
   * 
   * @return The component instance, such as Topic, Description, Priority, or Message that is
   *         uniquely identified by this identifier.
   */
  T getComponent();
}
