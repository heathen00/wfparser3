package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * Represents a reference to a defined Curv2D statement and includes the starting and ending parameters.
 * This is a substatement and is used by a number of statements: Connect, Hole, SpecialCurve, and Trim.
 * @author nickl
 *
 */
public interface Curve2DReference {
  BigDecimal getStartingParameterValue();

  BigDecimal getEndingParameterValue();

  Integer getCurve2DIndex();
}
