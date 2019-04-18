package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class CurveApproxCurvTechniqueImp extends CurveApproxImp implements CurveApproxCurvTechnique {
  private static final CurveApprox.Technique TECHNIQUE = CurveApprox.Technique.CURV;

  private final BigDecimal maximumDistance;
  private final BigDecimal maximumAngleInDegrees;

  CurveApproxCurvTechniqueImp(BigDecimal maximumDistance, BigDecimal maximumAngleInDegrees) {
    super(TECHNIQUE);
    this.maximumDistance = maximumDistance;
    this.maximumAngleInDegrees = maximumAngleInDegrees;
  }

  CurveApproxCurvTechniqueImp(CurveApproxCurvTechnique curveApproxCurvTechnique) {
    this(curveApproxCurvTechnique.getMaximumDistance(),
        curveApproxCurvTechnique.getMaximumAngleInDegrees());
  }

  @Override
  public BigDecimal getMaximumDistance() {
    return maximumDistance;
  }

  @Override
  public BigDecimal getMaximumAngleInDegrees() {
    return maximumAngleInDegrees;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + ((maximumAngleInDegrees == null) ? 0 : maximumAngleInDegrees.hashCode());
    result = prime * result + ((maximumDistance == null) ? 0 : maximumDistance.hashCode());
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
    CurveApproxCurvTechniqueImp other = (CurveApproxCurvTechniqueImp) obj;
    if (maximumAngleInDegrees == null) {
      if (other.maximumAngleInDegrees != null)
        return false;
    } else if (!maximumAngleInDegrees.equals(other.maximumAngleInDegrees))
      return false;
    if (maximumDistance == null) {
      if (other.maximumDistance != null)
        return false;
    } else if (!maximumDistance.equals(other.maximumDistance))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CurveApproxCurvTechniqueImp [maximumDistance=" + maximumDistance
        + ", maximumAngleInDegrees=" + maximumAngleInDegrees + ", super.toString()="
        + super.toString() + "]";
  }
}
