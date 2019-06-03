package com.ht.wfp3.message;

final class ConstraintsImp implements Constraints {
  private int priorityUidKeyMaxLength;

  ConstraintsImp(int priorityUidKeyMaxLength) {
    this.priorityUidKeyMaxLength = priorityUidKeyMaxLength;
  }

  @Override
  public int getPriorityUidKeyMaxLength() {
    return priorityUidKeyMaxLength;
  }
}
