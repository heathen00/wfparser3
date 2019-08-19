package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CurveImp extends StatementImp implements Curve {
  private static final String KEYWORD = "curv";

  private final BigDecimal startingParameterValue;
  private final BigDecimal endingParameterValue;
  private final List<GeoVertexReference> controlPointVertexReferenceList;

  CurveImp(BigDecimal startingParameterValue, BigDecimal endingParameterValue,
      List<GeoVertexReference> controlPointVertexReferenceList) {
    super(KEYWORD);
    if (null == startingParameterValue) {
      throw new NullPointerException("startingParameterValue cannot be null");
    }
    if (null == endingParameterValue) {
      throw new NullPointerException("endingParameterValue cannot be null");
    }
    if (null == controlPointVertexReferenceList) {
      throw new NullPointerException("controlPointVertexReferenceList cannot be null");
    }
    if (controlPointVertexReferenceList.contains(null)) {
      throw new IllegalArgumentException(
          "controlPointVertexReferenceList cannot contain null members");
    }
    if (controlPointVertexReferenceList.size() < MINIMUM_CONTROL_POINTS) {
      throw new IllegalArgumentException("controlPointVertexReferenceList requires at least "
          + MINIMUM_CONTROL_POINTS + " control points.");
    }
    this.startingParameterValue = startingParameterValue;
    this.endingParameterValue = endingParameterValue;
    this.controlPointVertexReferenceList = new ArrayList<>(controlPointVertexReferenceList);
  }

  CurveImp(Curve curv) {
    this(curv.getStartingParameterValue(), curv.getEndingParameterValue(),
        curv.getControlPointVertexReferenceList());
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
  public List<GeoVertexReference> getControlPointVertexReferenceList() {
    return Collections.unmodifiableList(controlPointVertexReferenceList);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((controlPointVertexReferenceList == null) ? 0
        : controlPointVertexReferenceList.hashCode());
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
    if (controlPointVertexReferenceList == null) {
      if (other.controlPointVertexReferenceList != null)
        return false;
    } else if (!controlPointVertexReferenceList.equals(other.controlPointVertexReferenceList))
      return false;
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
  public int compareTo(Curve o) {
    int compareTo = startingParameterValue.compareTo(o.getStartingParameterValue());
    if (0 == compareTo) {
      compareTo = endingParameterValue.compareTo(o.getEndingParameterValue());
      if (0 == compareTo) {
        ListOfComparableComparator<GeoVertexReference> listComparator =
            new ListOfComparableComparator<>();
        compareTo = listComparator.compare(controlPointVertexReferenceList,
            o.getControlPointVertexReferenceList());
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "CurveImp [startingParameterValue=" + startingParameterValue + ", endingParameterValue="
        + endingParameterValue + ", controlPointVertexReferenceList="
        + controlPointVertexReferenceList + ", super.toString()=" + super.toString() + "]";
  }
}
