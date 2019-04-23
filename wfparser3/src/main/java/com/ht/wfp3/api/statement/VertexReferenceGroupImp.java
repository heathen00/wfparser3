package com.ht.wfp3.api.statement;

class VertexReferenceGroupImp implements VertexReferenceGroup {
  private final VertexReference geometricVertexReference;
  private final VertexReference textureVertexReference;
  private final VertexReference normalVervexReference;

  VertexReferenceGroupImp(VertexReference geometricVertexReference,
      VertexReference textureVertexReference, VertexReference normalVertexReference) {
    this.geometricVertexReference = geometricVertexReference;
    this.textureVertexReference = textureVertexReference;
    this.normalVervexReference = normalVertexReference;
  }

  VertexReferenceGroupImp(VertexReferenceGroup vertexReferenceGroup) {
    this(vertexReferenceGroup.getGeoVertexRef(), vertexReferenceGroup.getTexVertexRef(),
        vertexReferenceGroup.getNormalVertexRef());
  }

  @Override
  public VertexReference getGeoVertexRef() {
    return geometricVertexReference;
  }

  @Override
  public VertexReference getTexVertexRef() {
    return textureVertexReference;
  }

  @Override
  public VertexReference getNormalVertexRef() {
    return normalVervexReference;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((geometricVertexReference == null) ? 0 : geometricVertexReference.hashCode());
    result =
        prime * result + ((normalVervexReference == null) ? 0 : normalVervexReference.hashCode());
    result =
        prime * result + ((textureVertexReference == null) ? 0 : textureVertexReference.hashCode());
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
    VertexReferenceGroupImp other = (VertexReferenceGroupImp) obj;
    if (geometricVertexReference == null) {
      if (other.geometricVertexReference != null)
        return false;
    } else if (!geometricVertexReference.equals(other.geometricVertexReference))
      return false;
    if (normalVervexReference == null) {
      if (other.normalVervexReference != null)
        return false;
    } else if (!normalVervexReference.equals(other.normalVervexReference))
      return false;
    if (textureVertexReference == null) {
      if (other.textureVertexReference != null)
        return false;
    } else if (!textureVertexReference.equals(other.textureVertexReference))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "VertexReferenceGroupImp [geometricVertexReference=" + geometricVertexReference
        + ", textureVertexReference=" + textureVertexReference + ", normalVervexReference="
        + normalVervexReference + "]";
  }
}
