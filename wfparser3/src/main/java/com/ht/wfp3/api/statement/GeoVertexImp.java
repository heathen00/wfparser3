package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class GeoVertexImp extends StatementImp implements GeoVertex {
  private static final String KEYWORD = "v";

  private final BigDecimal xCoord;
  private final BigDecimal yCoord;
  private final BigDecimal zCoord;
  private final BigDecimal wCoord;

  GeoVertexImp(BigDecimal xCoord, BigDecimal yCoord, BigDecimal zCoord, BigDecimal wCoord) {
    super(KEYWORD);
    if (null == xCoord) {
      throw new NullPointerException("xCoord cannot be null");
    }
    if (null == yCoord) {
      throw new NullPointerException("yCoord cannot be null");
    }
    if (null == zCoord) {
      throw new NullPointerException("zCoord cannot be null");
    }
    if (null == wCoord) {
      throw new NullPointerException("wCoord cannot be null");
    }
    this.xCoord = defensiveCopy(xCoord);
    this.yCoord = defensiveCopy(yCoord);
    this.zCoord = defensiveCopy(zCoord);
    this.wCoord = defensiveCopy(wCoord);
  }

  GeoVertexImp(BigDecimal xCoord, BigDecimal yCoord, BigDecimal zCoord) {
    this(xCoord, yCoord, zCoord, DEFAULT_W_COORD);
  }

  GeoVertexImp(GeoVertex geoVertex) {
    this(geoVertex.getXCoord(), geoVertex.getYCoord(), geoVertex.getZCoord(),
        geoVertex.getWCoord());
  }

  @Override
  public BigDecimal getXCoord() {
    return xCoord;
  }

  @Override
  public BigDecimal getYCoord() {
    return yCoord;
  }

  @Override
  public BigDecimal getZCoord() {
    return zCoord;
  }

  @Override
  public BigDecimal getWCoord() {
    return wCoord;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((wCoord == null) ? 0 : wCoord.hashCode());
    result = prime * result + ((xCoord == null) ? 0 : xCoord.hashCode());
    result = prime * result + ((yCoord == null) ? 0 : yCoord.hashCode());
    result = prime * result + ((zCoord == null) ? 0 : zCoord.hashCode());
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
    GeoVertexImp other = (GeoVertexImp) obj;
    if (wCoord == null) {
      if (other.wCoord != null) {
        return false;
      }
    } else if (!wCoord.equals(other.wCoord)) {
      return false;
    }
    if (xCoord == null) {
      if (other.xCoord != null) {
        return false;
      }
    } else if (!xCoord.equals(other.xCoord)) {
      return false;
    }
    if (yCoord == null) {
      if (other.yCoord != null) {
        return false;
      }
    } else if (!yCoord.equals(other.yCoord)) {
      return false;
    }
    if (zCoord == null) {
      if (other.zCoord != null) {
        return false;
      }
    } else if (!zCoord.equals(other.zCoord)) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(GeoVertex o) {
    int compareTo = xCoord.compareTo(o.getXCoord());
    if (0 == compareTo) {
      compareTo = yCoord.compareTo(o.getYCoord());
      if (0 == compareTo) {
        compareTo = zCoord.compareTo(o.getZCoord());
        if (0 == compareTo) {
          compareTo = wCoord.compareTo(o.getWCoord());
        }
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "GeoVertexImp [xCoord=" + xCoord + ", yCoord=" + yCoord + ", zCoord=" + zCoord
        + ", wCoord=" + wCoord + ", super.toString()=" + super.toString() + "]";
  }
}
