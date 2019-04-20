package com.ht.wfp3.api.statement;

class CurveOrSurfaceTypeImp extends StatementImp implements CurveOrSurfaceType {
  private static final String KEYWORD = "cstype";

  private final boolean isRational;
  private final Key key;

  CurveOrSurfaceTypeImp(boolean isRational, Key key) {
    super(KEYWORD);
    this.isRational = isRational;
    this.key = key;
  }

  CurveOrSurfaceTypeImp(CurveOrSurfaceType curveOrSurfaceType) {
    this(curveOrSurfaceType.isRational(), curveOrSurfaceType.getTypeKey());
  }

  @Override
  public boolean isRational() {
    return isRational;
  }

  @Override
  public Key getTypeKey() {
    return key;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (isRational ? 1231 : 1237);
    result = prime * result + ((key == null) ? 0 : key.hashCode());
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
    CurveOrSurfaceTypeImp other = (CurveOrSurfaceTypeImp) obj;
    if (isRational != other.isRational)
      return false;
    if (key != other.key)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CurveOrSurfaceTypeImp [isRational=" + isRational + ", key=" + key
        + ", super.toString()=" + super.toString() + "]";
  }
}
