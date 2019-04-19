package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class SurfaceApproxCparmbTechniqueImp extends SurfaceApproxImp
    implements SurfaceApproxCparmbTechnique {
  private static final SurfaceApprox.Technique TECHNIQUE = SurfaceApprox.Technique.CPARMB;

  private final BigDecimal resolutionForUAndVAxes;

  SurfaceApproxCparmbTechniqueImp(BigDecimal resolutionForUAndVAxes) {
    super(TECHNIQUE);
    this.resolutionForUAndVAxes = resolutionForUAndVAxes;
  }

  SurfaceApproxCparmbTechniqueImp(SurfaceApproxCparmbTechnique surfaceApproxCparmbTechnique) {
    this(surfaceApproxCparmbTechnique.getResolutionForUAndVAxes());
  }

  @Override
  public BigDecimal getResolutionForUAndVAxes() {
    return resolutionForUAndVAxes;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + ((resolutionForUAndVAxes == null) ? 0 : resolutionForUAndVAxes.hashCode());
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
    SurfaceApproxCparmbTechniqueImp other = (SurfaceApproxCparmbTechniqueImp) obj;
    if (resolutionForUAndVAxes == null) {
      if (other.resolutionForUAndVAxes != null)
        return false;
    } else if (!resolutionForUAndVAxes.equals(other.resolutionForUAndVAxes))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SurfaceApproxCparmbTechniqueImp [resolutionForUAndVAxes=" + resolutionForUAndVAxes
        + ", super.toString()=" + super.toString() + "]";
  }
}
