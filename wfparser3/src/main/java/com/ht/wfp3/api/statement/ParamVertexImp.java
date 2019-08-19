package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class ParamVertexImp extends StatementImp implements ParamVertex {
  private static final String KEYWORD = "vp";
  private static final BigDecimal COORD_NOT_USED_VALUE = BigDecimal.valueOf(Double.MIN_VALUE);

  private final BigDecimal uCoord;
  private final BigDecimal vCoord;
  private final BigDecimal wCoord;

  private final boolean isVCoordSet;
  private final boolean isWCoordSet;

  private ParamVertexImp(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord,
      boolean vCoordIsSet, boolean wCoordIsSet) {
    super(KEYWORD);
    if (null == uCoord) {
      throw new NullPointerException("uCoord cannot be null");
    }
    if (null == vCoord) {
      throw new NullPointerException("vCoord cannot be null");
    }
    if (null == wCoord) {
      throw new NullPointerException("wCoord cannot be null");
    }
    this.uCoord = uCoord;
    this.vCoord = vCoord;
    this.wCoord = wCoord;

    this.isVCoordSet = vCoordIsSet;
    this.isWCoordSet = wCoordIsSet;
  }

  ParamVertexImp(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord) {
    this(uCoord, vCoord, wCoord, true, true);
  }

  ParamVertexImp(BigDecimal uCoord) {
    this(uCoord, COORD_NOT_USED_VALUE, COORD_NOT_USED_VALUE, false, false);
  }

  ParamVertexImp(BigDecimal uCoord, BigDecimal vCoord) {
    this(uCoord, vCoord, COORD_NOT_USED_VALUE, true, false);
  }

  @Override
  public BigDecimal getUCoord() {
    return uCoord;
  }

  @Override
  public BigDecimal getVCoord() {
    if (!isVCoordSet) {
      throw new UnsupportedOperationException("cannot access v coordinate when it is not set");
    }
    return vCoord;
  }

  @Override
  public BigDecimal getWCoord() {
    if (!isWCoordSet) {
      throw new UnsupportedOperationException("cannot access w coordinate when it is not set");
    }
    return wCoord;
  }

  @Override
  public boolean isVCoordSet() {
    return isVCoordSet;
  }

  @Override
  public boolean isWCoordSet() {
    return isWCoordSet;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((uCoord == null) ? 0 : uCoord.hashCode());
    result = prime * result + ((vCoord == null) ? 0 : vCoord.hashCode());
    result = prime * result + ((wCoord == null) ? 0 : wCoord.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ParamVertexImp other = (ParamVertexImp) obj;
    if (uCoord == null) {
      if (other.uCoord != null) {
        return false;
      }
    } else if (!uCoord.equals(other.uCoord)) {
      return false;
    }
    if (vCoord == null) {
      if (other.vCoord != null) {
        return false;
      }
    } else if (!vCoord.equals(other.vCoord)) {
      return false;
    }
    if (wCoord == null) {
      if (other.wCoord != null) {
        return false;
      }
    } else if (!wCoord.equals(other.wCoord)) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(ParamVertex o) {
    int compareTo = uCoord.compareTo(o.getUCoord());
    if (0 == compareTo) {
      compareTo = Boolean.compare(isVCoordSet, o.isVCoordSet());
      if (0 == compareTo && isVCoordSet) {
        compareTo = vCoord.compareTo(o.getVCoord());
        if (0 == compareTo) {
          compareTo = Boolean.compare(isWCoordSet, o.isWCoordSet());
          if (compareTo == 0 && isWCoordSet) {
            compareTo = wCoord.compareTo(o.getWCoord());
          }
        }
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "ParamVertexImp [uCoord=" + uCoord + ", vCoord=" + vCoord + ", wCoord=" + wCoord
        + ", isVCoordSet=" + isVCoordSet + ", isWCoordSet=" + isWCoordSet + ", super.toString()="
        + super.toString() + "]";
  }
}
