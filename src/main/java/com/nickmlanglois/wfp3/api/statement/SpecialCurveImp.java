package com.nickmlanglois.wfp3.api.statement;

import java.util.List;

class SpecialCurveImp extends StatementsUsingCurve2DReferenceListImp implements SpecialCurve {
  private static final String KEYWORD = "scrv";

  SpecialCurveImp(List<Curve2DReference> curve2DReferenceList) {
    super(KEYWORD, MINIMUM_CURVE2D_REFERENCES, curve2DReferenceList);
  }

  SpecialCurveImp(SpecialCurve scrv) {
    this(scrv.getCurve2DReferenceList());
  }

  @Override
  public int compareTo(SpecialCurve o) {
    return compareToCommon(o);
  }
}
