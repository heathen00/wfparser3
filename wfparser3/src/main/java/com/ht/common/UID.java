package com.ht.common;

/**
 * The unique identity is used by all modules that extend the UniqueComponent interface to provide a
 * standard mechanism to uniquely identify either a message field or an entire message within a
 * given context. The context is synonymous with the type T that this UID is being used to identify.
 * Thus, what constitutes valid values for a unique Priority identifier would be different from the
 * Topic. By definition, the mapping between a specific UID instance and the component it uniquely
 * identifies is one-to-one.
 * 
 * @author nickl
 *
 * @param <T>
 */
public interface UID<T> extends Comparable<UID<T>> {
  public static <T> UID<T> createUid(String key, T component) {
    return new UIDImp<T>(key, component);
  }

  /**
   * The unique identifier for this component. Must be unique within its context. It does not
   * support i18n.
   * 
   * @return A string representation of this component's unique identifier.
   */
  String getKey();

  /**
   * The message field or full message that is uniquely identified by this identifier.
   * 
   * @return The component instance, such as Topic, Description, Priority, or Message that is
   *         uniquely identified by this identifier.
   */
  T getComponent();

  boolean equals(Object obj);

  int hashCode();
}
