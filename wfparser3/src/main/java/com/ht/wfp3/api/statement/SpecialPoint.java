package com.ht.wfp3.api.statement;

import java.util.List;

/**
 * The special point free-form geometry body statement.
 * 
 * sp vp1 vp. . .
 * 
 * Body statement for free-form geometry.
 * 
 * Specifies special geometric points to be associated with a curve or surface. For space curves and
 * trimming curves, the parameter vertices must be 1D. For surfaces, the parameter vertices must be
 * 2D.
 * 
 * vp is the reference number for the parameter vertex of a special point to be associated with the
 * parameter space point of the curve or surface.
 * 
 * @author nickl
 *
 */
public interface SpecialPoint extends Statement {
  static final Integer MINIMUM_VERTEX_REFERENCES = Integer.valueOf(1);

  List<ParamVertexReference> getVertexReferenceList();
}
