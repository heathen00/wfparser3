package com.nickmlanglois.wfp3.api.statement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

final class MatrixBuilderImp implements MatrixBuilder {
  private enum Method {
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
    buildMethod = Method.ROW_BY_ROW;
    major = new ArrayList<>();
    majorIndex = 0;
    major.add(new ArrayList<>());
    longestMinor = 0;
  }

  private void setConsistentMinorListLengths() {
    for (int i = 0; i < majorIndex; i++) {
      while (major.get(i).size() < longestMinor) {
        major.get(i).add(Matrix.DEFAULT_ELEMENT);
      }
    }
  }

  @Override
  public MatrixBuilder rowByRow() {
    buildMethod = Method.ROW_BY_ROW;
    return this;
  }

  @Override
  public MatrixBuilder colByCol() {
    buildMethod = Method.COL_BY_COL;
    return this;
  }

  @Override
  public MatrixBuilder append(BigDecimal element) {
    major.get(majorIndex).add(element);
    return this;
  }

  @Override
  public MatrixBuilder end() {
    internalEnd();
    return this;
  }

  @Override
  public Matrix build() {
    MatrixImp matrix = null;
    setConsistentMinorListLengths();
    if (buildMethod.equals(Method.ROW_BY_ROW)) {
      BigDecimal[][] matrixAs2DArray = new BigDecimal[majorIndex][longestMinor];
      for (int i = 0; i < matrixAs2DArray.length; i++) {
        for (int j = 0; j < major.get(i).size(); j++) {
          matrixAs2DArray[i][j] = major.get(i).get(j);
        }
      }
      matrix = new MatrixImp(matrixAs2DArray);
    } else if (buildMethod.equals(Method.COL_BY_COL)) {
      BigDecimal[][] matrixAs2DArray = new BigDecimal[longestMinor][majorIndex];
      for (int i = 0; i < major.size(); i++) {
        for (int j = 0; j < major.get(i).size(); j++) {
          matrixAs2DArray[j][i] = major.get(i).get(j);
        }
      }
      matrix = new MatrixImp(matrixAs2DArray);
    }
    return matrix;
  }

  @Override
  public MatrixBuilder clear() {
    internalClear();
    return this;
  }
}
