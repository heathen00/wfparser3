package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.util.Arrays;

class MatrixImp implements Matrix {
  private final BigDecimal[][] matrix;

  MatrixImp(BigDecimal[][] matrix) {
    this.matrix = matrix;
  }

  @Override
  public BigDecimal getElementAt(int row, int column) {
    return matrix[row][column];
  }

  @Override
  public int getNumRows() {
    return matrix.length;
  }

  @Override
  public int getNumColumns() {
    return matrix[0].length;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.deepHashCode(matrix);
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
    MatrixImp other = (MatrixImp) obj;
    if (!Arrays.deepEquals(matrix, other.matrix))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MatrixImp [matrix=" + Arrays.toString(matrix) + "]";
  }
}
