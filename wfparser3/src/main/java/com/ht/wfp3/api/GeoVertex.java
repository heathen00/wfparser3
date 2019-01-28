package com.ht.wfp3.api;

/**
 * The Geometric Vertex.
 * 
 * v x y z w
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Specifies a geometric vertex and its x y z coordinates. Rational curves and surfaces require a
 * fourth homogeneous coordinate, also called the weight.
 * 
 * x y z are the x, y, and z coordinates for the vertex. These are floating point numbers that
 * define the position of the vertex in three dimensions.
 * 
 * w is the weight required for rational curves and surfaces. It is not required for non-rational
 * curves and surfaces. If you do not specify a value for w, the default is 1.0.
 * 
 * NOTE: A positive weight value is recommended. Using zero or negative values may result in an
 * undefined point in a curve or surface.
 * 
 * @author nickl
 *
 */
public interface GeoVertex extends Element {
  String getXCoord();

  String getYCoord();

  String getZCoord();

  String getWCoord();
}

