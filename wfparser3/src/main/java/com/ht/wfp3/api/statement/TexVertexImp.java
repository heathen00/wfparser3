package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class TexVertexImp extends StatementImp implements TexVertex {
  private static final String KEYWORD = "vt";

  private final BigDecimal uCoord;
  private final BigDecimal vCoord;
  private final BigDecimal wCoord;

  TexVertexImp(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord) {
    super(KEYWORD);
    if (null == uCoord) {
      throw new NullPointerException("uCoord constructor parameter cannot be null");
    }
    if (null == vCoord) {
      throw new NullPointerException("vCoord constructor parameter cannot be null");
    }
    if (null == wCoord) {
      throw new NullPointerException("wCoord constructor parameter cannot be null");
    }
    this.uCoord = uCoord;
    this.vCoord = vCoord;
    this.wCoord = wCoord;
  }

  TexVertexImp(BigDecimal uCoord, BigDecimal vCoord) {
    this(uCoord, vCoord, DEFAULT_W_COORD);
  }

  TexVertexImp(BigDecimal uCoord) {
    this(uCoord, DEFAULT_V_COORD, DEFAULT_W_COORD);
  }

  TexVertexImp(TexVertex texVertex) {
    this(texVertex.getUCoord(), texVertex.getVCoord(), texVertex.getWCoord());
  }

  @Override
  public BigDecimal getUCoord() {
    return uCoord;
  }

  @Override
  public BigDecimal getVCoord() {
    return vCoord;
  }

  @Override
  public BigDecimal getWCoord() {
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

  @Override
  public String toString() {
    return "TexVertexImp [uCoord=" + uCoord + ", vCoord=" + vCoord + ", wCoord=" + wCoord
        + ", super.toString()=" + super.toString() + "]";
  }
}
