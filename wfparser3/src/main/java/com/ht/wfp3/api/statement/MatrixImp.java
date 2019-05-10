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
    if (row >= getNumRows() || column >= getNumColumns()) {
      throw new IllegalArgumentException("cannot access element (" + row + ", " + column
          + ") in matrix of size (" + getNumRows() + ", " + getNumColumns() + ")");
    }
    return matrix[row][column];
  }

  @Override
  public int getNumRows() {
    return matrix.length;
  }

  @Override
  public int getNumColumns() {
    return (matrix.length == 0 ? 0 : matrix[0].length);
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
  public int compareTo(Matrix o) {
    int compareTo = Integer.compare(getNumRows(), o.getNumRows());
    if (0 == compareTo) {
      compareTo = Integer.compare(getNumColumns(), o.getNumColumns());
      if (0 == compareTo) {
        for (int row = 0; row < matrix.length; row++) {
          for (int column = 0; column < matrix[row].length; column++) {
            compareTo = matrix[row][column].compareTo(o.getElementAt(row, column));
            if (0 != compareTo) {
              break;
            }
          }
        }
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "MatrixImp [matrix=" + Arrays.deepToString(matrix) + "]";
  }
}
