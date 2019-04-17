package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class StatementsUsingCurve2DReferenceListImp extends StatementImp
    implements UsesCurv2DReferenceList {
  private final List<Curve2DReference> curve2DReferenceList;

  StatementsUsingCurve2DReferenceListImp(String keyword,
      List<Curve2DReference> curve2DReferenceList) {
    super(keyword);
    this.curve2DReferenceList = new ArrayList<Curve2DReference>(curve2DReferenceList);
  }

  public StatementsUsingCurve2DReferenceListImp(
      StatementsUsingCurve2DReferenceListImp statementsUsingCurve2DReferenceList) {
    this(statementsUsingCurve2DReferenceList.getKeyword(),
        statementsUsingCurve2DReferenceList.getCurve2DReferenceList());
  }

  @Override
  public List<Curve2DReference> getCurve2DReferenceList() {
    return Collections.unmodifiableList(curve2DReferenceList);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + ((curve2DReferenceList == null) ? 0 : curve2DReferenceList.hashCode());
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
    return true;
  }

  @Override
  public String toString() {
    return "StatementsUsingCurve2DReferenceList [curve2DReferenceList=" + curve2DReferenceList
        + ", super.toString()=" + super.toString() + "]";
  }
}
