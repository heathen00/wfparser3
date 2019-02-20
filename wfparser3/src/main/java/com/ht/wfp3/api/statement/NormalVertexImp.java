package com.ht.wfp3.api.statement;

class NormalVertexImp implements NormalVertex {
  private String iCoord;
  private String jCoord;
  private String kCoord;

  NormalVertexImp(String iCoord, String jCoord, String kCoord) {
    this.iCoord = iCoord;
    this.jCoord = jCoord;
    this.kCoord = kCoord;
  }
  
  NormalVertexImp(NormalVertex normalVertex) {
    this(normalVertex.getICoord(), normalVertex.getJCoord(), normalVertex.getKCoord());
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
  public String getICoord() {
    return iCoord;
  }

  @Override
  public String getJCoord() {
    return jCoord;
  }

  @Override
  public String getKCoord() {
    return kCoord;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((iCoord == null) ? 0 : iCoord.hashCode());
    result = prime * result + ((jCoord == null) ? 0 : jCoord.hashCode());
    result = prime * result + ((kCoord == null) ? 0 : kCoord.hashCode());
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
    NormalVertexImp other = (NormalVertexImp) obj;
    if (iCoord == null) {
      if (other.iCoord != null) {
        return false;
      }
    } else if (!iCoord.equals(other.iCoord)) {
      return false;
    }
    if (jCoord == null) {
      if (other.jCoord != null) {
        return false;
      }
    } else if (!jCoord.equals(other.jCoord)) {
      return false;
    }
    if (kCoord == null) {
      if (other.kCoord != null) {
        return false;
      }
    } else if (!kCoord.equals(other.kCoord)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "NormalVertexImp [iCoord=" + iCoord + ", jCoord=" + jCoord + ", kCoord=" + kCoord + "]";
  }
}
