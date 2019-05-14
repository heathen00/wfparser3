package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * The curvature-dependent subdivision surface approximation technique.
 * 
 * stech curv maxdist maxangle
 * 
 * Specifies a surface with curvature-dependent subdivision using separate resolution parameters for
 * the maximum distance and the maximum angle.
 * 
 * The surface is subdivided in rectangular regions until 1) the distance in real space between the
 * approximating rectangle and the actual surface is less than the maxdist (approximately) and 2)
 * the angle in degrees between surface normals at the corners of the rectangle is less than the
 * maxangle. Following subdivision, the regions are triangulated.
 * 
 * maxdist is the distance in real space between the approximating rectangle and the actual surface.
 * 
 * maxangle is the angle in degrees between surface normals at the corners of the rectangle.
 * 
 * The smaller the values for maxdist and maxangle, the finer the resolution.
 * 
 * @author nickl
 *
 */
public interface SurfaceApproxCurvTechnique extends SurfaceApprox {
  static final BigDecimal MINIMUM_MAX_DISTANCE = BigDecimal.ZERO;
  static final BigDecimal MINIMUM_MAX_ANGLE = BigDecimal.ZERO;

  BigDecimal getMaxDistance();

  BigDecimal getMaxAngleInDegrees();
}
