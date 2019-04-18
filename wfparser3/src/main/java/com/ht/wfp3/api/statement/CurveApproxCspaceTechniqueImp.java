package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class CurveApproxCspaceTechniqueImp extends CurveApproxImp implements CurveApproxCspaceTechnique {
  private static final CurveApprox.Technique TECHNIQUE = CurveApprox.Technique.CSPACE;

  private final BigDecimal maxLength;

  CurveApproxCspaceTechniqueImp(BigDecimal maxLength) {
    super(TECHNIQUE);
    this.maxLength = maxLength;
  }

  CurveApproxCspaceTechniqueImp(CurveApproxCspaceTechnique curveApproxCspaceTechnique) {
    this(curveApproxCspaceTechnique.getMaxLength());
  }

  @Override
  public BigDecimal getMaxLength() {
    return maxLength;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((maxLength == null) ? 0 : maxLength.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    CurveApproxCspaceTechniqueImp other = (CurveApproxCspaceTechniqueImp) obj;
    if (maxLength == null) {
      if (other.maxLength != null)
        return false;
    } else if (!maxLength.equals(other.maxLength))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CurveApproxCspaceTechniqueImp [maxLength=" + maxLength + ", super.toString()="
        + super.toString() + "]";
  }
}
