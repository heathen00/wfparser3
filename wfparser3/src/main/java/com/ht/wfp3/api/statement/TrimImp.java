package com.ht.wfp3.api.statement;

import java.util.List;

class TrimImp extends StatementsUsingCurve2DReferenceListImp implements Trim {
  private static final String KEYWORD = "trim";

  TrimImp(List<Curve2DReference> curve2DReferenceList) {
    super(KEYWORD, curve2DReferenceList);
  }

  TrimImp(Trim trim) {
    this(trim.getCurve2DReferenceList());
  }
}
