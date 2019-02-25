package com.ht.wfp3.api.statement;

/**
 * The ray tracing polygonal and free-form geometry statement.
 * 
 * trace_obj filename
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Specifies the ray tracing object filename. This object will be used in generating reflections of
 * the current object on reflective surfaces. Reflections are only visible in a rendered image; they
 * cannot be seen using hardware shading.
 * 
 * An object will appear in reflections only if it has a trace object. You can use an object as its
 * own trace object. However, a simplified version of the original object is usually preferable for
 * trace objects, since ray tracing can greatly increase rendering time.
 * 
 * filename is the filename for the ray tracing object. You can enter any valid object filename for
 * the trace object. You can enter any valid object filename for the shadow object. The object file
 * can be an .obj or .mod file. If a filename is given without an extension, an extension of .obj is
 * assumed.
 * 
 * Only one trace object can be stored in a file. If more than one is specified, the last one is
 * used.
 * 
 * @author nickl
 *
 */
public interface RayTracingObject extends Statement {
  String getRayTracingObjectFileName();
}
