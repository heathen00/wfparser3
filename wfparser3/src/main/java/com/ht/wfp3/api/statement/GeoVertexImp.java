package com.ht.wfp3.api.statement;

class GeoVertexImp extends StatementImp implements GeoVertex {
  private static final String KEYWORD = "v";
  private static final boolean CAN_COMMENT = true;
  
  private String xCoord;
  private String yCoord;
  private String zCoord;
  private String wCoord;

  GeoVertexImp(String xCoord, String yCoord, String zCoord, String wCoord) {
    super(KEYWORD, CAN_COMMENT);
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.zCoord = zCoord;
    this.wCoord = wCoord;
  }

  GeoVertexImp(GeoVertex geoVertex) {
    this(geoVertex.getXCoord(), geoVertex.getYCoord(), geoVertex.getZCoord(),
        geoVertex.getWCoord());
  }

  @Override
  public String getXCoord() {
    return xCoord;
  }

  @Override
  public String getYCoord() {
    return yCoord;
  }

  @Override
  public String getZCoord() {
    return zCoord;
  }

  @Override
  public String getWCoord() {
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
  public String toString() {
    return "GeoVertexImp [xCoord=" + xCoord + ", yCoord=" + yCoord + ", zCoord=" + zCoord
        + ", wCoord=" + wCoord + ", super.toString()=" + super.toString() + "]";
  }
}
