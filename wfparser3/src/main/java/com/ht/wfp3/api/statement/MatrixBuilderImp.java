package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class MatrixBuilderImp implements MatrixBuilder {
  private static final BigDecimal DEFAULT_ELEMENT = BigDecimal.ZERO;

  private enum Method {
    NOT_SET, // Not set
    ROW_BY_ROW, // Row-major
    COL_BY_COL, // Column-major
  }

  private Method buildMethod;
  private List<List<BigDecimal>> major;
  private int majorIndex;
  private int longestMinor;

  public MatrixBuilderImp() {
    internalClear();
  }

  private void internalEnd() {
    longestMinor =
        (major.get(majorIndex).size() > longestMinor ? major.get(majorIndex).size() : longestMinor);
    major.add(new ArrayList<>());
    majorIndex++;
  }

  private void internalClear() {
    buildMethod = Method.NOT_SET;
    major = new ArrayList<>();
    majorIndex = 0;
    major.add(new ArrayList<>());
    longestMinor = 0;
  }


  @Override
  public MatrixBuilder buildRowByRow() {
    buildMethod = Method.ROW_BY_ROW;
    return this;
  }

  @Override
  public MatrixBuilder append(BigDecimal element) {
    major.get(majorIndex).add(element);
    return this;
  }

  @Override
  public MatrixBuilder endRow() {
    internalEnd();
    return this;
  }

  @Override
  public Matrix build() {
    MatrixImp matrix = null;
    if (buildMethod.equals(Method.ROW_BY_ROW)) {
      BigDecimal[][] matrixAs2DArray = new BigDecimal[majorIndex][longestMinor];
      for (int i = 0; i < matrixAs2DArray.length; i++) {
        Arrays.fill(matrixAs2DArray[i], 0, matrixAs2DArray[i].length, DEFAULT_ELEMENT);
        for (int j = 0; j < major.get(majorIndex).size(); j++) {
          matrixAs2DArray[i][j] = major.get(i).get(j);
        }
      }
      matrix = new MatrixImp(matrixAs2DArray);
    } else if (buildMethod.equals(Method.COL_BY_COL)) {
      // TODO not implemented.
      matrix = null;
    } else {
      // TODO not implemented.
    }
    return matrix;
  }

  @Override
  public MatrixBuilder clear() {
    internalClear();
    return this;
  }
}
