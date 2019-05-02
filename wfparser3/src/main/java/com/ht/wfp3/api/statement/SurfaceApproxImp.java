package com.ht.wfp3.api.statement;

abstract class SurfaceApproxImp extends StatementImp implements SurfaceApprox {
  private static final String KEYWORD = "stech";

  private final String techniqueKeyword;

  SurfaceApproxImp(String techniqueKeyword) {
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
    SurfaceApproxImp other = (SurfaceApproxImp) obj;
    if (techniqueKeyword == null) {
      if (other.techniqueKeyword != null)
        return false;
    } else if (!techniqueKeyword.equals(other.techniqueKeyword))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SurfaceApproxImp [techniqueKeyword=" + techniqueKeyword + ", super.toString()="
        + super.toString() + "]";
  }
}
