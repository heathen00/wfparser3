package com.ht.wfp3.api;


/**
 * The Parameter Vertex
 * 
 * Free-form geometry statement.
 * 
 * Specifies a point in the parameter space of a curve or surface.
 * 
 * The usage determines how many coordinates are required. Special points for curves require a 1D
 * control point (u only) in the parameter space of the curve. Special points for surfaces require a
 * 2D point (u and v) in the parameter space of the surface. Control points for non-rational
 * trimming curves require u and v coordinates. Control points for rational trimming curves require
 * u, v, and w (weight) coordinates.
 * 
 * u is the point in the parameter space of a curve or the first coordinate in the parameter space
 * of a surface.
 * 
 * v is the second coordinate in the parameter space of a surface.
 * 
 * w is the weight required for rational trimming curves. If you do not specify a value for w, it
 * defaults to 1.0.
 * 
 * NOTE: For additional information on parameter vertices, see the curv2 and sp statements
 * 
 * @author nickl
 *
 */
public interface ParamVertex extends Element {
  String getUCoord();

  String getVCoord();

  String getWCoord();
}
