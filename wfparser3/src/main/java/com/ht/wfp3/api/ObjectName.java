package com.ht.wfp3.api;

/**
 * An optional polygonal and free-form geometry statement to specify a user-defined name for the
 * elements following this statement.
 * 
 * o object_name
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Optional statement; it is not processed by any Wavefront programs. It specifies a user-defined
 * object name for the elements defined after this statement.
 * 
 * object_name is the user-defined object name. There is no default.
 * 
 * @author nickl
 *
 */
public interface ObjectName extends Statement, Commentable {
  String getObjectName();
}
