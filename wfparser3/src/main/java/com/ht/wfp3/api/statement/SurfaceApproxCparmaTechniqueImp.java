package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class SurfaceApproxCparmaTechniqueImp extends SurfaceApproxImp
    implements SurfaceApproxCparmaTechnique {
  private static final SurfaceApprox.Technique TECHNIQUE = SurfaceApprox.Technique.CPARMA;

  private final BigDecimal resolutionForUAxis;
  private final BigDecimal resolutionForVAxis;

  SurfaceApproxCparmaTechniqueImp(BigDecimal resolutionForUAxis, BigDecimal resolutionForVAxis) {
    super(TECHNIQUE);
    this.resolutionForUAxis = resolutionForUAxis;
    this.resolutionForVAxis = resolutionForVAxis;
  }

  SurfaceApproxCparmaTechniqueImp(SurfaceApproxCparmaTechnique surfaceApproxCparmaTechnique) {
    this(surfaceApproxCparmaTechnique.getResolutionForUAxis(),
        surfaceApproxCparmaTechnique.getResolutionForVAxis());
  }

  @Override
  public BigDecimal getResolutionForUAxis() {
    return resolutionForUAxis;
  }

  @Override
  public BigDecimal getResolutionForVAxis() {
    return resolutionForVAxis;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((resolutionForUAxis == null) ? 0 : resolutionForUAxis.hashCode());
    result = prime * result + ((resolutionForVAxis == null) ? 0 : resolutionForVAxis.hashCode());
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
    SurfaceApproxCparmaTechniqueImp other = (SurfaceApproxCparmaTechniqueImp) obj;
    if (resolutionForUAxis == null) {
      if (other.resolutionForUAxis != null)
        return false;
    } else if (!resolutionForUAxis.equals(other.resolutionForUAxis))
      return false;
    if (resolutionForVAxis == null) {
      if (other.resolutionForVAxis != null)
        return false;
    } else if (!resolutionForVAxis.equals(other.resolutionForVAxis))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SurfaceApproxCparmaTechniqueImp [resolutionForUAxis=" + resolutionForUAxis
        + ", resolutionForVAxis=" + resolutionForVAxis + ", super.toString()=" + super.toString() + "]";
  }
}
