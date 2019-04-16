package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * The constant spatial subdivision curve approxmation technique.
 * 
 * ctech cspace maxlength
 * 
 * Specifies a curve with constant spatial subdivision. The curve is approximated by a series of
 * line segments whose lengths in real space are less than or equal to the maxlength.
 * 
 * maxlength is the maximum length of the line segments. The smaller the value, the finer the
 * resolution.
 * 
 * @author nickl
 *
 */
public interface CurveApproxCspaceTechnique extends CurveApprox {
  BigDecimal getMaxLength();
}