package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.util.List;

/**
 * The parameter free form geometry body statement.
 * 
 * parm u p1 p2 p3. . .
 * 
 * parm v p1 p2 p3 . . .
 * 
 * Body statement for free-form geometry.
 * 
 * Specifies global parameter values. For B-spline curves and surfaces, this specifies the knot
 * vectors.
 * 
 * u is the u direction for the parameter values.
 * 
 * v is the v direction for the parameter values.
 * 
 * To set u and v values, use separate command lines.
 * 
 * p is the global parameter or knot value. You can specify multiple values. A minimum of two
 * parameter values are required. Parameter values must increase monotonically. The type of surface
 * and the degree dictate the number of values required.
 * 
 * @author nickl
 *
 */
public interface Parm extends Statement {
  static final Integer MINIMUM_PARAMETERS = Integer.valueOf(2);

  Axis getAxis();

  List<BigDecimal> getParameterValues();
}
