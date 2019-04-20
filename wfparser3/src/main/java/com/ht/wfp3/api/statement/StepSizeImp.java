package com.ht.wfp3.api.statement;

class StepSizeImp extends StatementImp implements StepSize {
  private static final String KEYWORD = "step";
  private final Integer stepSizeInUAxis;
  private final Integer stepSizeInVAxis;

  StepSizeImp(Integer stepSizeInUAxis, Integer stepSizeInVAxis) {
    super(KEYWORD);
    this.stepSizeInUAxis = stepSizeInUAxis;
    this.stepSizeInVAxis = stepSizeInVAxis;
  }
  
  StepSizeImp(StepSize step) {
    this(step.getStepSizeInUAxis(), step.getStepSizeInVAxis());
  }

  @Override
  public Integer getStepSizeInUAxis() {
    return stepSizeInUAxis;
  }

  @Override
  public Integer getStepSizeInVAxis() {
    return stepSizeInVAxis;
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
  public String toString() {
    return "StepSizeImp [stepSizeInUAxis=" + stepSizeInUAxis + ", stepSizeInVAxis="
        + stepSizeInVAxis + ", super.toString()=" + super.toString() + "]";
  }
}
