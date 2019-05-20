package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class CurveApproxCparmTechniqueImp extends CurveApproxImp implements CurveApproxCparmTechnique {
  private static final String TECHNIQUE_KEYWORD = "cparm";

  private final BigDecimal resolution;

  CurveApproxCparmTechniqueImp(BigDecimal resolution) {
    super(TECHNIQUE_KEYWORD);
    if (null == resolution) {
      throw new NullPointerException("resolution constructor parameter cannot be null");
    }
    if (MINIMUM_RESOLUTION.compareTo(resolution) > 0) {
      throw new IllegalArgumentException(
          "resolution constructor parameter must be greater or equal to "
              + MINIMUM_RESOLUTION.doubleValue());
    }
    this.resolution = resolution;
  }

  CurveApproxCparmTechniqueImp(CurveApproxCparmTechnique curveApproxCparmTechnique) {
    this(curveApproxCparmTechnique.getResolution());
  }

  @Override
  public BigDecimal getResolution() {
    return resolution;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
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
    CurveApproxCparmTechniqueImp other = (CurveApproxCparmTechniqueImp) obj;
    if (resolution == null) {
      if (other.resolution != null)
        return false;
    } else if (!resolution.equals(other.resolution))
      return false;
    return true;
  }

  @Override
  public int compareTo(Statement o) {
    int compareTo = super.compareTo(o);
    if (0 == compareTo) {
      CurveApproxCparmTechnique curveApproxCparmTechnique = (CurveApproxCparmTechnique) o;
      compareTo = resolution.compareTo(curveApproxCparmTechnique.getResolution());
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "CurveApproxCparmTechniqueImp [resolution=" + resolution + ", super.toString()="
        + super.toString() + "]";
  }
}
