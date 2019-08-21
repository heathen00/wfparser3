package com.ht.wfp3.message.OLD;

final class ConstraintsImp implements Constraints {
  private int uidKeyMaxLength;

  ConstraintsImp(int uidKeyMaxLength) {
    this.uidKeyMaxLength = uidKeyMaxLength;
  }

  @Override
  public int getUidKeyMaxLength() {
    return uidKeyMaxLength;
  }
}
