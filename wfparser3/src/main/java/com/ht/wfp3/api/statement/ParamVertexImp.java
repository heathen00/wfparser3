package com.ht.wfp3.api.statement;

class ParamVertexImp extends StatementImp implements ParamVertex {
  private static final String KEYWORD = "vp";
  private static final boolean CAN_COMMENT = true;

  private String uCoord;
  private String vCoord;
  private String wCoord;

  ParamVertexImp(String uCoord, String vCoord, String wCoord) {
    super(KEYWORD, CAN_COMMENT);
    this.uCoord = uCoord;
    this.vCoord = vCoord;
    this.wCoord = wCoord;
  }

  ParamVertexImp(ParamVertex paramVertex) {
    this(paramVertex.getUCoord(), paramVertex.getVCoord(), paramVertex.getWCoord());
  }

  @Override
  public String getUCoord() {
    return uCoord;
  }

  @Override
  public String getVCoord() {
    return vCoord;
  }

  @Override
  public String getWCoord() {
    return wCoord;
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
  public String toString() {
    return "ParamVertexImp [uCoord=" + uCoord + ", vCoord=" + vCoord + ", wCoord=" + wCoord
        + ", super.toString()=" + super.toString() + "]";
  }
}
