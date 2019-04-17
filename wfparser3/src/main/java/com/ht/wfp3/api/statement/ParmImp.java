package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ParmImp extends StatementImp implements Parm {
  private static final String KEYWORD = "parm";

  private final Axis axis;
  private final List<BigDecimal> parameterList;

  ParmImp(Axis axis, List<BigDecimal> parameterList) {
    super(KEYWORD);
    this.axis = axis;
    this.parameterList = new ArrayList<BigDecimal>(parameterList);
  }

  ParmImp(Parm parm) {
    this(parm.getAxis(), parm.getParameterValues());
  }

  @Override
  public Axis getAxis() {
    return axis;
  }

  @Override
  public List<BigDecimal> getParameterValues() {
    return Collections.unmodifiableList(parameterList);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((axis == null) ? 0 : axis.hashCode());
    result = prime * result + ((parameterList == null) ? 0 : parameterList.hashCode());
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
    ParmImp other = (ParmImp) obj;
    if (axis != other.axis)
      return false;
    if (parameterList == null) {
      if (other.parameterList != null)
        return false;
    } else if (!parameterList.equals(other.parameterList))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "ParmImp [axis=" + axis + ", parameterList=" + parameterList + ", super.toString()="
        + super.toString() + "]";
  }
}
