package com.ht.wfp3.api.statement;

class ColorInterpolationImp extends StatementImp implements ColorInterpolation {
  private static final String KEYWORD = "c_interp";

  private final boolean isEnabled;

  ColorInterpolationImp(boolean isEnabled) {
    super(KEYWORD);
    this.isEnabled = isEnabled;
  }

  ColorInterpolationImp(ColorInterpolation c_interp) {
    this(c_interp.isEnabled());
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
    ColorInterpolationImp other = (ColorInterpolationImp) obj;
    if (isEnabled != other.isEnabled)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "ColorInterpolationImp [isEnabled=" + isEnabled + ", super.toString()="
        + super.toString() + "]";
  }
}
