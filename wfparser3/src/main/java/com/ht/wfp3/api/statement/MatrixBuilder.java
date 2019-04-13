package com.ht.wfp3.api.statement;

public interface MatrixBuilder {

  MatrixBuilder buildRowByRow();

  MatrixBuilder append(String element);

  MatrixBuilder endRow();

  Matrix build();

  MatrixBuilder clear();

}
