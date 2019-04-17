package com.ht.wfp3.api.statement;

import java.util.List;

class HoleImp extends StatementsUsingCurve2DReferenceListImp implements Hole {
  private static final String KEYWORD = "hole";
  
  HoleImp(List<Curve2DReference> curve2DReferenceList) {
    super(KEYWORD, curve2DReferenceList);
  }
  
  HoleImp(Hole hole) {
    this(hole.getCurve2DReferenceList());
  }
}
