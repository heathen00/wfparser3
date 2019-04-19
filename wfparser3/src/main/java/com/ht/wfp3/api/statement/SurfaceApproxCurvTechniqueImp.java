package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class SurfaceApproxCurvTechniqueImp extends SurfaceApproxImp implements SurfaceApproxCurvTechnique {
  private static final SurfaceApprox.Technique TECHNIQUE = SurfaceApprox.Technique.CURV;

  private final BigDecimal maxDistance;
  private final BigDecimal maxAngleInDegrees;

  SurfaceApproxCurvTechniqueImp(BigDecimal maxDistance, BigDecimal maxAngleInDegrees) {
    super(TECHNIQUE);
    this.maxDistance = maxDistance;
    this.maxAngleInDegrees = maxAngleInDegrees;
  }
  
  SurfaceApproxCurvTechniqueImp(SurfaceApproxCurvTechnique surfaceApproxCurvTechnique) {
    this(surfaceApproxCurvTechnique.getMaxDistance(), surfaceApproxCurvTechnique.getMaxAngleInDegrees());
  }

  @Override
  public BigDecimal getMaxDistance() {
    return maxDistance;
  }

  @Override
  public BigDecimal getMaxAngleInDegrees() {
    return maxAngleInDegrees;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((maxAngleInDegrees == null) ? 0 : maxAngleInDegrees.hashCode());
    result = prime * result + ((maxDistance == null) ? 0 : maxDistance.hashCode());
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
    SurfaceApproxCurvTechniqueImp other = (SurfaceApproxCurvTechniqueImp) obj;
    if (maxAngleInDegrees == null) {
      if (other.maxAngleInDegrees != null)
        return false;
    } else if (!maxAngleInDegrees.equals(other.maxAngleInDegrees))
      return false;
    if (maxDistance == null) {
      if (other.maxDistance != null)
        return false;
    } else if (!maxDistance.equals(other.maxDistance))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SurfaceApproxCurvTechniqueImp [maxDistance=" + maxDistance + ", maxAngleInDegrees="
        + maxAngleInDegrees + ", super.toString()=" + super.toString() + "]";
  }
}
