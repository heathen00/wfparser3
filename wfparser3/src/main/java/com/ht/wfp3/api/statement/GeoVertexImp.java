package com.ht.wfp3.api.statement;

class GeoVertexImp implements GeoVertex {
  private String xCoord;
  private String yCoord;
  private String zCoord;
  private String wCoord;

  GeoVertexImp(String xCoord, String yCoord, String zCoord, String wCoord) {
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
  public boolean canComment() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getKeyword() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setComment(Comment comment) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getComment() {
    // TODO Auto-generated method stub
    return null;
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
    int result = 1;
    result = prime * result + ((wCoord == null) ? 0 : wCoord.hashCode());
    result = prime * result + ((xCoord == null) ? 0 : xCoord.hashCode());
    result = prime * result + ((yCoord == null) ? 0 : yCoord.hashCode());
    result = prime * result + ((zCoord == null) ? 0 : zCoord.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    System.out.println("here");
    if (this == obj) {
      return true;
    }
    if (obj == null) {
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
}
