package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

public interface MatrixBuilder {

  MatrixBuilder buildRowByRow();

  MatrixBuilder append(BigDecimal element);

  MatrixBuilder endRow();

  Matrix build();

  MatrixBuilder clear();
}
