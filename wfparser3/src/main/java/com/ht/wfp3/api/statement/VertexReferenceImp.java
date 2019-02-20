package com.ht.wfp3.api.statement;

class VertexReferenceImp implements VertexReference {
  private Type type;
  private String vertexIndex;

  VertexReferenceImp(VertexReference.Type type, String vertexIndex) {
    this.type = type;
    this.vertexIndex = vertexIndex;
  }

  VertexReferenceImp(VertexReference vertexReference) {
    this.type = vertexReference.getVertex();
    this.vertexIndex = getVertexIndexAsString();
  }

  @Override
  public boolean hasValue() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int getValue() {
    // TODO Auto-generated method stub
    return 0;
  }

  String getVertexIndexAsString() {
    return vertexIndex;
  }

  @Override
  public Type getVertex() {
    return type;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((vertexIndex == null) ? 0 : vertexIndex.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    VertexReferenceImp other = (VertexReferenceImp) obj;
    if (type != other.type) {
      return false;
    }
    if (vertexIndex == null) {
      if (other.vertexIndex != null) {
        return false;
      }
    } else if (!vertexIndex.equals(other.vertexIndex)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "VertexReferenceImp [type=" + type + ", vertexIndex=" + vertexIndex + "]";
  }
}
