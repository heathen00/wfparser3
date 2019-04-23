package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.List;

class SpecialPointImp extends StatementImp implements SpecialPoint {
  private static final String KEYWORD = "sp";

  private final List<VertexReference> vertexReferenceList;

  SpecialPointImp(List<VertexReference> vertexReferenceList) {
    super(KEYWORD);
    this.vertexReferenceList = new ArrayList<>(vertexReferenceList);
  }

  SpecialPointImp(SpecialPoint sp) {
    this(sp.getVertexReferenceList());
  }

  @Override
  public List<VertexReference> getVertexReferenceList() {
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
  public String toString() {
    return "SpecialPointImp [vertexReferenceList=" + vertexReferenceList + ", super.toString()="
        + super.toString() + "]";
  }
}
