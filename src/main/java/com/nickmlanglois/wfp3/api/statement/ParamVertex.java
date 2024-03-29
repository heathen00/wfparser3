package com.nickmlanglois.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * The Parameter Vertex data.
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
 * The vertex data is represented by four vertex lists; one for each type of vertex coordinate. A
 * right-hand coordinate system is used to specify the coordinate locations.
 * 
 * When vertices are loaded into the Advanced Visualizer, they are sequentially numbered, starting
 * with 1. These reference numbers are used in element statements.
 * 
 * @author nickl
 *
 */
public interface ParamVertex extends Comparable<ParamVertex>, Statement {
  BigDecimal DEFAULT_W_COORD = BigDecimal.valueOf(1.0d);

  BigDecimal getUCoord();

  BigDecimal getVCoord();

  BigDecimal getWCoord();

  boolean isVCoordSet();

  boolean isWCoordSet();
}
