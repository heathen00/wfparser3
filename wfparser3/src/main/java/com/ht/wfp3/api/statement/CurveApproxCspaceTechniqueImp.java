package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class CurveApproxCspaceTechniqueImp extends CurveApproxImp implements CurveApproxCspaceTechnique {
  private static final String TECHNIQUE_KEYWORD = "cspace";

  private final BigDecimal maxLength;

  CurveApproxCspaceTechniqueImp(BigDecimal maxLength) {
    super(TECHNIQUE_KEYWORD);
    if (null == maxLength) {
      throw new NullPointerException("maxLength constructor parameter cannot be null");
    }
    if (MINIMUM_MAX_LENGTH.compareTo(maxLength) >= 0) {
      throw new IllegalArgumentException(
          "maxLength constructor parameter must be greater than " + MINIMUM_MAX_LENGTH);
    }
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
  public int compareTo(CurveApproxCspaceTechnique o) {
    return maxLength.compareTo(o.getMaxLength());
  }

  @Override
  public String toString() {
    return "CurveApproxCspaceTechniqueImp [maxLength=" + maxLength + ", super.toString()="
        + super.toString() + "]";
  }
}
