package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

public interface MatrixBuilder {

  MatrixBuilder rowByRow();

  MatrixBuilder colByCol();

  MatrixBuilder append(BigDecimal element);

  MatrixBuilder end();

  Matrix build();

  MatrixBuilder clear();
}
