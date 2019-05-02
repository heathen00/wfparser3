package com.ht.wfp3.api.statement;

class VertexReferenceImp implements VertexReference {

  private final Integer vertexIndex;
  private final boolean isSet;

  VertexReferenceImp(Integer vertexIndex) {
    super();
    this.vertexIndex = vertexIndex;
    this.isSet = (vertexIndex.equals(INDEX_NOT_SET_VALUE) ? false : true);
  }

  VertexReferenceImp(VertexReference vertexReference) {
    this(vertexReference.getVertexIndex());
  }

  @Override
  public boolean isSet() {
    return isSet;
  }

  @Override
  public Integer getVertexIndex() {
    return vertexIndex;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (isSet ? 1231 : 1237);
    result = prime * result + ((vertexIndex == null) ? 0 : vertexIndex.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    VertexReferenceImp other = (VertexReferenceImp) obj;
    if (isSet != other.isSet)
      return false;
    if (vertexIndex == null) {
      if (other.vertexIndex != null)
        return false;
    } else if (!vertexIndex.equals(other.vertexIndex))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "VertexReferenceImp [vertexIndex=" + vertexIndex + ", isSet=" + isSet
        + ", super.toString()=" + super.toString() + "]";
  }
}
