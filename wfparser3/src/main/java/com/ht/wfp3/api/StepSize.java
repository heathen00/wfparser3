package com.ht.wfp3.api;

/**
 * Sets the step size for curves and surfaces that use a basis matrix.
 * 
 * step stepu stepv
 * 
 * Free-form geometry statement.
 * 
 * Sets the step size for curves and surfaces that use a basis matrix.
 * 
 * stepu is the step size in the u direction. It is required for both curves and surfaces that use a
 * basis matrix.
 * 
 * stepv is the step size in the v direction. It is required only for surfaces that use a basis
 * matrix. There is no default. A value must be supplied.
 * 
 * When a curve or surface is being evaluated and a transition from one segment or patch to the next
 * occurs, the set of control points used is incremented by the step size. The appropriate step size
 * depends on the representation type, which is expressed through the basis matrix, and on the
 * degree.
 * 
 * That is, suppose we are given a curve with k control points: {v , ... v } 1 k
 * 
 * If the curve is of degree n, then n + 1 control points are needed for each polynomial segment. If
 * the step size is given as s, then the ith polynomial segment, where i = 0 is the first segment,
 * will use the control points: {v ,...,v } is+1 is+n+1
 * 
 * For example, for Bezier curves, s = n .
 * 
 * For surfaces, the above description applies independently to each parametric direction.
 * 
 * When you create a file which uses the basis matrix type, be sure to specify a step size
 * appropriate for the current curve or surface representation.
 * 
 * @author nickl
 *
 */
public interface StepSize extends Node {
  int getStepSizeInUAxis();

  int getStepSizeInVAxis();
}
