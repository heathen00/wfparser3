package com.ht.wfp3.api.statement;

/**
 * A Two Dimensional free form curve or surface.
 * 
 * curv2 vp1 vp2 vp3. . .
 * 
 * Free-form geometry statement.
 * 
 * Specifies a 2D curve on a surface and its control points. A 2D curve is used as an outer or inner
 * trimming curve, as a special curve, or for connectivity.
 * 
 * vp is the parameter vertex reference number for the control point. You can specify multiple
 * control points. A minimum of two control points is required for a 2D curve.
 * 
 * The control points are parameter vertices because the curve must lie in the parameter space of
 * some surface. For a non-rational curve, the control vertices can be 2D. For a rational curve, the
 * control vertices can be 2D or 3D. The third coordinate (weight) defaults to 1.0 if omitted.
 * 
 * @author nickl
 *
 */
public interface Curve2D extends Statement, UsesVertexReferenceGroups {

}
