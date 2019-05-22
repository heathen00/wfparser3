package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * The curvature-dependent subdivision curve approximation technique.
 * 
 * ctech curv maxdist maxangle
 * 
 * Specifies curvature-dependent subdivision using separate resolution parameters for the maximum
 * distance and the maximum angle.
 * 
 * The curve is approximated by a series of line segments in which 1) the distance in object space
 * between a line segment and the actual curve must be less than the maxdist parameter and 2) the
 * angle in degrees between tangent vectors at the ends of a line segment must be less than the
 * maxangle parameter.
 * 
 * maxdist is the distance in real space between a line segment and the actual curve.
 * 
 * maxangle is the angle (in degrees) between tangent vectors at the ends of a line segment.
 * 
 * The smaller the values for maxdist and maxangle, the finer the resolution.
 * 
 * @author nickl
 *
 */
public interface CurveApproxCurvTechnique
    extends Comparable<CurveApproxCurvTechnique>, CurveApprox {
  public static final BigDecimal MINIMUM_MAXIMUM_DISTANCE = BigDecimal.ZERO;
  public static final BigDecimal MINIMUM_MAXIMUM_ANGLE = BigDecimal.ZERO;

  BigDecimal getMaximumDistance();

  BigDecimal getMaximumAngleInDegrees();
}
