package com.ht.wfp3.api.statement;

class BevelImp extends StatementImp implements Bevel {
  private static final String KEYWORD = "bevel";

  private final boolean isEnabled;

  BevelImp(boolean isEnabled) {
    super(KEYWORD);
    this.isEnabled = isEnabled;
  }

  BevelImp(Bevel bevel) {
    this(bevel.isEnabled());
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
    BevelImp other = (BevelImp) obj;
    if (isEnabled != other.isEnabled)
      return false;
    return true;
  }

  @Override
  public int compareTo(Bevel o) {
    return Boolean.compare(isEnabled, o.isEnabled());
  }

  @Override
  public String toString() {
    return "BevelImp [isEnabled=" + isEnabled + ", super.toString()=" + super.toString() + "]";
  }
}
