package com.ht.wfp3.api.statement;

abstract class CurveApproxImp extends StatementImp implements CurveApprox {
  private static final String KEYWORD = "ctech";

  private final CurveApprox.Technique curveApproxTechnique;

  CurveApproxImp(CurveApprox.Technique curveApproxTechnique) {
    super(KEYWORD);
    this.curveApproxTechnique = curveApproxTechnique;
  }

  CurveApproxImp(CurveApprox curveApprox) {
    this(curveApprox.getCurveApproximationTechnique());
  }

  @Override
  public Technique getCurveApproximationTechnique() {
    return curveApproxTechnique;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + ((curveApproxTechnique == null) ? 0 : curveApproxTechnique.hashCode());
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
    if (curveApproxTechnique != other.curveApproxTechnique)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CurveApproxImp [curveApproxTechnique=" + curveApproxTechnique + ", super.toString()="
        + super.toString() + "]";
  }
}
