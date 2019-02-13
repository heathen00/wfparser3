package com.ht.wfp3.api.statement;

import java.util.List;

/**
 * The surface element free-form geometry.
 * 
 * surf s0 s1 t0 t1 v1/vt1/vn1 v2/vt2/vn2 . . .
 * 
 * Element statement for free-form geometry.
 * 
 * Specifies a surface, its parameter range, and its control vertices. The surface is evaluated
 * within the global parameter range from s0 to s1 in the u direction and t0 to t1 in the v
 * direction.
 * 
 * s0 is the starting parameter value for the surface in the u direction.
 * 
 * s1 is the ending parameter value for the surface in the u direction.
 * 
 * t0 is the starting parameter value for the surface in the v direction.
 * 
 * t1 is the ending parameter value for the surface in the v direction.
 * 
 * v is the reference number for a control vertex in the surface.
 * 
 * vt is an optional argument.
 * 
 * vt is the reference number for a texture vertex in the surface. It must always follow the first
 * slash.
 * 
 * vn is an optional argument.
 * 
 * vn is the reference number for a vertex normal in the surface. It must always follow the second
 * slash.
 * 
 * For a non-rational surface, the control vertices are 3D. For a rational surface the control
 * vertices can be 3D or 4D. The fourth coordinate (weight) defaults to 1.0 if ommitted.
 * 
 * NOTE: For more information on the ordering of control points for survaces, refer to the section
 * on surfaces and control points in "mathematics of free-form curves/surfaces" at the end of this
 * appendix.
 * 
 * @author nickl
 *
 */
public interface Surface extends Statement, Commentable {
  String getStartingParameterValueUAxis();

  String getEndingParameterValueUAxis();

  String getStartingParameterValueVAxis();

  String getEndingParameterValueVAxis();

  void appendControlPointVertexReferenceGroup(VertexReferenceGroup createReferenceNumbers);

  List<VertexReferenceGroup> getControlPointVertexReferenceGroup();
}
