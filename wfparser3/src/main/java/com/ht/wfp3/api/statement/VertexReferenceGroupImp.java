package com.ht.wfp3.api.statement;

class VertexReferenceGroupImp implements VertexReferenceGroup {
  private final GeoVertexReference geometricVertexReference;
  private final TexVertexReference textureVertexReference;
  private final NormalVertexReference normalVervexReference;

  VertexReferenceGroupImp(GeoVertexReference geometricVertexReference,
      TexVertexReference textureVertexReference, NormalVertexReference normalVertexReference) {
    this.geometricVertexReference = geometricVertexReference;
    this.textureVertexReference = textureVertexReference;
    this.normalVervexReference = normalVertexReference;
  }

  @Override
  public GeoVertexReference getGeoVertexRef() {
    return geometricVertexReference;
  }

  @Override
  public TexVertexReference getTexVertexRef() {
    if (!textureVertexReference.isSet()) {
      throw new UnsupportedOperationException(
          "cannot access texture vertex reference when it is not set");
    }
    return textureVertexReference;
  }

  @Override
  public NormalVertexReference getNormalVertexRef() {
    if (!normalVervexReference.isSet()) {
      throw new UnsupportedOperationException(
          "cannot access normal vertex reference when it is not set");
    }
    return normalVervexReference;
  }

  @Override
  public boolean isTexVertexRefSet() {
    return textureVertexReference.isSet();
  }

  @Override
  public boolean isNormalVertexRefSet() {
    return normalVervexReference.isSet();
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
  public int compareTo(VertexReferenceGroup o) {
    int compareTo = geometricVertexReference.compareTo(o.getGeoVertexRef());
    if (0 == compareTo) {
      compareTo = Boolean.compare(isTexVertexRefSet(), o.isTexVertexRefSet());
      if (0 == compareTo && isTexVertexRefSet()) {
        compareTo = textureVertexReference.compareTo(o.getTexVertexRef());
        if (0 == compareTo) {
          compareTo = Boolean.compare(isNormalVertexRefSet(), o.isNormalVertexRefSet());
          if (0 == compareTo && isNormalVertexRefSet()) {
            compareTo = normalVervexReference.compareTo(normalVervexReference);
          }
        }
      } else if (0 == compareTo && !isTexVertexRefSet()) {
        compareTo = Boolean.compare(isNormalVertexRefSet(), o.isNormalVertexRefSet());
        if (0 == compareTo && isNormalVertexRefSet()) {
          compareTo = normalVervexReference.compareTo(normalVervexReference);
        }
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "VertexReferenceGroupImp [geometricVertexReference=" + geometricVertexReference
        + ", textureVertexReference=" + textureVertexReference + ", normalVervexReference="
        + normalVervexReference + "]";
  }
}
