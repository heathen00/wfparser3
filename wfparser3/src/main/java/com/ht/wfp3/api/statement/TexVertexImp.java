package com.ht.wfp3.api.statement;

class TexVertexImp implements TexVertex {
  private String uCoord;
  private String vCoord;
  private String wCoord;
  
  TexVertexImp(String uCoord, String vCoord, String wCoord) {
    this.uCoord = uCoord;
    this.vCoord = vCoord;
    this.wCoord = wCoord;
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
  public String getUCoord() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getVCoord() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getWCoord() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
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
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    TexVertexImp other = (TexVertexImp) obj;
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
}
