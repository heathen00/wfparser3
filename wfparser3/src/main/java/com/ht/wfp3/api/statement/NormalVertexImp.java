package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class NormalVertexImp extends StatementImp implements NormalVertex {
  private static final String KEYWORD = "vn";
  private static final boolean CAN_COMMENT = true;

  private BigDecimal iCoord;
  private BigDecimal jCoord;
  private BigDecimal kCoord;

  NormalVertexImp(BigDecimal iCoord, BigDecimal jCoord, BigDecimal kCoord) {
    super(KEYWORD, CAN_COMMENT);
    this.iCoord = iCoord;
    this.jCoord = jCoord;
    this.kCoord = kCoord;
  }

  NormalVertexImp(NormalVertex normalVertex) {
    this(normalVertex.getICoord(), normalVertex.getJCoord(), normalVertex.getKCoord());
  }

  @Override
  public BigDecimal getICoord() {
    return iCoord;
  }

  @Override
  public BigDecimal getJCoord() {
    return jCoord;
  }

  @Override
  public BigDecimal getKCoord() {
    return kCoord;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
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
    if (!super.equals(obj)) {
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
    return "NormalVertexImp [iCoord=" + iCoord + ", jCoord=" + jCoord + ", kCoord=" + kCoord
        + ", super.toString()=" + super.toString() + "]";
  }
}
