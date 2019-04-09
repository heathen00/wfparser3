package com.ht.wfp3.api.statement;

class CurveOrSurfaceTypeImp extends StatementImp implements CurveOrSurfaceType {
  private static final String KEYWORD = "v";
  private static final boolean CAN_COMMENT = true;

  private final String rational;
  private final Key key;
  
  CurveOrSurfaceTypeImp(String rational, Key key) {
    super(KEYWORD, CAN_COMMENT);
    this.rational = rational;
    this.key = key;
  }
  
  CurveOrSurfaceTypeImp(CurveOrSurfaceType curveOrSurfaceType) {
    this(curveOrSurfaceType.getRational(), curveOrSurfaceType.getTypeKey());
  }

  @Override
  public String getRational() {
    return rational;
  }

  @Override
  public Key getTypeKey() {
    return key;
  }
}
