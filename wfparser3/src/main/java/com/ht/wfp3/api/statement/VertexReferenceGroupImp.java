package com.ht.wfp3.api.statement;

class VertexReferenceGroupImp implements VertexReferenceGroup {
  private final VertexReferenceImp geometricVertexReference;
  private final VertexReferenceImp textureVertexReference;
  private final VertexReferenceImp normalVervexReference;
  private final VertexReferenceImp parameterVertexReference;

  VertexReferenceGroupImp(VertexReferenceImp geometricVertexReference,
      VertexReferenceImp textureVertexReference, VertexReferenceImp normalVertexReference,
      VertexReferenceImp parameterVertexReference) {
    super();
    this.geometricVertexReference = geometricVertexReference;
    this.textureVertexReference = textureVertexReference;
    this.normalVervexReference = normalVertexReference;
    this.parameterVertexReference = parameterVertexReference;
  }

  VertexReferenceGroupImp(VertexReferenceGroupImp vertexReferenceGroup) {
    this(vertexReferenceGroup.getGeoVertexRefObj(), vertexReferenceGroup.getTexVertexRefObj(),
        vertexReferenceGroup.getNormalVertexRefObj(), vertexReferenceGroup.getParamVertexRefObj());
  }

  // TODO What to return on published API when not set? Throw exception? For now, just assume the
  // values are all set. An exception is likely correct.
  // TODO you should probably have some checks to ensure that the vertexReference types are what is
  // expected. Although it is an internal interface. I would just be future proofing the
  // implementation
  // against that dastardly SOB, Future-Nick!

  @Override
  public Integer getGeoVertexRef() {
    return geometricVertexReference.getVertexIndex();
  }

  @Override
  public Integer getTexVertexRef() {
    return textureVertexReference.getVertexIndex();
  }

  @Override
  public Integer getNormalVertexRef() {
    return normalVervexReference.getVertexIndex();
  }

  @Override
  public Integer getParamVertexRef() {
    return parameterVertexReference.getVertexIndex();
  }

  VertexReferenceImp getGeoVertexRefObj() {
    return geometricVertexReference;
  }

  VertexReferenceImp getTexVertexRefObj() {
    return textureVertexReference;
  }

  VertexReferenceImp getNormalVertexRefObj() {
    return normalVervexReference;
  }

  VertexReferenceImp getParamVertexRefObj() {
    return parameterVertexReference;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((geometricVertexReference == null) ? 0 : geometricVertexReference.hashCode());
    result =
        prime * result + ((normalVervexReference == null) ? 0 : normalVervexReference.hashCode());
    result = prime * result
        + ((parameterVertexReference == null) ? 0 : parameterVertexReference.hashCode());
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
    if (parameterVertexReference == null) {
      if (other.parameterVertexReference != null)
        return false;
    } else if (!parameterVertexReference.equals(other.parameterVertexReference))
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
        + normalVervexReference + ", parameterVertexReference=" + parameterVertexReference + "]";
  }
}
