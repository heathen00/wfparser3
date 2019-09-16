package com.nickmlanglois.wfp3.api.statement;

class SmoothingGroupImp extends StatementImp implements SmoothingGroup {
  private static final String KEYWORD = "s";
  static final Integer OFF = Integer.valueOf(0);

  private final boolean isEnabled;
  private final Integer smoothingGroupNumber;

  SmoothingGroupImp(Integer smoothingGroupNumber) {
    super(KEYWORD);
    if (null == smoothingGroupNumber) {
      throw new NullPointerException("smoothingGroupNumber cannot be null");
    }
    if (OFF.compareTo(smoothingGroupNumber) > 0) {
      throw new IllegalArgumentException("smoothingGroupNumber must be greater or equal to " + OFF);
    }
    this.isEnabled = (smoothingGroupNumber == OFF ? false : true);
    this.smoothingGroupNumber = smoothingGroupNumber;
  }

  SmoothingGroupImp(SmoothingGroup s) {
    this(s.getSmoothingGroupNumber());
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }

  @Override
  public Integer getSmoothingGroupNumber() {
    return smoothingGroupNumber;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (isEnabled ? 1231 : 1237);
    result =
        prime * result + ((smoothingGroupNumber == null) ? 0 : smoothingGroupNumber.hashCode());
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
    SmoothingGroupImp other = (SmoothingGroupImp) obj;
    if (isEnabled != other.isEnabled)
      return false;
    if (smoothingGroupNumber == null) {
      if (other.smoothingGroupNumber != null)
        return false;
    } else if (!smoothingGroupNumber.equals(other.smoothingGroupNumber))
      return false;
    return true;
  }

  @Override
  public int compareTo(SmoothingGroup o) {
    return smoothingGroupNumber.compareTo(o.getSmoothingGroupNumber());
  }

  @Override
  public String toString() {
    return "SmoothingGroupImp [isEnabled=" + isEnabled + ", smoothingGroupNumber="
        + smoothingGroupNumber + ", super.toString()=" + super.toString() + "]";
  }
}
