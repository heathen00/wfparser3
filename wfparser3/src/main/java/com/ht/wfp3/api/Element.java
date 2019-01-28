package com.ht.wfp3.api;

/**
 * An element is any OBJ data that has its own distinct keyword, such as "v" for geometric vertex or
 * "l" for line.
 * 
 * @author nickl
 *
 */
public interface Element {
  String getKeyword();
}
