package com.ht.wfp3.api.statement;

/**
 * The free form geometry polynomial degree.
 * 
 * deg degu degv
 * 
 * Free-form geometry statement.
 * 
 * Sets the polynomial degree for curves and surfaces.
 * 
 * degu is the degree in the u direction. It is required for both curves and surfaces.
 * 
 * degv is the degree in the v direction. It is required only for surfaces. For Bezier, B-spline,
 * Taylor, and basis matrix, there is no default; a value must be supplied. For Cardinal, the degree
 * is always 3. If some other value is given for Cardinal, it will be ignored.
 * 
 * @author nickl
 *
 */
public interface Degree extends Statement {
  public static final Integer MINIMUM_DEGREE = 1;
  
  boolean isVAxisDegreeSet();

  Integer getUAxisDegree();

  Integer getVAxisDegree();
}
