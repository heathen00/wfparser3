package com.ht.wfp3.api;

/**
 * A node is any OBJ data that has its own distinct keyword, such as "v" for geometric vertex or "l"
 * for line. Node must remain abstract.
 * 
 * @author nickl
 *
 */
public interface Node {
  String getKeyword();
}
