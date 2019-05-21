package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.List;

class SpecialPointImp extends StatementImp implements SpecialPoint {
  private static final String KEYWORD = "sp";

  private final List<ParamVertexReference> vertexReferenceList;

  SpecialPointImp(List<ParamVertexReference> vertexReferenceList) {
    super(KEYWORD);
    if (null == vertexReferenceList) {
      throw new NullPointerException("vertexReferenceList constructor parameter cannot be null");
    }
    if (MINIMUM_VERTEX_REFERENCES.compareTo(vertexReferenceList.size()) > 0) {
      throw new IllegalArgumentException(
          "vertexReferenceList constructor parameter must contain at least "
              + MINIMUM_VERTEX_REFERENCES + " members");
    }
    if (vertexReferenceList.contains(null)) {
      throw new IllegalArgumentException("vertexReferenceList cannot contain null members");
    }
    this.vertexReferenceList = new ArrayList<>(vertexReferenceList);
  }

  SpecialPointImp(SpecialPoint sp) {
    this(sp.getVertexReferenceList());
  }

  @Override
  public List<ParamVertexReference> getVertexReferenceList() {
    return vertexReferenceList;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((vertexReferenceList == null) ? 0 : vertexReferenceList.hashCode());
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
    SpecialPointImp other = (SpecialPointImp) obj;
    if (vertexReferenceList == null) {
      if (other.vertexReferenceList != null)
        return false;
    } else if (!vertexReferenceList.equals(other.vertexReferenceList))
      return false;
    return true;
  }

  @Override
  public int compareTo(Statement o) {
    int compareTo = super.compareTo(o);
    if (0 == compareTo) {
      SpecialPoint specialPoint = (SpecialPoint) o;
      ListOfComparableComparator<ParamVertexReference> listComparator =
          new ListOfComparableComparator<>();
      compareTo =
          listComparator.compare(vertexReferenceList, specialPoint.getVertexReferenceList());
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "SpecialPointImp [vertexReferenceList=" + vertexReferenceList + ", super.toString()="
        + super.toString() + "]";
  }
}
