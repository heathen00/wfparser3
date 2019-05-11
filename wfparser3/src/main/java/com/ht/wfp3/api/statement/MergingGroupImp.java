package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class MergingGroupImp extends StatementImp implements MergingGroup {
  private static final String KEYWORD = "mg";

  private final boolean isEnabled;
  private final Integer mergingGroupNumber;
  private final BigDecimal mergingGroupResolution;

  MergingGroupImp(Integer mergingGroupNumber, BigDecimal mergingGroupResolution) {
    super(KEYWORD);
    if (null == mergingGroupNumber) {
      throw new NullPointerException("mergingGroupNumber constructor parameter cannot be null");
    }
    if (null == mergingGroupResolution) {
      throw new NullPointerException("mergingGroupResolution constructor parameter cannot be null");
    }
    if (OFF.compareTo(mergingGroupNumber) > 0) {
      throw new IllegalArgumentException(
          "mergingGroupNumber constructor parameter must be greater or equal to " + OFF);
    }
    if (MINIMUM_RESOLUTION.compareTo(mergingGroupResolution) >= 0) {
      throw new IllegalArgumentException(
          "mergingGroupResolution constructor parameter mube be greater than "
              + MINIMUM_RESOLUTION);
    }
    this.isEnabled = (mergingGroupNumber == OFF ? false : true);
    this.mergingGroupNumber = mergingGroupNumber;
    this.mergingGroupResolution = mergingGroupResolution;
  }

  MergingGroupImp(MergingGroup mg) {
    this(mg.getMergingGroupNumber(), mg.getMergingResolution());
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }

  @Override
  public Integer getMergingGroupNumber() {
    return this.mergingGroupNumber;
  }

  @Override
  public BigDecimal getMergingResolution() {
    return this.mergingGroupResolution;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (isEnabled ? 1231 : 1237);
    result = prime * result + ((mergingGroupNumber == null) ? 0 : mergingGroupNumber.hashCode());
    result =
        prime * result + ((mergingGroupResolution == null) ? 0 : mergingGroupResolution.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    MergingGroupImp other = (MergingGroupImp) obj;
    if (isEnabled != other.isEnabled)
      return false;
    if (mergingGroupNumber == null) {
      if (other.mergingGroupNumber != null)
        return false;
    } else if (!mergingGroupNumber.equals(other.mergingGroupNumber))
      return false;
    if (mergingGroupResolution == null) {
      if (other.mergingGroupResolution != null)
        return false;
    } else if (!mergingGroupResolution.equals(other.mergingGroupResolution))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MergingGroupImp [isEnabled=" + isEnabled + ", mergingGroupNumber=" + mergingGroupNumber
        + ", mergingGroupResolution=" + mergingGroupResolution + ", super.toString()="
        + super.toString() + "]";
  }
}
