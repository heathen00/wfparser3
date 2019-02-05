package com.ht.wfp3.api;

import java.util.List;

/**
 * Trimming loop free-form geometry body statement.
 * 
 * trim u0 u1 curv2d u0 u1 curv2d . . .
 * 
 * Body statement for free-form geometry.
 * 
 * Specifies a sequence of curves to build a single outer trimming loop.
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
public interface Trim extends Node, Commentable {
  List<Curve2DReference> getTrimmingCurve2DReferences();

  void appendTrimmingCurve2DReference(Curve2DReference trimmingCurve);
}
