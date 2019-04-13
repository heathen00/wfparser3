package com.ht.wfp3.api.statement;

class VertexReferenceImp {
  enum Type {
    GEOMETRIC, TEXTURE, NORMAL, PARAMETER,
  }
  static final int INDEX_NOT_SET_VALUE = 0;

  private final Type type;
  private final Integer vertexIndex;
  private final boolean isSet;

  VertexReferenceImp(VertexReferenceImp.Type type, Integer vertexIndex, boolean isSet) {
    super();
    this.type = type;
    this.vertexIndex = vertexIndex;
    this.isSet = isSet;
  }

  VertexReferenceImp(VertexReferenceImp vertexReference) {
    this(vertexReference.getVertexType(), vertexReference.getVertexIndex(),
        vertexReference.isSet());
  }

  public boolean isSet() {
    return isSet;
  }

  public Integer getVertexIndex() {
    return vertexIndex;
  }

  public Type getVertexType() {
    return type;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (isSet ? 1231 : 1237);
    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
    if (type != other.type)
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
    return "VertexReferenceImp [type=" + type + ", vertexIndex=" + vertexIndex + ", isSet=" + isSet
        + "]";
  }
}
