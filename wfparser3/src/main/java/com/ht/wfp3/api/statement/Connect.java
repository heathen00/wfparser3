package com.ht.wfp3.api.statement;

/**
 * Free-form geometry statement to connect two surfaces including their trimming curves.
 * 
 * con surf_1 q0_1 q1_1 curv2d_1 surf_2 q0_2 q1_2 curv2d_2
 * 
 * Free-form geometry statement.
 * 
 * Specifies connectivity between two surfaces.
 * 
 * surf_1 is the index of the first surface.
 * 
 * q0_1 is the starting parameter for the curve referenced by curv2d_1.
 * 
 * q1_1 is the ending parameter for the curve referenced by curv2d_1.
 * 
 * curv2d_1 is the index of a curve on the first surface. This curve must have been previously
 * defined with the curv2 statement.
 * 
 * surf_2 is the index of the second surface.
 * 
 * q0_2 is the starting parameter for the curve referenced by curv2d_2.
 * 
 * q1_2 is the ending parameter for the curve referenced by curv2d_2.
 * 
 * curv2d_2 is the index of a curve on the second surface. This curve must have been previously
 * defined with the curv2 statement.
 * 
 * @author nickl
 *
 */
public interface Connect extends Statement {
  Integer getFirstSurfaceIndex();

  Curve2DReference getCurve2DReferenceForFirstSurface();

  Integer getSecondSurfaceIndex();

  Curve2DReference getCurve2DReferenceForSecondSurface();
}
