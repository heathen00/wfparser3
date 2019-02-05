package com.ht.wfp3.api;

/**
 * The shadow object polygonal and free-form geometry statement.
 * 
 * shadow_obj filename
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Specifies the shadow object filename. This object is used to cast shadows for the current object.
 * Shadows are only visible in a rendered image; they cannot be seen using hardware shading. The
 * shadow object is invisible except for its shadow.
 * 
 * An object will cast shadows only if it has a shadow object. You can use an object as its own
 * shadow object. However, a simplified version of the original object is usually preferable for
 * shadow objects, since shadow casting can greatly increase rendering time.
 * 
 * filename is the filename for the shadow object. You can enter any valid object filename for the
 * shadow object. The object file can be an .obj or .mod file. If a filename is given without an
 * extension, an extension of .obj is assumed.
 * 
 * Only one shadow object can be stored in a file. If more than one shadow object is specified, the
 * last one specified will be used.
 * 
 * @author nickl
 *
 */
public interface ShadowObject extends Node, Commentable {
  String getShadowObjectFileName();
}
