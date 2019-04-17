package com.ht.wfp3.api.statement;

class BasisMatrixImp extends StatementImp implements BasisMatrix {
  private static final String KEYWORD = "bmat";
  
  private final Axis axis;
  private final Matrix matrix;
  
  public BasisMatrixImp(Axis axis, Matrix matrix) {
    super(KEYWORD);
    this.axis = axis;
    this.matrix = matrix;
  }

  BasisMatrixImp(BasisMatrix bmat) {
    this(bmat.getBasisMatrixAxis(), bmat.getMatrix());
  }

  @Override
  public Axis getBasisMatrixAxis() {
    return axis;
  }

  @Override
  public Matrix getMatrix() {
    // TODO Make copy
    return matrix;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((axis == null) ? 0 : axis.hashCode());
    result = prime * result + ((matrix == null) ? 0 : matrix.hashCode());
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
    BasisMatrixImp other = (BasisMatrixImp) obj;
    if (axis != other.axis)
      return false;
    if (matrix == null) {
      if (other.matrix != null)
        return false;
    } else if (!matrix.equals(other.matrix))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "BasisMatrixImp [axis=" + axis + ", matrix=" + matrix + ", super.toString()="
        + super.toString() + "]";
  }
}
