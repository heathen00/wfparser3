package com.nickmlanglois.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * The constant parametric subdivision type-A surface approximation technique.
 * 
 * stech cparma ures vres
 * 
 * Specifies a surface with constant parametric subdivision using separate resolution parameters for
 * the u and v directions. Each patch of the surface is subdivided n times in parameter space, where
 * n is the resolution parameter multiplied by the degree of the surface.
 * 
 * ures is the resolution parameter for the u direction.
 * 
 * vres is the resolution parameter for the v direction.
 * 
 * The larger the values for ures and vres, the finer the resolution. If you enter a value of 0 for
 * both ures and vres, each patch is approximated by two triangles.
 * 
 * @author nickl
 *
 */
public interface SurfaceApproxCparmaTechnique
    extends Comparable<SurfaceApproxCparmaTechnique>, SurfaceApprox {
  static final BigDecimal MINIMUM_RESOLUTION_FOR_U_AXIS = BigDecimal.ZERO;
  static final BigDecimal MINIMUM_RESOLUTION_FOR_V_AXIS = BigDecimal.ZERO;

  BigDecimal getResolutionForUAxis();

  BigDecimal getResolutionForVAxis();
}
