package com.nickmlanglois.wfp3.api.statement;

import java.util.List;

class HoleImp extends StatementsUsingCurve2DReferenceListImp implements Hole {
  private static final String KEYWORD = "hole";

  HoleImp(List<Curve2DReference> curve2DReferenceList) {
    super(KEYWORD, MINIMUM_CURVE2D_REFERENCES, curve2DReferenceList);
  }

  HoleImp(Hole hole) {
    this(hole.getCurve2DReferenceList());
  }

  @Override
  public int compareTo(Hole o) {
    return compareToCommon(o);
  }
}
