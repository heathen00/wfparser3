package com.nickmlanglois.wfp3.api.statement;

class EndImp extends StatementImp implements End {
  private static final String KEYWORD = "end";
  private static final EndImp SINGLETON = new EndImp();

  static EndImp createEndImp() {
    return SINGLETON;
  }

  private EndImp() {
    super(KEYWORD);
  }

  @Override
  public int compareTo(End o) {
    return 0;
  }
}
