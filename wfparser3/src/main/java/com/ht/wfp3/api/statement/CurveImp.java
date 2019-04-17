package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.util.List;

class CurveImp extends StatementsUsingVertexReferenceGroupsImp implements Curve {
  private static final String KEYWORD = "curv";

  private final BigDecimal startingParameterValue;
  private final BigDecimal endingParameterValue;

  CurveImp(BigDecimal startingParameterValue, BigDecimal endingParameterValue,
      List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(KEYWORD, vertexReferenceGroupList);
    this.startingParameterValue = startingParameterValue;
    this.endingParameterValue = endingParameterValue;
  }

  CurveImp(Curve curv) {
    this(curv.getStartingParameterValue(), curv.getEndingParameterValue(),
        curv.getVertexReferenceGroupList());
  }

  @Override
  public BigDecimal getStartingParameterValue() {
    return startingParameterValue;
  }

  @Override
  public BigDecimal getEndingParameterValue() {
    return endingParameterValue;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + ((endingParameterValue == null) ? 0 : endingParameterValue.hashCode());
    result =
        prime * result + ((startingParameterValue == null) ? 0 : startingParameterValue.hashCode());
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
    CurveImp other = (CurveImp) obj;
    if (endingParameterValue == null) {
      if (other.endingParameterValue != null)
        return false;
    } else if (!endingParameterValue.equals(other.endingParameterValue))
      return false;
    if (startingParameterValue == null) {
      if (other.startingParameterValue != null)
        return false;
    } else if (!startingParameterValue.equals(other.startingParameterValue))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CurveImp [startingParameterValue=" + startingParameterValue + ", endingParameterValue="
        + endingParameterValue + ", super.toString()=" + super.toString() + "]";
  }
}
