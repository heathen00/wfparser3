package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.util.List;

class SurfaceImp extends StatementsUsingVertexReferenceGroupsImp implements Surface {
  private static final String KEYWORD = "surf";
  private final BigDecimal startingParameterValueUAxis;
  private final BigDecimal endingParameterValueUAxis;
  private final BigDecimal startingParameterValueVAxis;
  private final BigDecimal endingParameterValueVAxis;

  SurfaceImp(BigDecimal startingParameterValueUAxis, BigDecimal endingParameterValueUAxis,
      BigDecimal startingParameterValueVAxis, BigDecimal endingParameterValueVAxis,
      List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(KEYWORD, vertexReferenceGroupList, MINIMUM_VERTEX_REFERENCE_GROUPS);
    if (null == startingParameterValueUAxis) {
      throw new NullPointerException(
          "startingParameterValueUAxis constructor parameter cannot be null");
    }
    if (null == endingParameterValueUAxis) {
      throw new NullPointerException(
          "endingParameterValueUAxis constructor parameter cannot be null");
    }
    if (null == startingParameterValueVAxis) {
      throw new NullPointerException(
          "startingParameterValueVAxis constructor parameter cannot be null");
    }
    if (null == endingParameterValueVAxis) {
      throw new NullPointerException(
          "endingParameterValueVAxis constructor parameter cannot be null");
    }
    this.startingParameterValueUAxis = startingParameterValueUAxis;
    this.endingParameterValueUAxis = endingParameterValueUAxis;
    this.startingParameterValueVAxis = startingParameterValueVAxis;
    this.endingParameterValueVAxis = endingParameterValueVAxis;
  }

  SurfaceImp(Surface surf) {
    this(surf.getStartingParameterValueUAxis(), surf.getEndingParameterValueUAxis(),
        surf.getStartingParameterValueVAxis(), surf.getEndingParameterValueVAxis(),
        surf.getVertexReferenceGroupList());
  }

  @Override
  public BigDecimal getStartingParameterValueUAxis() {
    return startingParameterValueUAxis;
  }

  @Override
  public BigDecimal getEndingParameterValueUAxis() {
    return endingParameterValueUAxis;
  }

  @Override
  public BigDecimal getStartingParameterValueVAxis() {
    return startingParameterValueVAxis;
  }

  @Override
  public BigDecimal getEndingParameterValueVAxis() {
    return endingParameterValueVAxis;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result
        + ((endingParameterValueUAxis == null) ? 0 : endingParameterValueUAxis.hashCode());
    result = prime * result
        + ((endingParameterValueVAxis == null) ? 0 : endingParameterValueVAxis.hashCode());
    result = prime * result
        + ((startingParameterValueUAxis == null) ? 0 : startingParameterValueUAxis.hashCode());
    result = prime * result
        + ((startingParameterValueVAxis == null) ? 0 : startingParameterValueVAxis.hashCode());
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
    SurfaceImp other = (SurfaceImp) obj;
    if (endingParameterValueUAxis == null) {
      if (other.endingParameterValueUAxis != null)
        return false;
    } else if (!endingParameterValueUAxis.equals(other.endingParameterValueUAxis))
      return false;
    if (endingParameterValueVAxis == null) {
      if (other.endingParameterValueVAxis != null)
        return false;
    } else if (!endingParameterValueVAxis.equals(other.endingParameterValueVAxis))
      return false;
    if (startingParameterValueUAxis == null) {
      if (other.startingParameterValueUAxis != null)
        return false;
    } else if (!startingParameterValueUAxis.equals(other.startingParameterValueUAxis))
      return false;
    if (startingParameterValueVAxis == null) {
      if (other.startingParameterValueVAxis != null)
        return false;
    } else if (!startingParameterValueVAxis.equals(other.startingParameterValueVAxis))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SurfaceImp [startingParameterValueUAxis=" + startingParameterValueUAxis
        + ", endingParameterValueUAxis=" + endingParameterValueUAxis
        + ", startingParameterValueVAxis=" + startingParameterValueVAxis
        + ", endingParameterValueVAxis=" + endingParameterValueVAxis + ", super.toString()="
        + super.toString() + "]";
  }
}

