package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class SurfaceApproxCparmaTechniqueImp extends SurfaceApproxImp
    implements SurfaceApproxCparmaTechnique {
  private static final String TECHNIQUE_KEYWORD = "cparma";

  private final BigDecimal resolutionForUAxis;
  private final BigDecimal resolutionForVAxis;

  SurfaceApproxCparmaTechniqueImp(BigDecimal resolutionForUAxis, BigDecimal resolutionForVAxis) {
    super(TECHNIQUE_KEYWORD);
    if (null == resolutionForUAxis) {
      throw new NullPointerException("resolutionForUAxis constructor parameter cannot be null");
    }
    if (null == resolutionForVAxis) {
      throw new NullPointerException("resolutionForVAxis constructor parameter cannot be null");
    }
    if (MINIMUM_RESOLUTION_FOR_U_AXIS.compareTo(resolutionForUAxis) > 0) {
      throw new IllegalArgumentException(
          "resolutionForUAxis constructor parameter must be greater or equal to "
              + MINIMUM_RESOLUTION_FOR_U_AXIS);
    }
    if (MINIMUM_RESOLUTION_FOR_V_AXIS.compareTo(resolutionForVAxis) > 0) {
      throw new IllegalArgumentException(
          "resolutionForVAxis constructor parameter must be greater or equal to "
              + MINIMUM_RESOLUTION_FOR_V_AXIS);
    }
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
  public int compareTo(Statement o) {
    int compareTo = super.compareTo(o);
    if (0 == compareTo) {
      SurfaceApproxCparmaTechnique surfaceApproxCparmaTechnique = (SurfaceApproxCparmaTechnique) o;
      compareTo =
          resolutionForUAxis.compareTo(surfaceApproxCparmaTechnique.getResolutionForUAxis());
      if (0 == compareTo) {
        compareTo =
            resolutionForVAxis.compareTo(surfaceApproxCparmaTechnique.getResolutionForVAxis());
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "SurfaceApproxCparmaTechniqueImp [resolutionForUAxis=" + resolutionForUAxis
        + ", resolutionForVAxis=" + resolutionForVAxis + ", super.toString()=" + super.toString()
        + "]";
  }
}
