package com.nickmlanglois.wfp3.api.statement;

import java.math.BigDecimal;

class SurfaceApproxCparmbTechniqueImp extends SurfaceApproxImp
    implements SurfaceApproxCparmbTechnique {
  private static final String TECHNIQUE_KEYWORD = "cparmb";

  private final BigDecimal resolutionForUAndVAxes;

  SurfaceApproxCparmbTechniqueImp(BigDecimal resolutionForUAndVAxes) {
    super(TECHNIQUE_KEYWORD);
    if (null == resolutionForUAndVAxes) {
      throw new NullPointerException("resolutionForUAndVAxes cannot be null");
    }
    if (MINIMUM_RESOLUTION.compareTo(resolutionForUAndVAxes) > 0) {
      throw new IllegalArgumentException(
          "resolutionForUAndVAxes must be greater or equal to " + MINIMUM_RESOLUTION);
    }
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
  public int compareTo(SurfaceApproxCparmbTechnique o) {
    return resolutionForUAndVAxes.compareTo(o.getResolutionForUAndVAxes());
  }

  @Override
  public String toString() {
    return "SurfaceApproxCparmbTechniqueImp [resolutionForUAndVAxes=" + resolutionForUAndVAxes
        + ", super.toString()=" + super.toString() + "]";
  }
}
