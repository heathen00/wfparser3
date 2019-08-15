package com.ht.uid;

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
public interface Uid<T> extends Comparable<Uid<T>> {

  /**
   * The unique identifier for this component. Must be unique within its context. It does not
   * support i18n.
   * 
   * @return A string representation of this component's unique identifier.
   */
  String getKey();

  boolean equals(Object obj);

  int hashCode();
}
