package com.nickmlanglois.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * The constant parametric subdivision type-B surface approximation technique.
 * 
 * stech cparmb uvres
 * 
 * Specifies a surface with constant parametric subdivision, with refinement using one resolution
 * parameter for both the u and v directions.
 * 
 * An initial triangulation is performed using only the points on the trimming curves. This
 * triangulation is then refined until all edges are of an appropriate length. The resulting
 * triangles are not oriented along isoparametric lines as they are in the cparma technique.
 * 
 * uvres is the resolution parameter for both the u and v directions. The larger the value, the
 * finer the resolution.
 * 
 * @author nickl
 *
 */
public interface SurfaceApproxCparmbTechnique
    extends Comparable<SurfaceApproxCparmbTechnique>, SurfaceApprox {
  static final BigDecimal MINIMUM_RESOLUTION = BigDecimal.ZERO;

  BigDecimal getResolutionForUAndVAxes();
}
