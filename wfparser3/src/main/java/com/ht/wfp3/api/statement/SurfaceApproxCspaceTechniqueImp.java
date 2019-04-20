package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class SurfaceApproxCspaceTechniqueImp extends SurfaceApproxImp
    implements SurfaceApproxCspaceTechnique {
  private static final String TECHNIQUE_KEYWORD = "cspace";

  private final BigDecimal maxLength;

  SurfaceApproxCspaceTechniqueImp(BigDecimal maxLength) {
    super(TECHNIQUE_KEYWORD);
    this.maxLength = maxLength;
  }

  SurfaceApproxCspaceTechniqueImp(SurfaceApproxCspaceTechnique surfaceApproxCspaceTechnique) {
    this(surfaceApproxCspaceTechnique.getMaxLength());
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
    SurfaceApproxCspaceTechniqueImp other = (SurfaceApproxCspaceTechniqueImp) obj;
    if (maxLength == null) {
      if (other.maxLength != null)
        return false;
    } else if (!maxLength.equals(other.maxLength))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SurfaceApproxCspaceTechniqueImp [maxLength=" + maxLength + ", super.toString()="
        + super.toString() + "]";
  }
}
