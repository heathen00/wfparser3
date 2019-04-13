package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * Represents a two dimensional matrix used by the Basis Matrix (bmat) statement.
 * 
 * @author nickl
 *
 */
public interface Matrix {
  BigDecimal getElementAt(int row, int column);

  int getNumRows();

  int getNumColumns();
}
