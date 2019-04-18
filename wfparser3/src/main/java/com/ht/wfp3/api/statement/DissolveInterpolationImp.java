package com.ht.wfp3.api.statement;

class DissolveInterpolationImp extends StatementImp implements DissolveInterpolation {
  private static final String KEYWORD = "d_interp";

  private final boolean isEnabled;

  DissolveInterpolationImp(boolean isEnabled) {
    super(KEYWORD);
    this.isEnabled = isEnabled;
  }

  DissolveInterpolationImp(DissolveInterpolation d_interp) {
    this(d_interp.isEnabled());
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (isEnabled ? 1231 : 1237);
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
    DissolveInterpolationImp other = (DissolveInterpolationImp) obj;
    if (isEnabled != other.isEnabled)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "DissolveInterpolationImp [isEnabled=" + isEnabled + ", super.toString()="
        + super.toString() + "]";
  }
}
