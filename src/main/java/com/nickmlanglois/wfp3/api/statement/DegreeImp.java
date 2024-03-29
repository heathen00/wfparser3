package com.nickmlanglois.wfp3.api.statement;

class DegreeImp extends StatementImp implements Degree {
  private static final String KEYWORD = "deg";

  private static final Integer V_AXIS_NOT_SET = Integer.MIN_VALUE;

  private final Integer uAxisDegree;
  private final Integer vAxisDegree;

  DegreeImp(Integer uAxisDegree, Integer vAxisDegree) {
    super(KEYWORD);
    if (null == uAxisDegree) {
      throw new NullPointerException("uAxisDegree cannot be null");
    }
    if (null == vAxisDegree) {
      throw new NullPointerException("vAxisDegree cannot be null");
    }
    if (MINIMUM_DEGREE.compareTo(uAxisDegree) > 0) {
      throw new IllegalArgumentException("uAxisDegree must be greater than " + MINIMUM_DEGREE);
    }
    if (MINIMUM_DEGREE.compareTo(vAxisDegree) > 0 && !V_AXIS_NOT_SET.equals(vAxisDegree)) {
      throw new IllegalArgumentException("vAxisDegree must be greater than " + MINIMUM_DEGREE);
    }
    this.uAxisDegree = uAxisDegree;
    this.vAxisDegree = vAxisDegree;
  }

  DegreeImp(Integer uAxisDegree) {
    this(uAxisDegree, V_AXIS_NOT_SET);
  }

  DegreeImp(Degree deg) {
    this(deg.getUAxisDegree(), deg.isVAxisDegreeSet() ? deg.getVAxisDegree() : V_AXIS_NOT_SET);
  }

  @Override
  public Integer getUAxisDegree() {
    return uAxisDegree;
  }

  @Override
  public Integer getVAxisDegree() {
    if (V_AXIS_NOT_SET.equals(vAxisDegree)) {
      throw new UnsupportedOperationException("v axis degree is not set");
    }
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
  public int compareTo(Degree o) {
    int compareTo = uAxisDegree.compareTo(o.getUAxisDegree());
    if (0 == compareTo) {
      compareTo = Boolean.compare(isVAxisDegreeSet(), o.isVAxisDegreeSet());
      if (0 == compareTo && isVAxisDegreeSet()) {
        compareTo = vAxisDegree.compareTo(o.getVAxisDegree());
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "DegreeImp [uAxisDegree=" + uAxisDegree + ", vAxisDegree=" + vAxisDegree
        + ", super.toString()=" + super.toString() + "]";
  }

  @Override
  public boolean isVAxisDegreeSet() {
    return !V_AXIS_NOT_SET.equals(vAxisDegree);
  }
}
