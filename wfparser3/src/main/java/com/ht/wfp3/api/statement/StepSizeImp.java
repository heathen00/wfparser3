package com.ht.wfp3.api.statement;

class StepSizeImp extends StatementImp implements StepSize {
  private static final String KEYWORD = "step";
  static final Integer STEP_SIZE_IN_V_AXIS_NOT_USED_VALUE = Integer.MIN_VALUE;

  private final Integer stepSizeInUAxis;
  private final Integer stepSizeInVAxis;
  private final boolean isStepSizeInVAxisUsed;

  private StepSizeImp(Integer stepSizeInUAxis, Integer stepSizeInVAxis,
      boolean isStepSizeInVAxisUsed) {
    super(KEYWORD);
    this.isStepSizeInVAxisUsed = isStepSizeInVAxisUsed;
    if (null == stepSizeInUAxis) {
      throw new NullPointerException("stepSizeInUAxis cannot be null");
    }
    if (null == stepSizeInVAxis) {
      throw new NullPointerException("stepSizeInVAxis cannot be null");
    }
    if (MINIMUM_STEP_SIZE_IN_U_AXIS.compareTo(stepSizeInUAxis) > 0) {
      throw new IllegalArgumentException(
          "stepSizeInUAxis must be greater than " + MINIMUM_STEP_SIZE_IN_U_AXIS);
    }
    if (this.isStepSizeInVAxisUsed && MINIMUM_STEP_SIZE_IN_V_AXIS.compareTo(stepSizeInVAxis) > 0) {
      throw new IllegalArgumentException(
          "stepSizeInVAxis must be greater than " + MINIMUM_STEP_SIZE_IN_V_AXIS);
    }
    this.stepSizeInUAxis = stepSizeInUAxis;
    this.stepSizeInVAxis = stepSizeInVAxis;
  }

  StepSizeImp(Integer stepSizeInUAxis, Integer stepSizeInVAxis) {
    this(stepSizeInUAxis, stepSizeInVAxis, true);
  }

  StepSizeImp(Integer stepSizeInUAxis) {
    this(stepSizeInUAxis, STEP_SIZE_IN_V_AXIS_NOT_USED_VALUE, false);
  }

  @Override
  public Integer getStepSizeInUAxis() {
    return stepSizeInUAxis;
  }

  @Override
  public Integer getStepSizeInVAxis() {
    if (!isStepSizeInVAxisUsed) {
      throw new UnsupportedOperationException("cannot access stepSizeInVAxis when it is not set");
    }
    return stepSizeInVAxis;
  }

  @Override
  public boolean isStepSizeInVAxisSet() {
    return isStepSizeInVAxisUsed;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((stepSizeInUAxis == null) ? 0 : stepSizeInUAxis.hashCode());
    result = prime * result + ((stepSizeInVAxis == null) ? 0 : stepSizeInVAxis.hashCode());
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
    StepSizeImp other = (StepSizeImp) obj;
    if (stepSizeInUAxis == null) {
      if (other.stepSizeInUAxis != null)
        return false;
    } else if (!stepSizeInUAxis.equals(other.stepSizeInUAxis))
      return false;
    if (stepSizeInVAxis == null) {
      if (other.stepSizeInVAxis != null)
        return false;
    } else if (!stepSizeInVAxis.equals(other.stepSizeInVAxis))
      return false;
    return true;
  }

  @Override
  public int compareTo(StepSize o) {
    int compareTo = stepSizeInUAxis.compareTo(o.getStepSizeInUAxis());
    if (0 == compareTo) {
      compareTo = Boolean.compare(isStepSizeInVAxisUsed, o.isStepSizeInVAxisSet());
      if (0 == compareTo && isStepSizeInVAxisUsed) {
        compareTo = stepSizeInVAxis.compareTo(o.getStepSizeInVAxis());
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "StepSizeImp [stepSizeInUAxis=" + stepSizeInUAxis + ", stepSizeInVAxis="
        + stepSizeInVAxis + ", super.toString()=" + super.toString() + "]";
  }
}
