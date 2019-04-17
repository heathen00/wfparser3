package com.ht.wfp3.api.statement;

class DegreeImp extends StatementImp implements Degree {
  private static final String KEYWORD = "deg";
  private final Integer uAxisDegree;
  private final Integer vAxisDegree;
  
  DegreeImp(Integer uAxisDegree, Integer vAxisDegree) {
    super(KEYWORD);
    this.uAxisDegree = uAxisDegree;
    this.vAxisDegree = vAxisDegree;
  }

  DegreeImp(Degree deg) {
    this(deg.getUAxisDegree(), deg.getVAxisDegree());
  }

  @Override
  public Integer getUAxisDegree() {
    return uAxisDegree;
  }

  @Override
  public Integer getVAxisDegree() {
    return vAxisDegree;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((uAxisDegree == null) ? 0 : uAxisDegree.hashCode());
    result = prime * result + ((vAxisDegree == null) ? 0 : vAxisDegree.hashCode());
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
    DegreeImp other = (DegreeImp) obj;
    if (uAxisDegree == null) {
      if (other.uAxisDegree != null)
        return false;
    } else if (!uAxisDegree.equals(other.uAxisDegree))
      return false;
    if (vAxisDegree == null) {
      if (other.vAxisDegree != null)
        return false;
    } else if (!vAxisDegree.equals(other.vAxisDegree))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "DegreeImp [uAxisDegree=" + uAxisDegree + ", vAxisDegree=" + vAxisDegree
        + ", super.toString()=" + super.toString() + "]";
  }
}
