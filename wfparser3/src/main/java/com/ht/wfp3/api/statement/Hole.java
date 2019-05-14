package com.ht.wfp3.api.statement;

/**
 * The hole free-form geometry body statement.
 * 
 * hole u0 u1 curv2d u0 u1 curv2d . . .
 * 
 * Body statement for free-form geometry.
 * 
 * Specifies a sequence of curves to build a single inner trimming loop (hole).
 * 
 * u0 is the starting parameter value for the trimming curve curv2d.
 * 
 * u1 is the ending parameter value for the trimming curve curv2d.
 * 
 * curv2d is the index of the trimming curve lying in the parameter space of the surface. This curve
 * must have been previously defined with the curv2 statement.
 * 
 * @author nickl
 *
 */
public interface Hole extends Statement, UsesCurv2DReferenceList {
  public static final Integer MINIMUM_CURVE2D_REFERENCES = Integer.valueOf(1);
}
