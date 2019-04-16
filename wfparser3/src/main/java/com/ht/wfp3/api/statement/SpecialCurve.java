package com.ht.wfp3.api.statement;

/**
 * Special Curve free-form geometry body statement.
 * 
 * scrv u0 u1 curv2d u0 u1 curv2d . . .
 * 
 * Body statement for free-form geometry.
 * 
 * Specifies a sequence of curves which lie on the given surface to build a single special curve.
 * 
 * u0 is the starting parameter value for the special curve curv2d.
 * 
 * u1 is the ending parameter value for the special curve curv2d.
 * 
 * curv2d is the index of the special curve lying in the parameter space of the surface. This curve
 * must have been previously defined with the curv2 statement.
 * 
 * @author nickl
 *
 */
public interface SpecialCurve extends Statement, UsesCurv2DReferenceList {
}
