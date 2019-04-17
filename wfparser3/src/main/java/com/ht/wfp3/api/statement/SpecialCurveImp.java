package com.ht.wfp3.api.statement;

import java.util.List;

class SpecialCurveImp extends StatementsUsingCurve2DReferenceListImp implements SpecialCurve {
  private static final String KEYWORD = "scrv";
  
  SpecialCurveImp(List<Curve2DReference> curve2DReferenceList) {
    super(KEYWORD, curve2DReferenceList);
  }
  
  public SpecialCurveImp(SpecialCurve scrv) {
    this(scrv.getCurve2DReferenceList());
  }
}
