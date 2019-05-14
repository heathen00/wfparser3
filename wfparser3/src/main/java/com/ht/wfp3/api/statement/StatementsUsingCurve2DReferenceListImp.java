package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class StatementsUsingCurve2DReferenceListImp extends StatementImp
    implements UsesCurv2DReferenceList {
  private final List<Curve2DReference> curve2DReferenceList;
  private final Integer minimumCurve2DReferences;

  StatementsUsingCurve2DReferenceListImp(String keyword, Integer minimumCurve2DReferences,
      List<Curve2DReference> curve2DReferenceList) {
    super(keyword);
    this.minimumCurve2DReferences = minimumCurve2DReferences;
    if (null == curve2DReferenceList) {
      throw new NullPointerException("curve2DReferenceList constructor parameter cannot be null");
    }
    if (curve2DReferenceList.contains(null)) {
      throw new IllegalArgumentException(
          "curve2DReferenceList constructor parameter cannot contain null members");
    }
    if (minimumCurve2DReferences.compareTo(curve2DReferenceList.size()) > 0) {
      throw new IllegalArgumentException(
          "curve2DReferenceList constructor parameter must contain at least one curve2DReference");
    }
    this.curve2DReferenceList = new ArrayList<Curve2DReference>(curve2DReferenceList);
  }

  StatementsUsingCurve2DReferenceListImp(
      StatementsUsingCurve2DReferenceListImp statementsUsingCurve2DReferenceList) {
    this(statementsUsingCurve2DReferenceList.getKeyword(),
        statementsUsingCurve2DReferenceList.getMinimumNumberOfCurve2DReferences(),
        statementsUsingCurve2DReferenceList.getCurve2DReferenceList());
  }

  @Override
  public List<Curve2DReference> getCurve2DReferenceList() {
    return Collections.unmodifiableList(curve2DReferenceList);
  }

  @Override
  public Integer getMinimumNumberOfCurve2DReferences() {
    return minimumCurve2DReferences;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + ((curve2DReferenceList == null) ? 0 : curve2DReferenceList.hashCode());
    result = prime * result
        + ((minimumCurve2DReferences == null) ? 0 : minimumCurve2DReferences.hashCode());
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
    StatementsUsingCurve2DReferenceListImp other = (StatementsUsingCurve2DReferenceListImp) obj;
    if (curve2DReferenceList == null) {
      if (other.curve2DReferenceList != null)
        return false;
    } else if (!curve2DReferenceList.equals(other.curve2DReferenceList))
      return false;
    if (minimumCurve2DReferences == null) {
      if (other.minimumCurve2DReferences != null)
        return false;
    } else if (!minimumCurve2DReferences.equals(other.minimumCurve2DReferences))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "StatementsUsingCurve2DReferenceListImp [curve2DReferenceList=" + curve2DReferenceList
        + ", minimumCurve2DReferences=" + minimumCurve2DReferences + ", super.toString()="
        + super.toString() + "]";
  }
}
