package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

class Curve2DReferenceImp implements Curve2DReference {
  private final BigDecimal startingParameterValue;
  private final BigDecimal endingParameterValue;
  private final Integer curve2DIndex;

  Curve2DReferenceImp(BigDecimal startingParameterValue, BigDecimal endingParameterValue,
      Integer curve2DIndex) {
    if (null == startingParameterValue) {
      throw new NullPointerException("startingParameterValue constructor parameter cannot be null");
    }
    if (null == endingParameterValue) {
      throw new NullPointerException("endingParameterValue constructor parameter cannot be null");
    }
    if (null == curve2DIndex) {
      throw new NullPointerException("curve2DIndex constructor parameter cannot be null");
    }
    if (0 == curve2DIndex.intValue()) {
      throw new IllegalArgumentException("curve2DIndex constructor parameter cannot be zero");
    }
    this.startingParameterValue = startingParameterValue;
    this.endingParameterValue = endingParameterValue;
    this.curve2DIndex = curve2DIndex;
  }

  Curve2DReferenceImp(Curve2DReference curve2DReference) {
    this(curve2DReference.getStartingParameterValue(), curve2DReference.getEndingParameterValue(),
        curve2DReference.getCurve2DIndex());
  }

  @Override
  public BigDecimal getStartingParameterValue() {
    return startingParameterValue;
  }

  @Override
  public BigDecimal getEndingParameterValue() {
    return endingParameterValue;
  }

  @Override
  public Integer getCurve2DIndex() {
    return curve2DIndex;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((curve2DIndex == null) ? 0 : curve2DIndex.hashCode());
    result =
        prime * result + ((endingParameterValue == null) ? 0 : endingParameterValue.hashCode());
    result =
        prime * result + ((startingParameterValue == null) ? 0 : startingParameterValue.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Curve2DReferenceImp other = (Curve2DReferenceImp) obj;
    if (curve2DIndex == null) {
      if (other.curve2DIndex != null)
        return false;
    } else if (!curve2DIndex.equals(other.curve2DIndex))
      return false;
    if (endingParameterValue == null) {
      if (other.endingParameterValue != null)
        return false;
    } else if (!endingParameterValue.equals(other.endingParameterValue))
      return false;
    if (startingParameterValue == null) {
      if (other.startingParameterValue != null)
        return false;
    } else if (!startingParameterValue.equals(other.startingParameterValue))
      return false;
    return true;
  }

  @Override
  public int compareTo(Curve2DReference o) {
    int compareTo = startingParameterValue.compareTo(o.getStartingParameterValue());
    if (0 == compareTo) {
      compareTo = endingParameterValue.compareTo(o.getEndingParameterValue());
      if (0 == compareTo) {
        compareTo = Integer.compare(curve2DIndex, o.getCurve2DIndex());
      }
    }
    return compareTo;
  }  

  @Override
  public String toString() {
    return "Curve2DReferenceImp [startingParameterValue=" + startingParameterValue
        + ", endingParameterValue=" + endingParameterValue + ", curve2DIndex=" + curve2DIndex + "]";
  }
}
