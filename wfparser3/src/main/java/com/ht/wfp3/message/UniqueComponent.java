package com.ht.wfp3.message;

/**
 * Base interface that must be extended by any message component that needs to be uniquely
 * identified in a given context.
 * 
 * @author nickl
 *
 * @param <T>
 */
interface UniqueComponent<T> {

  /**
   * The identifier instance that uniquely identifies this component within a context.
   * 
   * @return An identifier instance that uniquely identifies this component within a context.
   */
  UID<T> getUniqueId();
}
