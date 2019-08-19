package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.List;

class Curve2DImp extends StatementImp implements Curve2D {
  private static final String KEYWORD = "curv2";

  private final List<ParamVertexReference> controlPointVertexReferenceList;

  Curve2DImp(List<ParamVertexReference> controlPointVertexReferenceList) {
    super(KEYWORD);
    if (null == controlPointVertexReferenceList) {
      throw new NullPointerException("controlPointVertexReferenceList cannot be null");
    }
    if (controlPointVertexReferenceList.contains(null)) {
      throw new IllegalArgumentException(
          "controlPointVertexReferenceList cannot contain null members");
    }
    if (controlPointVertexReferenceList.size() < MINIMUM_CONTROL_POINTS) {
      throw new IllegalArgumentException("controlPointVertexReferenceList requires a minimum of "
          + MINIMUM_CONTROL_POINTS + " control points");
    }
    this.controlPointVertexReferenceList = new ArrayList<>(controlPointVertexReferenceList);
  }

  Curve2DImp(Curve2D curv2) {
    this(curv2.getControlPointVertexReferences());
  }

  @Override
  public List<ParamVertexReference> getControlPointVertexReferences() {
    return controlPointVertexReferenceList;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((controlPointVertexReferenceList == null) ? 0
        : controlPointVertexReferenceList.hashCode());
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
    Curve2DImp other = (Curve2DImp) obj;
    if (controlPointVertexReferenceList == null) {
      if (other.controlPointVertexReferenceList != null)
        return false;
    } else if (!controlPointVertexReferenceList.equals(other.controlPointVertexReferenceList))
      return false;
    return true;
  }

  @Override
  public int compareTo(Curve2D o) {
    ListOfComparableComparator<ParamVertexReference> listComparator =
        new ListOfComparableComparator<>();
    return listComparator.compare(controlPointVertexReferenceList,
        o.getControlPointVertexReferences());
  }

  @Override
  public String toString() {
    return "Curve2DImp [controlPointVertexReferenceList=" + controlPointVertexReferenceList
        + ", super.toString()=" + super.toString() + "]";
  }
}
