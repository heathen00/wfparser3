package com.nickmlanglois.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * The constant parametric subdivision curve approximation technique.
 * 
 * ctech cparm res
 * 
 * Specifies a curve with constant parametric subdivision using one resolution parameter. Each
 * polynomial segment of the curve is subdivided n times in parameter space, where n is the
 * resolution parameter multiplied by the degree of the curve.
 * 
 * res is the resolution parameter. The larger the value, the finer the resolution. If res has a
 * value of 0, each polynomial curve segment is represented by a single line segment.
 * 
 * 
 * @author nickl
 *
 */
public interface CurveApproxCparmTechnique
    extends Comparable<CurveApproxCparmTechnique>, CurveApprox {
  public static final BigDecimal MINIMUM_RESOLUTION = BigDecimal.ZERO;

  BigDecimal getResolution();
}
