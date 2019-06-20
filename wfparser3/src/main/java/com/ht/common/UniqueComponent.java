package com.ht.common;

/**
 * Base interface that must be extended by any message component that needs to be uniquely
 * identified in a given context.
 * 
 * @author nickl
 *
 * @param <T>
 */
public interface UniqueComponent<T> {

  /**
   * The identifier instance that uniquely identifies this component within a context.
   * 
   * @return An identifier instance that uniquely identifies this component within a context.
   */
  UID<T> getUid();
}
