package com.ht.wfp3.api.statement;

/**
 * The curve or service (sic., surface) type for free form geometry.
 * 
 * cstype rat type
 * 
 * Free-form geometry statement.
 * 
 * Specifies the type of curve or surface and indicates a rational or non-rational form.
 * 
 * rat is an optional argument.
 * 
 * rat specifies a rational form for the curve or surface type. If rat is not included, the curve or
 * surface is non-rational
 * 
 * type specifies the curve or surface type. Allowed types are:
 * 
 * bmatrix basis matrix
 * 
 * bezier Bezier
 * 
 * bspline B-spline
 * 
 * cardinal Cardinal
 * 
 * taylor Taylor
 * 
 * There is no default. A value must be supplied.
 * 
 * @author nickl
 *
 */
public interface CurveOrSurfaceType extends Statement {
  enum Key {
    BMATRIX("bmatrix"), BEZIER("bezier"), BSPLINE("bspline"), CARDINAL("cardinal"), TAYLOR(
        "taylor");

    private final String keyword;

    Key(String keyword) {
      this.keyword = keyword;
    }

    public String getKeyword() {
      return keyword;
    }
  }

  boolean isRational();

  CurveOrSurfaceType.Key getTypeKey();
}
