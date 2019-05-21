package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class SurfaceApproxCurvTechniqueImp extends SurfaceApproxImp implements SurfaceApproxCurvTechnique {
  private static final String TECHNIQUE_KEYWORD = "curv";

  private final BigDecimal maxDistance;
  private final BigDecimal maxAngleInDegrees;

  SurfaceApproxCurvTechniqueImp(BigDecimal maxDistance, BigDecimal maxAngleInDegrees) {
    super(TECHNIQUE_KEYWORD);
    if (null == maxDistance) {
      throw new NullPointerException("maxDistance constructor parameter cannot be null");
    }
    if (null == maxAngleInDegrees) {
      throw new NullPointerException("maxAngleInDegrees constructor parameter cannot be null");
    }
    if (MINIMUM_MAX_DISTANCE.compareTo(maxDistance) >= 0) {
      throw new IllegalArgumentException(
          "maxDistance constructor parameter must be greater than " + MINIMUM_MAX_DISTANCE);
    }
    if (MINIMUM_MAX_ANGLE.compareTo(maxAngleInDegrees) >= 0) {
      throw new IllegalArgumentException(
          "maxAngleInDegrees constructor parameter must be greater than " + MINIMUM_MAX_ANGLE);
    }
    this.maxDistance = maxDistance;
    this.maxAngleInDegrees = maxAngleInDegrees;
  }

  SurfaceApproxCurvTechniqueImp(SurfaceApproxCurvTechnique surfaceApproxCurvTechnique) {
    this(surfaceApproxCurvTechnique.getMaxDistance(),
        surfaceApproxCurvTechnique.getMaxAngleInDegrees());
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
  public int compareTo(Statement o) {
    int compareTo = super.compareTo(o);
    if (0 == compareTo) {
      SurfaceApproxCurvTechnique surfaceApproxCurvTechnique = (SurfaceApproxCurvTechnique) o;
      compareTo = maxDistance.compareTo(surfaceApproxCurvTechnique.getMaxDistance());
      if (0 == compareTo) {
        compareTo = maxAngleInDegrees.compareTo(surfaceApproxCurvTechnique.getMaxAngleInDegrees());
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "SurfaceApproxCurvTechniqueImp [maxDistance=" + maxDistance + ", maxAngleInDegrees="
        + maxAngleInDegrees + ", super.toString()=" + super.toString() + "]";
  }
}
