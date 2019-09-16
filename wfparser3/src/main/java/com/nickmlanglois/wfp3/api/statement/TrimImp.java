package com.nickmlanglois.wfp3.api.statement;

import java.util.List;

class TrimImp extends StatementsUsingCurve2DReferenceListImp implements Trim {
  private static final String KEYWORD = "trim";

  TrimImp(List<Curve2DReference> curve2DReferenceList) {
    super(KEYWORD, MINIMUM_CURVE2D_REFERENCES, curve2DReferenceList);
  }

  TrimImp(Trim trim) {
    this(trim.getCurve2DReferenceList());
  }

  @Override
  public int compareTo(Trim o) {
    return compareToCommon(o);
  }
}
