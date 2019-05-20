package com.ht.wfp3.api.statement;

abstract class CurveApproxImp extends StatementImp implements CurveApprox {
  private static final String KEYWORD = "ctech";

  private final String techniqueKeyword;

  CurveApproxImp(String techniqueKeyword) {
    super(KEYWORD);
    this.techniqueKeyword = techniqueKeyword;
  }

  @Override
  public String getTechniqueKeyword() {
    return techniqueKeyword;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((techniqueKeyword == null) ? 0 : techniqueKeyword.hashCode());
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
    CurveApproxImp other = (CurveApproxImp) obj;
    if (techniqueKeyword == null) {
      if (other.techniqueKeyword != null)
        return false;
    } else if (!techniqueKeyword.equals(other.techniqueKeyword))
      return false;
    return true;
  }

  @Override
  public int compareTo(Statement o) {
    int compareTo = super.compareTo(o);
    if (0 == compareTo) {
      CurveApprox curveApprox = (CurveApprox) o;
      compareTo = techniqueKeyword.compareTo(curveApprox.getTechniqueKeyword());
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "CurveApproxImp [techniqueKeyword=" + techniqueKeyword + ", super.toString()="
        + super.toString() + "]";
  }
}
