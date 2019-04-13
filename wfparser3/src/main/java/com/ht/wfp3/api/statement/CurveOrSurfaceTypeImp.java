package com.ht.wfp3.api.statement;

class CurveOrSurfaceTypeImp extends StatementImp implements CurveOrSurfaceType {
  private static final String KEYWORD = "cstype";
  private static final boolean CAN_COMMENT = true;

  private final boolean isRational;
  private final Key key;

  CurveOrSurfaceTypeImp(boolean isRational, Key key) {
    super(KEYWORD, CAN_COMMENT);
    this.isRational = isRational;
    this.key = key;
  }

  CurveOrSurfaceTypeImp(CurveOrSurfaceType curveOrSurfaceType) {
    this(curveOrSurfaceType.isRational(), curveOrSurfaceType.getTypeKey());
  }

  @Override
  public boolean isRational() {
    return isRational;
  }

  @Override
  public Key getTypeKey() {
    return key;
  }
}
